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
}