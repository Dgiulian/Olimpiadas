package transaccion;

import bd.Delegacion;
import java.util.List;
public class TDelegacion extends TransaccionBase<Delegacion> {

	@Override
	public List<Delegacion> getList() {
		return super.getList("select * from delegacion ");
	}

	public Boolean actualizar(Delegacion delegacion) {
		return super.actualizar(delegacion, "id");
	}

	public Delegacion getById(Integer id) {
		String query = String.format(
				"select * from delegacion where delegacion.id = %d ", id);
		return super.getById(query);
	}
}