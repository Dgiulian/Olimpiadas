/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.detalle;

import bd.Categoria;
import bd.Deporte;
import bd.Equipo;
import bd.Grupo;
import bd.Prueba_deportiva;
import bd.Prueba_deportiva_detalle;
import bd.Sede;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import transaccion.TCategoria;
import transaccion.TDeporte;
import transaccion.TEquipo;
import transaccion.TGrupo;
import transaccion.TPrueba_deportiva;
import transaccion.TSede;

/**
 *
 * @author JuanMa
 */
public class Prueba_deportiva_DetalleDet extends Prueba_deportiva_detalle {

    public static HashMap<Integer, Equipo> mapEquipos;
    public static HashMap<Integer, Prueba_deportiva> mapPruebas;

    EquipoDet equipo;
    Prueba_deportivaDet prueba;

    public Prueba_deportiva_DetalleDet(Prueba_deportiva_detalle prueba_deportiva_detalle) {
        super(prueba_deportiva_detalle);

        mapEquipos = new TEquipo().getMap();
        Equipo equipo_base = mapEquipos.get(prueba_deportiva_detalle.getId_equipo());
        equipo = new EquipoDet(equipo_base);

    }

    public Prueba_deportiva_DetalleDet(Prueba_deportiva_detalle prueba_deportiva_detalle, boolean completo) {
        super(prueba_deportiva_detalle);

        mapEquipos = new TEquipo().getMap();
        Equipo equipo_base = mapEquipos.get(prueba_deportiva_detalle.getId_equipo());
        equipo = new EquipoDet(equipo_base);
        if (completo) {
            equipo.setearJugadores();
        }

    }

    public void setearPrueba() {
        mapPruebas = new TPrueba_deportiva().getMap();
        Prueba_deportiva prueba_base = mapPruebas.get(this.getId_prueba());
        this.prueba = new Prueba_deportivaDet(prueba_base);
    }

    public static HashMap<Integer, Equipo> getMapEquipos() {
        return mapEquipos;
    }

    public static void setMapEquipos(HashMap<Integer, Equipo> mapEquipos) {
        Prueba_deportiva_DetalleDet.mapEquipos = mapEquipos;
    }

    public static HashMap<Integer, Prueba_deportiva> getMapPruebas() {
        return mapPruebas;
    }

    public static void setMapPruebas(HashMap<Integer, Prueba_deportiva> mapPruebas) {
        Prueba_deportiva_DetalleDet.mapPruebas = mapPruebas;
    }

    public EquipoDet getEquipo() {
        return equipo;
    }

    public void setEquipo(EquipoDet equipo) {
        this.equipo = equipo;
    }

    public Prueba_deportivaDet getPrueba() {
        return prueba;
    }

    public void setPrueba(Prueba_deportivaDet prueba) {
        this.prueba = prueba;
    }

}
