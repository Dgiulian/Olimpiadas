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
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import transaccion.TCategoria;
import transaccion.TDeporte;
import transaccion.TEquipo;
import transaccion.TGrupo;
import transaccion.TPrueba_deportiva_detalle;
import transaccion.TSede;

/**
 *
 * @author JuanMa
 */
public class Prueba_deportivaDet extends Prueba_deportiva {

    public static final HashMap<Integer, Deporte> mapDeportes = new TDeporte().getMap();
    public static HashMap<Integer, Categoria> mapCategorias;
    public static HashMap<Integer, Grupo> mapGrupos;
    public static final HashMap<Integer, Sede> mapSedes = new TSede().getMap();
    public static final String[] estados = {"", "En Agenda", "En Curso", "Finalizada", "Suspendida"};

    Deporte deporte;
    Categoria categoria;
    Grupo grupo;
    Sede sede;
    String estado = "";
    List<EquipoDet> equipos = new ArrayList();
    List<Prueba_deportiva_DetalleDet> detalle_prueba = new ArrayList();

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

        mapCategorias = new TCategoria().getMap();
        mapGrupos = new TGrupo().getMap();

        deporte = mapDeportes.get(prueba_deportiva.getId_deporte());
        categoria = mapCategorias.get(prueba_deportiva.getId_categoria());
        estado = estados[prueba_deportiva.getId_estado()];
        grupo = mapGrupos.get(prueba_deportiva.getId_grupo());
        sede = mapSedes.get(prueba_deportiva.getId_sede());

    }

    public void setearDetalle(boolean completo) {

        HashMap<String, String> filtro = new HashMap<>();
        filtro.put("id_prueba", String.valueOf(this.getId()));
        List<Prueba_deportiva_detalle> list_detalle = new TPrueba_deportiva_detalle().getListFiltro(filtro);
        if (list_detalle != null) {
            for (Prueba_deportiva_detalle p : list_detalle) {
                this.detalle_prueba.add(new Prueba_deportiva_DetalleDet(p, completo));
            }
        }

    }

    public Deporte getDeporte() {
        return deporte;
    }

    public void setDeporte(Deporte deporte) {
        this.deporte = deporte;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<EquipoDet> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<EquipoDet> equipos) {
        this.equipos = equipos;
    }

    public int getTipo_prueba() {
        return tipo_prueba;
    }

    public void setTipo_prueba(int tipo_prueba) {
        this.tipo_prueba = tipo_prueba;
    }

    public List<Prueba_deportiva_DetalleDet> getDetalle_prueba() {
        return detalle_prueba;
    }

    public void setDetalle_prueba(List<Prueba_deportiva_DetalleDet> detalle_prueba) {
        this.detalle_prueba = detalle_prueba;
    }
    
    

}
