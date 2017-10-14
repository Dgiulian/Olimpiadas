/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.detalle;

import bd.Delegacion;
import bd.Equipo;
import bd.Equipo_detalle;
import bd.Jugador;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import transaccion.TDelegacion;
import transaccion.TEquipo_detalle;
import transaccion.TJugador;

/**
 *
 * @author JuanMa
 */
public class EquipoDet extends Equipo {

    Delegacion delegacion;
    public static final HashMap<Integer, Delegacion> mapDelegaciones = new TDelegacion().getMap();
    List<Equipo_detalleDet> jugadores = new ArrayList<Equipo_detalleDet>();

    public EquipoDet(Equipo equipo) {
        super(equipo);
        delegacion = mapDelegaciones.get(equipo.getId_delegacion());
        List<Equipo_detalle> lista_jugadores = new TEquipo_detalle().getById_equipo(equipo.id);
        if (lista_jugadores != null && lista_jugadores.size() == 1) {
            Equipo_detalle jugador = lista_jugadores.get(0);
            Equipo_detalleDet jugador_detalle = new Equipo_detalleDet(jugador);
            super.setNombre(jugador_detalle.getJugador().getNombre_apellido());
        }
    }

    public void setearJugadores() {
        List<Equipo_detalle> lista_jugadores = new TEquipo_detalle().getById_equipo(this.id);
        List<Equipo_detalleDet> listaDet = new ArrayList<Equipo_detalleDet>();
        if (lista_jugadores != null) {
            for (Equipo_detalle c : lista_jugadores) {
                listaDet.add(new Equipo_detalleDet(c));
            }
        }
        Collections.sort(listaDet);
        this.setJugadores(listaDet);
    }

    public Delegacion getDelegacion() {
        return delegacion;
    }

    public void setDelegacion(Delegacion delegacion) {
        this.delegacion = delegacion;
    }

    public List<Equipo_detalleDet> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Equipo_detalleDet> jugadores) {
        this.jugadores = jugadores;
    }

}
