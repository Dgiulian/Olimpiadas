/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PruebaDetalle;

import bd.Prueba_deportiva;
import bd.Prueba_deportiva_detalle;
import bd.detalle.Prueba_deportivaDet;
import bd.detalle.Prueba_deportiva_DetalleDet;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import transaccion.TPrueba_deportiva;
import transaccion.TPrueba_deportiva_detalle;
import utils.JsonRespuesta;
import utils.Parser;

/**
 *
 * @author JuanMa
 */
@WebServlet(name = "PruebaDetalleList", urlPatterns = {"/PruebaDetalleList"})
public class PruebaListPruebaDetalle extends HttpServlet {

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

            Integer id_prueba = Parser.parseInt(request.getParameter("id_prueba"));

            JsonRespuesta jr = new JsonRespuesta();
            HashMap<String, String> filtro = new HashMap<>();

            if (id_prueba != 0) {
                filtro.put("id_prueba", id_prueba.toString());
            }

            List<Prueba_deportiva_detalle> lista = new TPrueba_deportiva_detalle().getListFiltro(filtro);
            List<Prueba_deportiva_DetalleDet> listaDet = new ArrayList();
            if (lista != null) {
                for (Prueba_deportiva_detalle c : lista) {
                    Prueba_deportiva_DetalleDet pd = new Prueba_deportiva_DetalleDet(c);
                    pd.setearPrueba();
                    listaDet.add(pd);
                }
                jr.setTotalRecordCount(listaDet.size());
            } else {
                jr.setTotalRecordCount(0);
            }
            jr.setResult("OK");
            jr.setRecords(listaDet);
            String jsonResult = new Gson().toJson(jr);
            out.print(jsonResult);

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
