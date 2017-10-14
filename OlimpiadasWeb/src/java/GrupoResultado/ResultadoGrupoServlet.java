/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GrupoResultado;

import bd.Categoria;
import bd.Grupo;
import bd.Prueba_deportiva;
import bd.detalle.Prueba_deportivaDet;
import bd.vista.MedalleroGrupoVista;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import transaccion.TCategoria;
import transaccion.TGrupo;
import transaccion.TPrueba_deportiva;
import transaccion_vista.TMedalleroGrupo;
import utils.JsonRespuesta;
import utils.Parser;

/**
 *
 * @author JuanMa
 */
@WebServlet(name = "resultado_grupo", urlPatterns = {"/resultado_grupo"})
public class ResultadoGrupoServlet extends HttpServlet {

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

            int id_grupo = Parser.parseInt(request.getParameter("id_grupo"));
            Grupo grupo = new TGrupo().getById(id_grupo);
            if (grupo.getTipo_vista_tabla() != 2) {
                TMedalleroGrupo tm = new TMedalleroGrupo();
                List<MedalleroGrupoVista> lista = tm.getMedallero(grupo);
                JsonRespuesta jr = new JsonRespuesta();
                jr.setResult("OK");
                jr.setRecords(lista);
                String jsonResult = new Gson().toJson(jr);
                out.print(jsonResult);
            } else {

                TPrueba_deportiva tp = new TPrueba_deportiva();
                HashMap<String, String> filtro = new HashMap<>();
                filtro.put("id_grupo", String.valueOf(id_grupo));

                List<Prueba_deportiva> lista = new TPrueba_deportiva().getListFiltro(filtro);
                List<Prueba_deportivaDet> listaDet = new ArrayList();
                if (lista != null) {
                    for (Prueba_deportiva c : lista) {
                        Prueba_deportivaDet pb= new Prueba_deportivaDet(c);
                        pb.setearDetalle(true);
                        listaDet.add(pb); 
                    }
                }
                JsonRespuesta jr = new JsonRespuesta();
                jr.setResult("OK");
                jr.setRecords(listaDet);
                String jsonResult = new Gson().toJson(jr);
                out.print(jsonResult);

            }

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
