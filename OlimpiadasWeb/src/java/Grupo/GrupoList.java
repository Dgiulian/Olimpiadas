/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grupo;

import Grupo.GrupoList;
import bd.Equipo;
import bd.Grupo;
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
import transaccion.TGrupo;
import transaccion.TDeporte;
import transaccion.TEquipo;
import utils.JsonRespuesta;
import utils.Parser;

/**
 *
 * @author Diego
 */
public class GrupoList extends HttpServlet {

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
        Integer id_categoria = Parser.parseInt(request.getParameter("id_categoria"));
        String pagNro = request.getParameter("pagNro");       
                
        Integer page = (pagNro!=null)?Integer.parseInt(pagNro):0;
        
        try {
            JsonRespuesta jr = new JsonRespuesta();
            HashMap<String,String> filtro = new HashMap<String,String>();
            //if(id_categoria!=0) 
                filtro.put("id_categoria", id_categoria.toString());
            List<Grupo> lista = new TGrupo().getListFiltro(filtro);
            List<GrupoList.GrupoDet> listaDet = new ArrayList();                        
            if (lista != null) {
                for(Grupo c:lista) listaDet.add(new GrupoList.GrupoDet(c));
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
    private class GrupoDet extends Grupo{
        List<Equipo> detalle;
        public GrupoDet(Grupo g){
            super(g);
            detalle = new TEquipo().getById_grupo(g.getId());
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
