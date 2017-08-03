/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UsuarioApp;

import bd.Usuario_app;
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
import transaccion.TUsuario_app;
import utils.JsonRespuesta;
import utils.Parser;

/**
 *
 * @author Diego
 */
public class UsuarioAppList extends HttpServlet {

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
        
        String username     = request.getParameter("username");
        String email     = request.getParameter("email");
        String apellido     = request.getParameter("apellido");
        String nombre     = request.getParameter("nombre");
        
        Integer page = (pagNro!=null)?Integer.parseInt(pagNro):0;        
        
        try {
            JsonRespuesta jr = new JsonRespuesta();
            HashMap<String,String> filtro = new HashMap<String,String>();  
            
            if(username != null && !"".equals(username)) filtro.put("username",username);
            if(email != null    && !"".equals(email))    filtro.put("email",email);
            if(apellido != null && !"".equals(apellido)) filtro.put("apellido",apellido);
            if(nombre != null   && !"".equals(nombre))   filtro.put("nombre",nombre);
            
            List<Usuario_app> lista = new TUsuario_app().getListFiltro(filtro);
            List<Usuario_app> listaDet = new ArrayList();            
                        
            if (lista != null) {
                for(Usuario_app c:lista) listaDet.add(new Usuario_app(c));
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
