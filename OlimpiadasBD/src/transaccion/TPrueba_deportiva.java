package transaccion;

import bd.Prueba_deportiva;
import java.util.List;

public class TPrueba_deportiva extends TransaccionBase<Prueba_deportiva> {

    @Override
    public List<Prueba_deportiva> getList() {
        return super.getList("select * from prueba_deportiva ");
    }

    public List<Prueba_deportiva> getListUsuario(String usuario) {
        return super.getList("select prueba_deportiva.*\n"
                + "from (((usuario_app inner join jugador on jugador.dni=usuario_app.documento) inner join equipo_detalle on jugador.id= equipo_detalle.id_jugador)\n"
                + "inner join prueba_deportiva_detalle on prueba_deportiva_detalle.id_equipo=equipo_detalle.id_equipo)\n"
                + "inner join prueba_deportiva on prueba_deportiva.id=prueba_deportiva_detalle.id_prueba\n"
                + "where usuario_app.username='" + usuario + "' \n"
                + " order by prueba_deportiva.fecha, prueba_deportiva.hora");

    }

    public Boolean actualizar(Prueba_deportiva prueba_deportiva) {
        return super.actualizar(prueba_deportiva, "id");
    }

    public Prueba_deportiva getById(Integer id) {
        String query = String
                .format("select * from prueba_deportiva where prueba_deportiva.id = %d ",
                        id);
        return super.getById(query);
    }

    public List<Prueba_deportiva> getList_batch(String fecha, String hora_desde, String hora_hasta) {
        String sql = " select * "
                + " from prueba_deportiva "
                + " where prueba_deportiva.fecha='" + fecha + "' and prueba_deportiva.hora<='" + hora_hasta + "' and prueba_deportiva.hora>='" + hora_desde + "'"
                + " order by fecha, hora";
        System.out.println(sql);
        return this.getList(sql);

    }

    public List<Prueba_deportiva> getList_filtro(String fecha, int id_deporte, int id_categoria, int id_delegacion) {

        String where = "";
        if (fecha != null && !fecha.equalsIgnoreCase("")) {
            where = " and prueba_deportiva.fecha='" + fecha + "'";
        }
        if (id_deporte != 0) {
            where = where + " and prueba_deportiva.id_deporte=" + id_deporte;
        }
        if (id_categoria != 0) {
            where = where + " and prueba_deportiva.id_categoria=" + id_categoria;
        }
        if (id_delegacion != 0) {
            where = where + " and equipo.id_delegacion=" + id_delegacion;
        }

        String query = "select DISTINCT prueba_deportiva.*\n"
                + "from ((prueba_deportiva inner join prueba_deportiva_detalle on prueba_deportiva.id=prueba_deportiva_detalle.id_prueba) inner join equipo on equipo.id=prueba_deportiva_detalle.id_equipo)\n"
                + "inner join delegacion on delegacion.id=equipo.id_delegacion"
                + " where true=true " + where 
                + " order by prueba_deportiva.fecha, prueba_deportiva.hora"; 
        
        return getList(query);

    }

}
