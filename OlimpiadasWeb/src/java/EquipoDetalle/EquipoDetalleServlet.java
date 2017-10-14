/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EquipoDetalle;

import bd.Delegacion;
import bd.Equipo;
import bd.Equipo_detalle;
import bd.Jugador;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import transaccion.TDelegacion;
import transaccion.TEquipo;
import transaccion.TEquipo_detalle;
import transaccion.TJugador;
import utils.BaseException;
import utils.Parser;

/**
 *
 * @author Diego
 */
public class EquipoDetalleServlet extends HttpServlet {

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
        Integer id_equipo = Parser.parseInt(request.getParameter("id_equipo"));
        try {
            Equipo equipo = new TEquipo().getById(id_equipo);
            if (equipo == null) {
                throw new BaseException("ERROR", "No se encontr&oacute; el equipo");
            }
            System.out.println("recupero jugadores");
            List<Jugador> lstJugadores = new TJugador().getById_delegacion(equipo.getId_delegacion());
            if (equipo.getObservaciones().equalsIgnoreCase("SI")) {
                lstJugadores = new TJugador().getList();
            }

            System.out.println("recuperao equipo");
            List<Equipo_detalle> lstEquipo_detalle = new TEquipo_detalle().getById_equipo(equipo.getId());
            System.out.println("recupero delegacion");
            Delegacion delegacion = new TDelegacion().getById(equipo.getId_delegacion());
            System.out.println("error");
            if (delegacion == null) {
                throw new BaseException("ERROR", "No se encontr&oacute; la delegaci&oacute;n");
            }
            System.out.println("seteo atributps");
            request.setAttribute("equipo", equipo);
            request.setAttribute("delegacion", delegacion);
            request.setAttribute("lstJugadores", lstJugadores);
            request.setAttribute("lstEquipo_detalle", lstEquipo_detalle);

            request.getRequestDispatcher("equipo_detalle.jsp").forward(request, response);
        } catch (BaseException ex) {
            request.setAttribute("titulo", ex.getResult());
            request.setAttribute("mensaje", ex.getMessage());
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
