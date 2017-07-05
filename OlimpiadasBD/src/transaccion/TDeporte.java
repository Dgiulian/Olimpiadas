package transaccion;

import bd.Deporte;
import java.util.List;
public class TDeporte extends TransaccionBase<Deporte> {

	@Override
	public List<Deporte> getList() {
		return super.getList("select * from deporte ");
	}

	public Boolean actualizar(Deporte deporte) {
		return super.actualizar(deporte, "id");
	}

	public Deporte getById(Integer id) {
		String query = String.format(
				"select * from deporte where deporte.id = %d ", id);
		return super.getById(query);
	}
}