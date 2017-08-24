/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Equipo;

import bd.Equipo;
import bd.Grupo_detalle;
import bd.detalle.EquipoDet;
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
import transaccion.TEquipo;
import transaccion.TGrupo_detalle;
import utils.JsonRespuesta;
import utils.Parser;

/**
 *
 * @author Diego
 */
public class EquipoList extends HttpServlet {
    
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
        Integer id_grupo = Parser.parseInt(request.getParameter("id_grupo"));
        Integer id_delegacion = Parser.parseInt(request.getParameter("id_delegacion"));
        String nombre     = request.getParameter("nombre");
        boolean fitroByGrupo = (request.getParameter("id_grupo")!=null);
        Integer page = (pagNro!=null)?Integer.parseInt(pagNro):0;
        
        
        TGrupo_detalle tgrupo_detalle = new TGrupo_detalle();
        try {
            JsonRespuesta jr = new JsonRespuesta();
            HashMap<String,String> filtro = new HashMap<>();
             
            if(nombre!=null && !"".equals(nombre)) filtro.put("nombre",nombre);
            if(id_delegacion!=0) filtro.put("id_delegacion",id_delegacion.toString());
            
            List<Equipo> lista = new TEquipo().getListFiltro(filtro);
            List<EquipoDet> listaDet = new ArrayList();            
                        
            if (lista != null) {
                
                for(Equipo equipo:lista) {
                    if(fitroByGrupo){
                        List<Grupo_detalle> lstGruposEquipo = tgrupo_detalle.getById_equipo(equipo.getId());
                        if(!tgrupo_detalle.contiene(lstGruposEquipo,id_grupo)) continue;
                    }
                    listaDet.add(new EquipoDet(equipo));
                }
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
