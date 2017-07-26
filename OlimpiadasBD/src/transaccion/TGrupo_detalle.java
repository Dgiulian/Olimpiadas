package transaccion;

import bd.Grupo_detalle;
import java.util.List;
public class TGrupo_detalle extends TransaccionBase<Grupo_detalle> {

    @Override
    public List<Grupo_detalle> getList() {
        return super.getList("select * from grupo_detalle ");
    }

    public Boolean actualizar(Grupo_detalle grupo_detalle) {
        return super.actualizar(grupo_detalle, "id");
    }

    public Grupo_detalle getById(Integer id) {
        String query = String.format(
                        "select * from grupo_detalle where grupo_detalle.id = %d ", id);
        return super.getById(query);
    }

    public boolean eliminar(Integer id_grupo) {
        conexion.conectarse();
        String query = String.format("delete from grupo_detalle where grupo_detalle.id_grupo = %d",id_grupo);
        boolean todoOk = conexion.ejecutarSQL(query);
        conexion.desconectarse();
        return todoOk;
    }
    public boolean contiene(List<Grupo_detalle> lista,Integer id_grupo){
        boolean existe = false;
        if(lista==null) return false;
        for(Grupo_detalle equipo_detalle:lista){
            if (equipo_detalle.getId_grupo().equals(id_grupo)) {
                existe =  true; 
                break;
            }
        }
        /*if (lista.stream().anyMatch((equipo_detalle) -> (equipo_detalle.getId_grupo().equals(id_grupo)))) {
            return true;
        }*/
        return existe;
    }

    public List<Grupo_detalle> getById_equipo(Integer id_equipo) {
        String query = String.format("select * from grupo_detalle where id_equipo = %d",id_equipo);
        return this.getList(query);
    }
}