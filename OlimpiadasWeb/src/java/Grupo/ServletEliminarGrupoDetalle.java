/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grupo;

import bd.Grupo_detalle;
import bd.Prueba_deportiva;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import transaccion.TGrupo_detalle;
import transaccion.TPrueba_deportiva;
import utils.BaseException;
import utils.JsonRespuesta;
import utils.Parser;

/**
 *
 * @author JuanMa
 */
@WebServlet(name = "eliminar_equipo_grupo", urlPatterns = {"/eliminar_equipo_grupo"})
public class ServletEliminarGrupoDetalle extends HttpServlet {

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
        
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        JsonRespuesta jr = new JsonRespuesta();
        try {
            Integer id_grupo = Parser.parseInt(request.getParameter("id_grupo"));
            Integer id_equipo = Parser.parseInt(request.getParameter("id_equipo"));
            
            HashMap<String, String> mapafiltro = new HashMap<String, String>();
            mapafiltro.put("id_equipo", String.valueOf(id_equipo));
            mapafiltro.put("id_grupo", String.valueOf(id_grupo));
            
            List<Grupo_detalle> parametro = new TGrupo_detalle().getListFiltro(mapafiltro);
            if (parametro != null && parametro.size() > 0) {
                boolean baja = new TGrupo_detalle().baja(parametro.get(0));
                if (baja) {
                    jr.setResult("OK");
                } else {
                    throw new BaseException("ERROR", "Ocurrio un error al eliminar el registro");
                }
            } else {
                throw new BaseException("ERROR", "No existe el registro");
            }
        } catch (BaseException ex) {
            jr.setResult(ex.getResult());
            jr.setMessage(ex.getMessage());
        } finally {
            out.print(new Gson().toJson(jr));
            out.close();
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        processRequest(request, response);
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
