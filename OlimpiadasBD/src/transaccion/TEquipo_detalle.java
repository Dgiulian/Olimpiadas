package transaccion;

import bd.Equipo_detalle;
import java.util.List;
public class TEquipo_detalle extends TransaccionBase<Equipo_detalle> {

	@Override
	public List<Equipo_detalle> getList() {
		return super.getList("select * from equipo_detalle ");
	}

	public Boolean actualizar(Equipo_detalle equipo_detalle) {
		return super.actualizar(equipo_detalle, "id");
	}

	public Equipo_detalle getById(Integer id) {
		String query = String.format(
				"select * from equipo_detalle where equipo_detalle.id = %d ",
				id);
		return super.getById(query);
	}
}