/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EquipoDetalle;

import bd.Equipo_detalle;
import bd.Jugadores;
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
import transaccion.TEquipo_detalle;
import transaccion.TJugadores;
import utils.JsonRespuesta;
import utils.Parser;

/**
 *
 * @author Diego
 */
public class EquipoDetalleList extends HttpServlet {

    public HashMap<Integer, Jugadores> mapJugadores;

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
        Integer id_equipo = Parser.parseInt(request.getParameter("id_equipo"));
                
        Integer page = (pagNro!=null)?Integer.parseInt(pagNro):0;
        mapJugadores = new TJugadores().getMap();
        
        try {
            JsonRespuesta jr = new JsonRespuesta();
            HashMap<String,String> filtro = new HashMap<String,String>();
            filtro.put("id_equipo", id_equipo.toString());
            List<Equipo_detalle> lista = new TEquipo_detalle().getListFiltro(filtro);
            List<Equipo_detalleDet> listaDet = new ArrayList();            
                        
            if (lista != null) {
                for(Equipo_detalle c:lista) listaDet.add(new Equipo_detalleDet(c));
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

    private class Equipo_detalleDet extends Equipo_detalle {
        Jugadores jugador;
        public Equipo_detalleDet(Equipo_detalle c) {
            super(c);
            jugador = mapJugadores.get(c.getId_jugador());
        }
    }

}