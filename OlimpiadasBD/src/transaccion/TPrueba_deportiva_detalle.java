package transaccion;

import bd.Prueba_deportiva_detalle;
import java.util.List;
public class TPrueba_deportiva_detalle
		extends
			TransaccionBase<Prueba_deportiva_detalle> {

	@Override
	public List<Prueba_deportiva_detalle> getList() {
		return super.getList("select * from prueba_deportiva_detalle ");
	}

	public Boolean actualizar(Prueba_deportiva_detalle prueba_deportiva_detalle) {
		return super.actualizar(prueba_deportiva_detalle, "id");
	}

	public Prueba_deportiva_detalle getById(Integer id) {
		String query = String
				.format("select * from prueba_deportiva_detalle where prueba_deportiva_detalle.id = %d ",
						id);
		return super.getById(query);
	}

    public boolean eliminar(Integer id_prueba) {
        String query = String.format("delete from prueba_deportiva_detalle where id_prueba = %d",id_prueba);
        boolean todoOk;
        conexion.conectarse();
        todoOk = conexion.ejecutarSQL(query);        
        conexion.desconectarse();
        return todoOk;
    }
//    public static boolean contiene(List<Equipo> lista,Integer id_equipo){
//        boolean existe = false;
//        if(lista==null) return false;
//        for(Equipo equipo:lista){
//            if (equipo.getId_equipo().equals(id_equipo)) {
//                existe =  true; 
//                break;
//            }
//        }
//        /*if (lista.stream().anyMatch((equipo_detalle) -> (equipo_detalle.getId_jugador().equals(id_jugador)))) {
//            return true;
//        }*/
//        return existe;
//    }
    
    
}