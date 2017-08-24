/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prueba;

import bd.Prueba_deportiva;
import bd.detalle.Prueba_deportivaDet;
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
import transaccion.TPrueba_deportiva;
import utils.JsonRespuesta;
import utils.Parser;
import utils.TFecha;

/**
 *
 * @author Diego
 */
public class PruebaList extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String pagNro = request.getParameter("pagNro");
        Integer page = (pagNro!=null)?Integer.parseInt(pagNro):0;
        String fecha = TFecha.formatearFechaVistaBd(request.getParameter("fecha"));
        Integer id_deporte = Parser.parseInt(request.getParameter("id_deporte"));
        Integer id_categoria = Parser.parseInt(request.getParameter("id_categoria"));
        Integer id_grupo = Parser.parseInt(request.getParameter("id_grupo"));        
        Integer id_estado = Parser.parseInt(request.getParameter("id_estado"));
        
        try {
            JsonRespuesta jr = new JsonRespuesta();
            HashMap<String,String> filtro = new HashMap<>();
            
            if (fecha!=null && !"".equals(fecha)) filtro.put("fecha",fecha);
            if (id_deporte!=0) filtro.put("id_deporte",id_deporte.toString());
            if (id_categoria!=0) filtro.put("id_categoria",id_categoria.toString());
            if (id_grupo!=0) filtro.put("id_grupo",id_grupo.toString());
            if (id_estado!=0) filtro.put("id_estado",id_estado.toString());
            
            List<Prueba_deportiva> lista = new TPrueba_deportiva().getListFiltro(filtro);
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
