/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EquipoDetalle;

import bd.Equipo;
import bd.Equipo_detalle;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import transaccion.TEquipo;
import transaccion.TEquipo_detalle;
import utils.BaseException;
import utils.JsonRespuesta;
import utils.Parser;

/**
 *
 * @author Diego
 */
public class EquipoDetalleEdit extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EquipoDetalleEdit</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EquipoDetalleEdit at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String pagNro = request.getParameter("pagNro");                       
        Integer page = (pagNro!=null)?Integer.parseInt(pagNro):0;
        Integer id = Parser.parseInt(request.getParameter("id"));
        Integer id_equipo = Parser.parseInt(request.getParameter("id_equipo"));        
        Integer id_jugador = Parser.parseInt(request.getParameter("id_jugador"));        
        TEquipo_detalle tequipo_detalle = new TEquipo_detalle();
        Equipo_detalle equipo_detalle;
        boolean nuevo = false;
        JsonRespuesta jr = new JsonRespuesta();
        try {
            Equipo equipo = new TEquipo().getById(id_equipo);
            if(equipo==null) throw new BaseException("ERROR","No se encontr&oacute; el equipo");
            
            equipo_detalle = tequipo_detalle.getById(id);
            if(equipo_detalle==null){
                equipo_detalle = new Equipo_detalle();
                nuevo = true;
            }
            equipo_detalle.setId_equipo(id_equipo);
            equipo_detalle.setId_jugador(id_jugador);            
            boolean todoOk;
            if(nuevo) {
                id = tequipo_detalle.alta(equipo_detalle);
                todoOk = id!=0; 
            } else todoOk = tequipo_detalle.actualizar(equipo_detalle);
            
            
            if(!todoOk) throw new BaseException("ERROR","Ocurri&oacute; un error al guardar la categor&iacute;a");
            jr.setResult("OK");
            jr.setRecord(equipo_detalle);
            String jsonResult = new Gson().toJson(jr);
            out.print(jsonResult);
        } catch(BaseException ex){
            jr.setResult(ex.getResult());
            jr.setMessage(ex.getMessage());
        }finally {            
            out.close();
        }
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
