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
import java.util.HashMap;
import java.util.List;
import transaccion.TCategoria;
import transaccion.TDeporte;
import transaccion.TEquipo;
import transaccion.TGrupo;

/**
 *
 * @author JuanMa
 */
public class Prueba_deportivaDet extends Prueba_deportiva {

    public  final HashMap<Integer, Deporte> mapDeportes = new TDeporte().getMap();
    public  final HashMap<Integer, Categoria> mapCategorias = new TCategoria().getMap();
    public  final HashMap<Integer, Grupo> mapGrupos = new TGrupo().getMap();
    public  final String[] estados = {"", "En Agenda", "En Curso", "Finalizada", "Suspendida"};

    Deporte deporte;
    Categoria categoria;
    Grupo grupo;
    String estado = "";
    List<Equipo> detalle;

    public Prueba_deportivaDet(Prueba_deportiva prueba_deportiva) {
        super(prueba_deportiva);
        detalle = new TEquipo().getById_prueba(prueba_deportiva.getId());
        deporte = mapDeportes.get(prueba_deportiva.getId_deporte());
        categoria = mapCategorias.get(prueba_deportiva.getId_categoria());
        estado = estados[prueba_deportiva.getId_estado()];
        grupo= mapGrupos.get(prueba_deportiva.getId_grupo());
    }
}
