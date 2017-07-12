/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Deporte;

import bd.Deporte;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import transaccion.TDeporte;
import utils.BaseException;
import utils.JsonRespuesta;
import utils.Parser;

/**
 *
 * @author Diego
 */
public class DeporteEdit extends HttpServlet {

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
            out.println("<title>Servlet DeporteEdit</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DeporteEdit at " + request.getContextPath() + "</h1>");
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
        Integer tipo = Parser.parseInt(request.getParameter("tipo"));
        String nombre = request.getParameter("nombre");
        Integer cantidad =Parser.parseInt( request.getParameter("cantidad_jugadores"));
        TDeporte tdeporte = new TDeporte();
        Deporte deporte;
        boolean nuevo = false;
        JsonRespuesta jr = new JsonRespuesta();
        try {
            deporte = tdeporte.getById(id);
            if(deporte==null){
                deporte = new Deporte();
                nuevo = true;
            }
            deporte.setNombre(nombre);
            deporte.setCantidad_jugadores(cantidad);
            deporte.setTipo(tipo);            
            boolean todoOk;
            if(nuevo) {
                id = tdeporte.alta(deporte);
                todoOk = id!=0; 
            } else todoOk = tdeporte.actualizar(deporte);
            
            
            if(!todoOk) throw new BaseException("ERROR","Ocurri&oacute; un error al guardar la categor&iacute;a");
            jr.setResult("OK");
            jr.setRecord(deporte);
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
