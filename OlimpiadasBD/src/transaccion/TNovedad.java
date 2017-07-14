package transaccion;

import bd.Novedad;
import java.util.List;
public class TNovedad extends TransaccionBase<Novedad> {

	@Override
	public List<Novedad> getList() {
		return super.getList("select * from novedad ");
	}

	public Boolean actualizar(Novedad novedad) {
		return super.actualizar(novedad, "id");
	}

	public Novedad getById(Integer id) {
		String query = String.format(
				"select * from novedad where novedad.id = %d ", id);
		return super.getById(query);
	}
}