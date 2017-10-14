/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transaccion_vista;

import bd.Categoria;
import bd.Equipo;
import bd.Grupo;
import bd.detalle.EquipoDet;
import bd.detalle.GrupoDet;
import bd.vista.MedalleroDelegacion;
import bd.vista.MedalleroGrupoVista;
import java.util.ArrayList;
import java.util.List;
import transaccion.TCategoria;
import transaccion.TEquipo;
import transaccion.TGrupo;
import transaccion.TransaccionBase;

/**
 *
 * @author JuanMa
 */
public class TMedalleroGrupo extends TransaccionBase<MedalleroGrupoVista> {

    public void recuperar_medallero(int id_grupo) {

        Grupo g = new TGrupo().getById(id_grupo);
        GrupoDet grupo = new GrupoDet(g);
        Categoria categoria = new TCategoria().getById(grupo.getId_categoria());

    }

    public List<MedalleroGrupoVista> getMedallero(Grupo grupo) {

        Categoria categoria = new TCategoria().getById(grupo.getId_categoria());
        String order_by = "";
        if (categoria.getOrden_puntaje() == 1) {
            order_by = "asc";
        } else {
            order_by = "desc";
        }
        //si es por puntos la categoria
        if (categoria.getTipo_puntaje() == 1) {
            List<MedalleroGrupoVista> list = this.getMedalleroPuntos(grupo.getId(), order_by);
            List<MedalleroGrupoVista> list_definitiva = new ArrayList<MedalleroGrupoVista>();
            for (MedalleroGrupoVista medallero : list) {
                Equipo equipo = new TEquipo().getById(medallero.getId_equipo());
                EquipoDet detalle = new EquipoDet(equipo);
                detalle.setearJugadores();
                if (detalle.getJugadores().size() == 1) {
                    medallero.setNombre(detalle.getJugadores().get(0).getJugador().getNombre_apellido());
                }
                list_definitiva.add(medallero);
            }
            return list_definitiva;
        }
        //si es por tiempos la categoria
        if (categoria.getTipo_puntaje() == 2) {
            List<MedalleroGrupoVista> list =  this.getMedalleroTiempos(grupo.getId(), order_by);
            List<MedalleroGrupoVista> list_definitiva = new ArrayList<MedalleroGrupoVista>();
            for (MedalleroGrupoVista medallero : list) {
                Equipo equipo = new TEquipo().getById(medallero.getId_equipo());
                EquipoDet detalle = new EquipoDet(equipo);
                detalle.setearJugadores();
                if (detalle.getJugadores().size() == 1) {
                    medallero.setNombre(detalle.getJugadores().get(0).getJugador().getNombre_apellido());
                }
                list_definitiva.add(medallero);
            }
            return list_definitiva;
        }

        return null;

    }

    public List<MedalleroGrupoVista> getMedalleroPuntos(int id_grupo, String order_by) {
        String query = " select tabla.*, (tabla.afavor-tabla.encontra) as diferencia\n"
                + "from (\n"
                + "select delegacion.nombre as delegacion, equipo.nombre,  prueba_deportiva_detalle.id_equipo, count(*) as pj,  CAST(sum(prueba_deportiva_detalle.puntos) AS signed integer)  as resultado, CAST(sum(prueba_deportiva_detalle.resultado) AS signed integer) as afavor, CAST(sum(prueba_deportiva_detalle.resultado_encontra) AS signed integer) as encontra \n"
                + "from (((prueba_deportiva_detalle  inner join prueba_deportiva on prueba_deportiva.id=prueba_deportiva_detalle.id_prueba) inner join grupo on grupo.id=prueba_deportiva.id_grupo) inner join equipo on equipo.id=prueba_deportiva_detalle.id_equipo) inner join delegacion on delegacion.id=equipo.id_delegacion   \n"
                + "where prueba_deportiva.id_grupo= " + id_grupo + " \n"
                + "group by prueba_deportiva_detalle.id_equipo ) as tabla\n"
                + "order by resultado " + order_by + ", diferencia " + order_by;

        String query2 = "select gr.nombre as delegacion, gr.nombre,  gr.id_equipo, tabla_grupo.pj, tabla_grupo.resultado, tabla_grupo.afavor, tabla_grupo.encontra, tabla_grupo.diferencia\n"
                + "from \n"
                + "(select delegacion.nombre as delegacion, equipo.nombre,  grupo_detalle.id_equipo\n"
                + "from ((grupo inner join grupo_detalle on grupo.id=grupo_detalle.id_grupo) inner join equipo on equipo.id=grupo_detalle.id_equipo) inner join delegacion on delegacion.id=equipo.id_delegacion\n"
                + "where grupo.id=" + id_grupo + ") as gr left join \n"
                + "(select tabla.*, (tabla.afavor-tabla.encontra) as diferencia\n"
                + "from (\n"
                + "select prueba_deportiva_detalle.id_equipo, count(prueba_deportiva.id_estado) as pj,  CAST(sum(prueba_deportiva_detalle.puntos) AS signed integer)  as resultado, CAST(sum(prueba_deportiva_detalle.resultado) AS signed integer) as afavor, CAST(sum(prueba_deportiva_detalle.resultado_encontra) AS signed integer) as encontra \n"
                + "from ((prueba_deportiva_detalle  inner join prueba_deportiva on prueba_deportiva.id=prueba_deportiva_detalle.id_prueba)  inner join equipo on equipo.id=prueba_deportiva_detalle.id_equipo) inner join delegacion on delegacion.id=equipo.id_delegacion\n"
                + "where prueba_deportiva.id_grupo= " + id_grupo + " and prueba_deportiva.id_estado=3\n"
                + "group by prueba_deportiva_detalle.id_equipo ) as tabla\n"
                + "order by resultado " + order_by + ", diferencia " + order_by + ") as tabla_grupo on gr.id_equipo=tabla_grupo.id_equipo\n"
                + "order by resultado " + order_by + ", diferencia " + order_by;
        System.out.println("query: " + query2);
        return this.getList(query2);
    }

    public List<MedalleroGrupoVista> getMedalleroTiempos(int id_grupo, String order_by) {
        String query = " select delegacion.nombre as delegacion, equipo.nombre,  prueba_deportiva_detalle.id_equipo,CAST(sum(prueba_deportiva_detalle.puntos) AS UNSIGNED)  as resultado, prueba_deportiva_detalle.resultado as tiempo "
                + " from (((prueba_deportiva_detalle  inner join prueba_deportiva on prueba_deportiva.id=prueba_deportiva_detalle.id_prueba) inner join grupo on grupo.id=prueba_deportiva.id_grupo) inner join equipo on equipo.id=prueba_deportiva_detalle.id_equipo) inner join delegacion on delegacion.id=equipo.id_delegacion   "
                + " where prueba_deportiva.id_grupo= " + id_grupo
                + " group by prueba_deportiva_detalle.id_equipo "
                + " order by tiempo " + order_by;
        System.out.println("query: " + query);
        return this.getList(query);
    }

    @Override
    public List<MedalleroGrupoVista> getList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void main(String[] args) {

        TMedalleroDelegacion tm = new TMedalleroDelegacion();
        List<MedalleroDelegacion> lista = tm.recuperar_medallero_categoria(56);

    }

}
