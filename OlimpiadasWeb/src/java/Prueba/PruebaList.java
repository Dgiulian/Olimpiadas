/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prueba;

import bd.Categoria;
import bd.Delegacion;
import bd.Deporte;
import bd.Prueba_deportiva;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import transaccion.TCategoria;
import transaccion.TDeporte;
import transaccion.TPrueba_deportiva;
import utils.JsonRespuesta;

/**
 *
 * @author Diego
 */
public class PruebaList extends HttpServlet {
    HashMap<Integer, Categoria> mapCategorias;
    HashMap<Integer, Deporte> mapDeportes ;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String pagNro = request.getParameter("pagNro");
        Integer page = (pagNro!=null)?Integer.parseInt(pagNro):0;
        
        mapDeportes = new TDeporte().getMap();
        mapCategorias = new TCategoria().getMap();
        
        try {
            JsonRespuesta jr = new JsonRespuesta();
            List<Prueba_deportiva> lista = new TPrueba_deportiva().getList();
            List<Prueba_deportivaDet> listaDet = new ArrayList();
            if (lista != null) {
                for(Prueba_deportiva c:lista) listaDet.add(new Prueba_deportivaDet(c));
                jr.setTotalRecordCount(listaDet.size());
            } else {
                jr.setTotalRecordCount(0);
            }            
            jr.setResult("OK");
            jr.setRecords(listaDet);
            String jsonResult = new Gson().toJson(jr);
            out.print(jsonResult);
        } finally {            
            out.close();
        }
    }
    private class Prueba_deportivaDet extends Prueba_deportiva {
        Deporte deporte;
        Categoria categoria;
        public Prueba_deportivaDet(Prueba_deportiva prueba_deportiva) {
            super(prueba_deportiva);
            deporte = mapDeportes.get(prueba_deportiva.getId_deporte());            
            categoria = mapCategorias.get(prueba_deportiva.getId_categoria());            
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
