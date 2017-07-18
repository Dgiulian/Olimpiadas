package transaccion;

import bd.Equipo_detalle;
import conexion.TransaccionRS;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
public class TEquipo_detalle extends TransaccionBase<Equipo_detalle> {

    @Override
    public List<Equipo_detalle> getList() {
        return super.getList("select * from equipo_detalle ");
    }

    public Boolean actualizar(Equipo_detalle equipo_detalle) {
        return super.actualizar(equipo_detalle, "id");
    }

    public Equipo_detalle getById(Integer id) {
        String query = String.format("select * from equipo_detalle where equipo_detalle.id = %d ",id);
        return super.getById(query);
    }

    public List<Equipo_detalle> getById_equipo(Integer id_equipo) {
        String query = String.format("select * from equipo_detalle where equipo_detalle.id_equipo = %d ", id_equipo);
        return super.getList(query);
    }
    public static boolean contiene(List<Equipo_detalle> lista,Integer id_jugador){
        boolean existe = false;
        if(lista==null) return false;
        if (lista.stream().anyMatch((equipo_detalle) -> (equipo_detalle.getId_jugador().equals(id_jugador)))) {
            return true;
        }
        return false;
    }
    public boolean eliminar(Integer id_equipo){
        boolean todoOk;
        conexion.conectarse();
        String query = String.format("delete from equipo_detale where id_equipo = %d",id_equipo);
        todoOk = conexion.ejecutarSQL(query);
        conexion.desconectarse();
        return todoOk;
    }
        
}