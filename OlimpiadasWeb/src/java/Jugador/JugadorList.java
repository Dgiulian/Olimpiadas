/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jugador;

import bd.Jugador;
import bd.detalle.JugadorDet;
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
import transaccion.TJugador;
import utils.JsonRespuesta;
import utils.Parser;

/**
 *
 * @author Diego
 */
public class JugadorList extends HttpServlet {
    
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
        response.setContentType("application/json;charset=ISO-8859-1");
        PrintWriter out = response.getWriter();
        String pagNro = request.getParameter("pagNro");       
        Integer id_delegacion = Parser.parseInt(request.getParameter("id_delegacion"));
        String nombre     = request.getParameter("nombre");           
        Integer page = (pagNro!=null)?Integer.parseInt(pagNro):0;
        
        
        try {
            HashMap<String,String>filtro = new HashMap<>();
            if(id_delegacion!=0) filtro.put("id_delegacion",id_delegacion.toString());
            if(nombre!=null && !"".equals(nombre)) filtro.put("nombre",nombre);
            JsonRespuesta jr = new JsonRespuesta();
            
            List<Jugador> lista = new TJugador().setOrderBy("nombre_apellido").getListFiltro(filtro);
            List<JugadorDet> listaDet = new ArrayList();            
                        
            if (lista != null) {
                for(Jugador c:lista) listaDet.add(new JugadorDet(c));
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
