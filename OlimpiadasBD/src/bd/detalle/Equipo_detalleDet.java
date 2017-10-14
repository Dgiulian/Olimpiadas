/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.detalle;

import bd.Equipo_detalle;
import bd.Jugador;
import java.util.HashMap;
import transaccion.TJugador;

/**
 *
 * @author JuanMa
 */
public class Equipo_detalleDet extends Equipo_detalle implements Comparable<Equipo_detalleDet> {

    public HashMap<Integer, Jugador> mapJugador;
    Jugador jugador;

    public Equipo_detalleDet(Equipo_detalle c) {
        super(c);
        //jugador = mapJugador.get(c.getId_jugador());
        jugador = new TJugador().getById(c.getId_jugador());
    }

    public HashMap<Integer, Jugador> getMapJugador() {
        return mapJugador;
    }

    public void setMapJugador(HashMap<Integer, Jugador> mapJugador) {
        this.mapJugador = mapJugador;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    @Override
    public int compareTo(Equipo_detalleDet o) {
        if (jugador.getNombre_apellido().compareTo(o.getJugador().getNombre_apellido()) < 0) {
            return -1;
        }
        if (jugador.getNombre_apellido().compareTo(o.getJugador().getNombre_apellido()) > 0) {
            return 1;
        }
        return 0;
    }

}
