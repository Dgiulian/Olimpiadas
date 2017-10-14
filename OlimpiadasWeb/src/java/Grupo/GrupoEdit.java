/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grupo;

import bd.Grupo;
import bd.Grupo_detalle;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import transaccion.TGrupo;
import transaccion.TGrupo_detalle;
import utils.BaseException;
import utils.JsonRespuesta;
import utils.Parser;

/**
 *
 * @author Diego
 */
public class GrupoEdit extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GrupoEdit</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GrupoEdit at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();

       

        String pagNro = request.getParameter("pagNro");
        Integer page = (pagNro != null) ? Integer.parseInt(pagNro) : 0;
        Integer id = Parser.parseInt(request.getParameter("id"));
        Integer id_categoria = Parser.parseInt(request.getParameter("id_categoria"));

        String[] arrEquipos = request.getParameterValues("arrEquipo[]");
        String nombre = request.getParameter("nombre");
        String observacion = request.getParameter("observacion");
        Integer id_tipo_tabla = Parser.parseInt(request.getParameter("tipo_vista_tabla"));
        TGrupo tgrupo = new TGrupo();
        Grupo grupo;
        boolean nuevo = false;
        JsonRespuesta jr = new JsonRespuesta();
        try {
            grupo = tgrupo.getById(id);
            if (grupo == null) {
                grupo = new Grupo();
                nuevo = true;
            }
            grupo.setNombre(nombre);
            grupo.setObservacion(observacion);
            grupo.setId_categoria(id_categoria);
            grupo.setTipo_vista_tabla(id_tipo_tabla);
            boolean todoOk;
            if (nuevo) {
                id = tgrupo.alta(grupo);
                grupo.setId(id);
                todoOk = id != 0;
            } else {
                todoOk = tgrupo.actualizar(grupo);
            }

            System.out.println("estado acutalizacion: " + todoOk);
            System.out.println(arrEquipos);

            if (todoOk) {
                TGrupo_detalle tgrupo_detalle = new TGrupo_detalle();
                tgrupo_detalle.eliminar(grupo.getId());
                for (String eq : arrEquipos) {
                    Integer id_equipo = Parser.parseInt(eq);
                    Grupo_detalle grupo_detalle = new Grupo_detalle();
                    grupo_detalle.setId_grupo(grupo.getId());
                    grupo_detalle.setId_equipo(id_equipo);
                    tgrupo_detalle.alta(grupo_detalle);
                }
            }

            if (!todoOk) {
                throw new BaseException("ERROR", "Ocurri&oacute; un error al guardar la categor&iacute;a");
            }
            jr.setResult("OK");
            jr.setRecord(grupo);
        } catch (BaseException ex) {
            jr.setResult(ex.getResult());
            jr.setMessage(ex.getMessage());
        } finally {
            String jsonResult = new Gson().toJson(jr);
            out.print(jsonResult);
            out.close();
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
