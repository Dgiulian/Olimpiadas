package transaccion;

import bd.Equipo_prueba_deportiva;
import java.util.List;
public class TEquipo_prueba_deportiva
		extends
			TransaccionBase<Equipo_prueba_deportiva> {

	@Override
	public List<Equipo_prueba_deportiva> getList() {
		return super.getList("select * from equipo_prueba_deportiva ");
	}

	public Boolean actualizar(Equipo_prueba_deportiva equipo_prueba_deportiva) {
		return super.actualizar(equipo_prueba_deportiva, "id");
	}

	public Equipo_prueba_deportiva getById(Integer id) {
		String query = String
				.format("select * from equipo_prueba_deportiva where equipo_prueba_deportiva.id = %d ",
						id);
		return super.getById(query);
	}
}