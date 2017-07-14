package transaccion;

import bd.Grupo;
import java.util.List;
public class TGrupo extends TransaccionBase<Grupo> {

	@Override
	public List<Grupo> getList() {
		return super.getList("select * from grupo ");
	}

	public Boolean actualizar(Grupo grupo) {
		return super.actualizar(grupo, "id");
	}

	public Grupo getById(Integer id) {
		String query = String.format(
				"select * from grupo where grupo.id = %d ", id);
		return super.getById(query);
	}
}