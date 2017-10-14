/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Equipo;

import bd.Equipo;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import transaccion.TEquipo;
import utils.JsonRespuesta;
import utils.Parser;

/**
 *
 * @author JuanMa
 */
@WebServlet(name = "edit_bolsa", urlPatterns = {"/edit_bolsa"})
public class EquipoEditBolsa extends HttpServlet {

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

            Integer id = Parser.parseInt(request.getParameter("id_equipo"));
            Integer bolsa = Parser.parseInt(request.getParameter("bolsa"));

            TEquipo tequipo = new TEquipo();
            Equipo equipo = tequipo.getById(id);
            if (bolsa == 1) {
                equipo.setObservaciones("SI");
            } else {
                equipo.setObservaciones("NO");
            }
            JsonRespuesta jr = new JsonRespuesta();
            if (tequipo.actualizar(equipo)) {
                jr.setResult("OK");
                jr.setRecord(equipo);
            } else {
                jr.setResult("FAIL");
            }
            String jsonResult = new Gson().toJson(jr);
            out.print(jsonResult);
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
