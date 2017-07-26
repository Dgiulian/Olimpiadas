/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Categoria;

import Categoria.CategoriaList;
import bd.Categoria;
import bd.Deporte;
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
import utils.JsonRespuesta;
import utils.Parser;

/**
 *
 * @author Diego
 */
public class CategoriaList extends HttpServlet {
    HashMap<Integer, Deporte> mapDeportes;
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
        String pagNro = request.getParameter("pagNro");       
        Integer id_deporte = Parser.parseInt(request.getParameter("id_deporte"));
                
        Integer page = (pagNro!=null)?Integer.parseInt(pagNro):0;
        mapDeportes = new TDeporte().getMap();
        
        try {
            JsonRespuesta jr = new JsonRespuesta();
            HashMap<String,String> filtro = new HashMap<String,String>();
            if(id_deporte!=0) filtro.put("id_deporte",id_deporte.toString());
            
            List<Categoria> lista = new TCategoria().getListFiltro(filtro);
            List<CategoriaDet> listaDet = new ArrayList();            
                        
            if (lista != null) {
                for(Categoria c:lista) listaDet.add(new CategoriaDet(c));
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

    private class CategoriaDet extends Categoria {
        Deporte deporte;
        public CategoriaDet(Categoria categoria) {
            super(categoria);
            deporte = mapDeportes.get(categoria.getId_deporte());            
        }
    }

}
