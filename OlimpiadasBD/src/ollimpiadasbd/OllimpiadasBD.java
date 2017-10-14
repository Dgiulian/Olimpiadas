/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ollimpiadasbd;

import bd.Categoria;
import bd.Cronograma;
import bd.Delegacion;
import bd.Equipo;
import bd.Equipo_detalle;
import bd.Grupo;
import bd.Grupo_detalle;
import bd.Jugador;
import bd.detalle.EquipoDet;
import bd.detalle.Equipo_detalleDet;
import conexion.Conexion;
import java.util.HashMap;
import java.util.List;
import javafx.scene.paint.Color;
import transaccion.TCategoria;
import transaccion.TCronograma;
import transaccion.TDelegacion;
import transaccion.TEquipo;
import transaccion.TEquipo_detalle;
import transaccion.TGrupo;
import transaccion.TGrupo_detalle;
import transaccion.TUsuario;
import transaccion_vista.TNotificacion;

/**
 *
 * @author Diego
 */
public class OllimpiadasBD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        OllimpiadasBD ol = new OllimpiadasBD();
        //ol.cargar_engrupo();
    }

    public void cargar_enprueba() {

        
        
        
    }

    public void cargar_engrupo() {

        TCategoria tc = new TCategoria();
        List<Categoria> lista_categoria = tc.getById_deporte(5);
        System.out.println("Deporte: NATACION");
        for (Categoria c : lista_categoria) {
            List<Equipo> equipos = new TEquipo().getById_categoria(c.getId());
            System.out.println("Categoria: " + c.getNombre());
            for (Equipo e : equipos) {
                HashMap<String, String> filtro = new HashMap<String, String>();
                filtro.put("id_equipo", String.valueOf(e.getId()));
                List<Grupo_detalle> lista_grupo = new TGrupo_detalle().getListFiltro(filtro);
                if (lista_grupo != null && lista_grupo.size() > 0) {
                } else {
                    System.out.println("No esta en el grupo: " + e.getNombre());
                    List<Grupo> list_grupo = new TGrupo().getById_categoria(c.getId());
                    if (list_grupo != null) {
                        Grupo grupo = list_grupo.get(0);
                        System.out.println("Doy de alta en grupo: " + grupo.getNombre());
                        Grupo_detalle grupo_detalle = new Grupo_detalle();
                        grupo_detalle.setId_equipo(e.getId());
                        grupo_detalle.setId_grupo(grupo.getId());
                        new TGrupo_detalle().alta(grupo_detalle);
                    }
                }

            }
        }

    }

    public void individualizar() {

        // TODO code application logic here
        TCategoria tc = new TCategoria();
        List<Categoria> lista_categoria = tc.getById_deporte(5);
        for (Categoria c : lista_categoria) {
            if (c.getId() != 32 && c.getId() != 82 && c.getId() != 91 && c.getId() != 107 && c.getId() != 90) {
                List<Equipo> equipos = new TEquipo().getById_categoria(c.getId());
                for (Equipo e : equipos) {
                    EquipoDet detalle = new EquipoDet(e);
                    detalle.setearJugadores();
                    if (detalle.getJugadores().size() > 1) {
                        System.out.println("Categoria: " + c.getNombre());
                        System.out.println("Equipo: " + e.getNombre());
                        System.out.println("***************************");
                        new OllimpiadasBD().dividir_equipo(detalle);
                    }
                }
            }
        }

    }

    public OllimpiadasBD() {
    }

    public void dividir_equipo(EquipoDet equipo) {

        int cantidad = equipo.getJugadores().size();
        List<Equipo_detalleDet> jugadores = equipo.getJugadores();
        int id = 0;
        while (cantidad > 1) {
            System.out.println("CANTIDAD: " + cantidad);
            Equipo_detalleDet jugador = jugadores.get(id);
            Equipo equipo_nuevo = new Equipo();
            equipo_nuevo.setId_categoria(equipo.getId_categoria());
            equipo_nuevo.setId_delegacion(equipo.getId_delegacion());
            equipo_nuevo.setObservaciones("");
            equipo_nuevo.setNombre(equipo.getNombre() + " v" + String.valueOf(cantidad));
            int id_nuevo = new TEquipo().alta(equipo_nuevo);
            if (id_nuevo > 0) {
                System.out.println("Alta EQUPO: " + id_nuevo);
                Equipo_detalle jugador_nuevo = new Equipo_detalle();
                jugador_nuevo.setId_equipo(id_nuevo);
                jugador_nuevo.setId_jugador(jugador.getId_jugador());
                int id_nuevo_jugador = new TEquipo_detalle().alta(jugador_nuevo);
                if (id_nuevo_jugador > 0) {
                    System.out.println("ID Jugador Nuevo: " + id_nuevo_jugador);
                    Equipo_detalle jugador_eliminar = new Equipo_detalle();
                    jugador_eliminar.setId(jugador.getId());
                    new TEquipo_detalle().baja(jugador_eliminar);
                }
            }
            cantidad = cantidad - 1;
            id++;
        }

    }

}
