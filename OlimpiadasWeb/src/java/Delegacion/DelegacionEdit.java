/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Delegacion;

import bd.Delegacion;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import transaccion.TDelegacion;
import utils.BaseException;
import utils.JsonRespuesta;
import utils.Parser;

/**
 *
 * @author Diego
 */
public class DelegacionEdit extends HttpServlet {

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
            out.println("<title>Servlet DelegacionEdit</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DelegacionEdit at " + request.getContextPath() + "</h1>");
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
        String nombre = request.getParameter("nombre");
        String observaciones = request.getParameter("observaciones");
        String nombre_corto = request.getParameter("nombre_corto");
        TDelegacion tdelegacion = new TDelegacion();
        Delegacion delegacion;
        boolean nuevo = false;
        JsonRespuesta jr = new JsonRespuesta();
        try {
            delegacion = tdelegacion.getById(id);
            if (delegacion == null) {
                delegacion = new Delegacion();
                nuevo = true;
            }
            delegacion.setNombre(nombre);
            delegacion.setNombre_corto(nombre_corto);
            delegacion.setObservaciones(observaciones);
            boolean todoOk;
            if (nuevo) {
                id = tdelegacion.alta(delegacion);
                todoOk = id != 0;
            } else {
                todoOk = tdelegacion.actualizar(delegacion);
            }

            if (!todoOk) {
                throw new BaseException("ERROR", "Ocurri&oacute; un error al guardar la categor&iacute;a");
            }
            jr.setResult("OK");
            jr.setRecord(delegacion);
            String jsonResult = new Gson().toJson(jr);
            out.print(jsonResult);
        } catch (BaseException ex) {
            jr.setResult(ex.getResult());
            jr.setMessage(ex.getMessage());
        } finally {
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
