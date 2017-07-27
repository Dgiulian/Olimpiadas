/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prueba;

import bd.Categoria;
import bd.Deporte;
import bd.Equipo;
import bd.Grupo;
import bd.Prueba_deportiva;
import bd.Prueba_deportiva_detalle;
import bd.Sede;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import transaccion.TCategoria;
import transaccion.TDeporte;
import transaccion.TEquipo;
import transaccion.TGrupo;
import transaccion.TPrueba_deportiva;
import transaccion.TPrueba_deportiva_detalle;
import transaccion.TSede;
import utils.BaseException;
import utils.JsonRespuesta;
import utils.Parser;
import utils.PathCfg;
import utils.TFecha;

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

        Integer id = Parser.parseInt(request.getParameter("id"));
        Prueba_deportiva prueba;
        prueba = new TPrueba_deportiva().getById(id);
        if(prueba==null){
            prueba = new Prueba_deportiva();
        }
        
        List<Deporte> deportes = (List<Deporte>) new TDeporte().getList(); 
        List<Sede> sedes = (List<Sede>) new TSede().getList();
        
        List<Categoria> categorias = (List<Categoria>) new TCategoria().getById_deporte(prueba.getId_deporte());
        List<Grupo> grupos = (List<Grupo>) new TGrupo().getById(prueba.getId_categoria());
        List<Equipo> equipos_sel = (List<Equipo>) new TEquipo().getById_prueba(prueba.getId());        
        List<Equipo> equipos = (List<Equipo>) new TEquipo().getById_grupo(prueba.getId_grupo());
        request.setAttribute("prueba",prueba);
        request.setAttribute("deportes",deportes);
        request.setAttribute("sedes",sedes);
        request.setAttribute("equipos",equipos);
        request.setAttribute("equipos_sel",equipos_sel);
        request.setAttribute("categorias",categorias);
        request.setAttribute("grupos",grupos);
        request.getRequestDispatcher("prueba_deportiva_edit.jsp").forward(request,response);
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
        Integer id_sede = Parser.parseInt(request.getParameter("id_sede"));
        Integer id_categoria = Parser.parseInt(request.getParameter("id_categoria"));
        Integer id_grupo = Parser.parseInt(request.getParameter("id_grupo"));
        Integer id_estado = Parser.parseInt(request.getParameter("id_estado"));
        Integer tipo_puntaje = Parser.parseInt(request.getParameter("tipo_puntaje"));
        Integer orden_puntaje = Parser.parseInt(request.getParameter("orden_puntaje"));
        String fecha = TFecha.formatearFechaVistaBd(request.getParameter("fecha"));
        String hora = request.getParameter("hora");
        String[] equipos = request.getParameterValues("equipos[]");
        
        if(equipos==null)
            equipos = request.getParameterValues("equipos");
        
        String observaciones = request.getParameter("observaciones");
        boolean byAjax = request.getParameter("ajax")!=null;
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
            prueba_deportiva.setId_sede(id_sede);
            prueba_deportiva.setObservaciones(observaciones);
            prueba_deportiva.setId_deporte(id_deporte);
            prueba_deportiva.setId_estado(id_estado);
            prueba_deportiva.setId_grupo(id_grupo);
            prueba_deportiva.setTipo_puntaje(tipo_puntaje);
            prueba_deportiva.setOrden_puntaje(orden_puntaje);
            prueba_deportiva.setFecha(fecha);
            prueba_deportiva.setHora(hora);
            boolean todoOk;
            if(nuevo) {
                id = tprueba_deportiva.alta(prueba_deportiva);
                prueba_deportiva.setId(id);
                todoOk = id!=0; 
            } else todoOk = tprueba_deportiva.actualizar(prueba_deportiva);
            
            
            
            if(!todoOk) throw new BaseException("ERROR","Ocurri&oacute; un error al guardar la prueba deportiva");
            TPrueba_deportiva_detalle tprueba_detalle = new TPrueba_deportiva_detalle();
            tprueba_detalle.eliminar(prueba_deportiva.getId());
            if(equipos!=null){
                for(String eq:equipos){
                    Integer id_equipo = Parser.parseInt(eq);
                    Prueba_deportiva_detalle prueba_detalle = new Prueba_deportiva_detalle();
                    prueba_detalle.setId_equipo(id_equipo);
                    prueba_detalle.setId_prueba(prueba_deportiva.getId());
                    tprueba_detalle.alta(prueba_detalle);
                }
            }
            
             if(byAjax){
                jr.setResult("OK");
                jr.setRecord(prueba_deportiva);
            }            
            else response.sendRedirect(PathCfg.PRUEBA);
            
        } catch(BaseException ex){
            jr.setResult(ex.getResult());
            jr.setMessage(ex.getMessage());
        }finally {
           if(byAjax){
                String jsonResult = new Gson().toJson(jr);
                out.print(jsonResult);
                
            } 
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
