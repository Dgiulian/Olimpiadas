package transaccion;

import bd.Sede;
import java.util.List;
public class TSede extends TransaccionBase<Sede> {

	@Override
	public List<Sede> getList() {
		return super.getList("select * from sede ");
	}

	public Boolean actualizar(Sede sede) {
		return super.actualizar(sede, "id");
	}

	public Sede getById(Integer id) {
		String query = String.format("select * from sede where sede.id = %d ",
				id);
		return super.getById(query);
	}
}