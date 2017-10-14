/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transaccion_vista;

import bd.vista.MedalleroDelegacion;
import java.util.List;
import transaccion.TransaccionBase;

/**
 *
 * @author JuanMa
 */
public class TMedalleroDelegacion extends TransaccionBase<MedalleroDelegacion> {

    public List<MedalleroDelegacion> recuperar_medallero_delegacion() {

        String query = "select delegacion.nombre, tabla_oro.oro, tabla_plata.plata, tabla_bronce.bronce\n"
                + "from ((delegacion left join tabla_oro on tabla_oro.id=delegacion.id) left join tabla_bronce on tabla_bronce.id=delegacion.id) left join tabla_plata on tabla_plata.id=delegacion.id\n"
                + "order by oro desc, plata desc, bronce desc";
        return this.getList(query);

    }

    public MedalleroDelegacion recuperar_medallero_delegacion(int id_delegacion) {

        String query = "select delegacion.nombre, tabla_oro.oro, tabla_plata.plata, tabla_bronce.bronce\n"
                + "from ((delegacion left join tabla_oro on tabla_oro.id=delegacion.id) left join tabla_bronce on tabla_bronce.id=delegacion.id) left join tabla_plata on tabla_plata.id=delegacion.id\n"
                + " where delegacion.id=" + id_delegacion
                + " order by oro desc, plata desc, bronce desc";
        List<MedalleroDelegacion> lista = this.getList(query);
        if (lista != null) {
            return lista.get(0);
        } else {
            return null;
        }

    }

    public List<MedalleroDelegacion> recuperar_medallero_categoria(int id_categoria) {
        String query = "select delegacion.nombre, equipo.nombre as equipo, prueba_deportiva_detalle.medalla\n"
                + "from (((prueba_deportiva inner join prueba_deportiva_detalle on prueba_deportiva.id=prueba_deportiva_detalle.id_prueba) \n"
                + "inner join categoria on categoria.id=prueba_deportiva.id_categoria) \n"
                + "inner join equipo on equipo.id=prueba_deportiva_detalle.id_equipo) \n"
                + "inner join delegacion on delegacion.id=equipo.id_delegacion\n"
                + "where (prueba_deportiva_detalle.medalla='oro' or prueba_deportiva_detalle.medalla='plata' or prueba_deportiva_detalle.medalla='bronce')\n"
                + "and prueba_deportiva.id_categoria=" + id_categoria
                + " order by CHAR_LENGTH(prueba_deportiva_detalle.medalla)";
        return this.getList(query);

    }

    @Override
    public List<MedalleroDelegacion> getList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
