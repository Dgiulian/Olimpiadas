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
import bd.Sede;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import transaccion.TCategoria;
import transaccion.TDeporte;
import transaccion.TEquipo;
import transaccion.TGrupo;
import transaccion.TSede;

/**
 *
 * @author JuanMa
 */
public class Prueba_deportivaDet extends Prueba_deportiva {

    public static final HashMap<Integer, Deporte> mapDeportes = new TDeporte().getMap();
    public static final HashMap<Integer, Categoria> mapCategorias = new TCategoria().getMap();
    public static final HashMap<Integer, Grupo> mapGrupos = new TGrupo().getMap();
    public static final HashMap<Integer, Sede> mapSedes = new TSede().getMap();
    public static final String[] estados = {"", "En Agenda", "En Curso", "Finalizada", "Suspendida"};

    Deporte deporte;
    Categoria categoria;
    Grupo grupo;
    Sede sede;
    String estado = "";
    List<EquipoDet> equipos= new ArrayList<>();

    //Tipo prueba indica si es un partido o de multiples equipos
    int tipo_prueba = 0;

    public Prueba_deportivaDet(Prueba_deportiva prueba_deportiva) {
        super(prueba_deportiva);
        List<Equipo> list = new TEquipo().getById_prueba(prueba_deportiva.getId());
        if (list != null && list.size() == 2) {
            tipo_prueba = 1;
        } else {
            tipo_prueba = 2;
        }

        if (list != null) {
            for (Equipo c : list) {
                equipos.add(new EquipoDet(c));
            }
        }

        deporte = mapDeportes.get(prueba_deportiva.getId_deporte());
        categoria = mapCategorias.get(prueba_deportiva.getId_categoria());
        estado = estados[prueba_deportiva.getId_estado()];
        grupo = mapGrupos.get(prueba_deportiva.getId_grupo());
        sede = mapSedes.get(prueba_deportiva.getId_sede());

    }
}
