/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jugador;

import bd.Categoria;
import bd.Delegacion;
import bd.Deporte;
import bd.Equipo;
import bd.Equipo_detalle;
import bd.Jugador;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import transaccion.TCategoria;
import transaccion.TDelegacion;
import transaccion.TDeporte;
import transaccion.TEquipo;
import transaccion.TEquipo_detalle;
import transaccion.TJugador;
import utils.BaseException;
import utils.Parser;
import utils.PathCfg;

/**
 *
 * @author Diego
 */
public class JugadorCategoria extends HttpServlet {

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
        try {
            Integer id = Parser.parseInt(request.getParameter("id"));
            Jugador jugador;
            jugador = new TJugador().getById(id);
            if (jugador == null) {
                throw new BaseException("ERROR", "No se encontr&oacute; el jugador");
            }
            TCategoria tcategoria = new TCategoria();
            tcategoria.setOrderBy(" id_deporte ");
            List<Categoria> categorias = (List<Categoria>) tcategoria.getListFiltro(null);
            List<Categoria> inscripciones = (List<Categoria>) new TCategoria().getByJugador(jugador.getId());
            if (inscripciones == null) {
                inscripciones = new ArrayList<>();
            }
            HashMap<Integer, Deporte> deportes = new TDeporte().getMap();
            request.setAttribute("jugador", jugador);
            request.setAttribute("categorias", categorias);
            request.setAttribute("inscripciones", inscripciones);
            request.setAttribute("deportes", deportes);

            request.getRequestDispatcher("jugador_categoria.jsp").forward(request, response);
        } catch (BaseException ex) {
            request.setAttribute("titulo", ex.getResult());
            request.setAttribute("mensaje", ex.getMessage());
            request.getRequestDispatcher("message.jsp").forward(request, response);
        }
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

        try {
            Integer id = Parser.parseInt(request.getParameter("id"));
            TJugador tjugador = new TJugador();
            TEquipo tequipo = new TEquipo();
            TEquipo_detalle tequipo_detalle = new TEquipo_detalle();
            Jugador jugador = tjugador.getById(id);
            if (jugador == null) {
                throw new BaseException("ERROR", "No se encontr&oacute; el jugador");
            }

            tequipo_detalle.deleteByJugador(jugador.getId());

            Integer id_delegacion = jugador.getId_delegacion();
            Map<String, String[]> parametros = request.getParameterMap();
            Iterator<String> it = parametros.keySet().iterator();
            while (it.hasNext()) {
                String key = it.next();
                String[] values = parametros.get(key);
                String value = values.length > 0 ? values[0] : "";
                if (key.startsWith("id_categoria")) {
                    Integer id_categoria = parseCategoria(key);
                    if (id_categoria == 0) {
                        continue;
                    }
                    Equipo equipo = tequipo.getByDelegacionCategoria(id_delegacion, id_categoria);

                    int id_equipo = 0;
                    if (equipo == null) {
                        equipo = new Equipo();
                        equipo.setId_categoria(id_categoria);
                        equipo.setId_delegacion(id_delegacion);
                        Categoria categoria = new TCategoria().getById(id_categoria);
                        Delegacion delegacion = new TDelegacion().getById(id_delegacion);
                        equipo.setNombre(delegacion.getNombre_corto() + " " + categoria.getNombre_corto() + " MAS");
                        id_equipo = new TEquipo().alta(equipo);
                    } else {
                        id_equipo = equipo.getId();
                    }
                    Equipo_detalle equipo_detalle = new Equipo_detalle();
                    equipo_detalle.setId_equipo(equipo.getId());
                    equipo_detalle.setId_jugador(jugador.getId());
                    tequipo_detalle.alta(equipo_detalle);

                }
            }
            response.sendRedirect(PathCfg.JUGADOR);
        } catch (BaseException ex) {
            request.setAttribute("titulo", ex.getResult());
            request.setAttribute("mensaje", ex.getMessage());
            request.getRequestDispatcher("message.jsp").forward(request, response);
        }
    }

    private Integer parseCategoria(String key) {
        String[] split = key.split("_");
        String value = split.length > 1 ? split[split.length - 1] : "0";
        return Parser.parseInt(value);
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
