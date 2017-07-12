/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prueba;

import bd.Prueba_deportiva;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import transaccion.TPrueba_deportiva;
import utils.BaseException;
import utils.JsonRespuesta;
import utils.Parser;

/**
 *
 * @author Diego
 */
public class PruebaEdit extends HttpServlet {

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
            out.println("<title>Servlet PruebaEdit</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PruebaEdit at " + request.getContextPath() + "</h1>");
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
        Integer id_deporte = Parser.parseInt(request.getParameter("id_deporte"));
        Integer id_categoria = Parser.parseInt(request.getParameter("id_categoria"));
        String observaciones = request.getParameter("observaciones");
        TPrueba_deportiva tprueba_deportiva = new TPrueba_deportiva();
        Prueba_deportiva prueba_deportiva;
        boolean nuevo = false;
        JsonRespuesta jr = new JsonRespuesta();
        try {
            prueba_deportiva = tprueba_deportiva.getById(id);
            if(prueba_deportiva==null){
                prueba_deportiva = new Prueba_deportiva();
                nuevo = true;
            }
            prueba_deportiva.setId_categoria(id_categoria);
            prueba_deportiva.setObservaciones(observaciones);
            prueba_deportiva.setId_deporte(id_deporte);            
            boolean todoOk;
            if(nuevo) {
                id = tprueba_deportiva.alta(prueba_deportiva);
                todoOk = id!=0; 
            } else todoOk = tprueba_deportiva.actualizar(prueba_deportiva);
            
            
            if(!todoOk) throw new BaseException("ERROR","Ocurri&oacute; un error al guardar la categor&iacute;a");
            jr.setResult("OK");
            jr.setRecord(prueba_deportiva);
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
