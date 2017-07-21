package transaccion;

import bd.Equipo;
import java.util.List;
public class TEquipo extends TransaccionBase<Equipo> {

	@Override
	public List<Equipo> getList() {
		return super.getList("select * from equipo ");
	}

	public Boolean actualizar(Equipo equipo) {
		return super.actualizar(equipo, "id");
	}

	public Equipo getById(Integer id) {
		String query = String.format(
				"select * from equipo where equipo.id = %d ", id);
		return super.getById(query);
	}

    public List<Equipo> getById_grupo(Integer id_grupo) {
        String query = String.format("select equipo.* from equipo,grupo_detalle " +
                                     " where grupo_detalle.id_equipo = equipo.id " +
                                     "   and grupo_detalle.id_grupo = %d;",id_grupo);
        return this.getList(query);
    }

    public List<Equipo> getById_prueba(Integer id_prueba) {
        String query = String.format("select equipo.* from equipo,prueba_deportiva_detalle " +
                                     " where prueba_deportiva_detalle.id_equipo = equipo.id " +
                                     "   and prueba_deportiva_detalle.id_prueba = %d;",id_prueba);
        System.out.println(query);
        return this.getList(query);
    }
}