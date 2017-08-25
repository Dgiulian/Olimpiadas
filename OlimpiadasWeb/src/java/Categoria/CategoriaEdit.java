/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Categoria;

import bd.Categoria;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import transaccion.TCategoria;
import transaccion.TEquipo;
import utils.BaseException;
import utils.JsonRespuesta;
import utils.Parser;

/**
 *
 * @author Diego
 */
public class CategoriaEdit extends HttpServlet {

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
        String nombre = request.getParameter("nombre");
        String nombre_corto = request.getParameter("nombre_corto");
        String detalle = request.getParameter("detalle");
        Integer orden_puntaje = Parser.parseInt(request.getParameter("orden_puntaje"));
        Integer tipo_puntaje = Parser.parseInt(request.getParameter("tipo_puntaje"));
        TCategoria tcategoria = new TCategoria();
        Categoria categoria;
        boolean nuevo = false;
        JsonRespuesta jr = new JsonRespuesta();
        try {
            categoria = tcategoria.getById(id);
            if(categoria==null){
                categoria = new Categoria();
                nuevo = true;
            }
            categoria.setNombre(nombre);
            categoria.setNombre_corto(nombre_corto);
            categoria.setDetalle(detalle);
            categoria.setId_deporte(id_deporte); 
            categoria.setOrden_puntaje(orden_puntaje);
            categoria.setTipo_puntaje(tipo_puntaje);
            boolean todoOk;
            if(nuevo) {
                id = tcategoria.alta(categoria);
                categoria.setId(id);
                todoOk = id!=0; 
            } else todoOk = tcategoria.actualizar(categoria);
            
            if (nuevo){
                new TEquipo().crearPorCategoria(categoria);
            }
            if(!todoOk) throw new BaseException("ERROR","Ocurri&oacute; un error al guardar la categor&iacute;a");
            jr.setResult("OK");
            jr.setRecord(categoria);
            
        } catch(BaseException ex){
            jr.setResult(ex.getResult());
            jr.setMessage(ex.getMessage());
        }finally {
            String jsonResult = new Gson().toJson(jr);
            out.print(jsonResult);
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
