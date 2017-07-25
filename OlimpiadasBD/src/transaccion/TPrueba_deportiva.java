package transaccion;

import bd.Prueba_deportiva;
import java.util.List;
public class TPrueba_deportiva extends TransaccionBase<Prueba_deportiva> {

	@Override
	public List<Prueba_deportiva> getList() {
		return super.getList("select * from prueba_deportiva ");
	}

	public Boolean actualizar(Prueba_deportiva prueba_deportiva) {
		return super.actualizar(prueba_deportiva, "id");
	}

	public Prueba_deportiva getById(Integer id) {
		String query = String
				.format("select * from prueba_deportiva where prueba_deportiva.id = %d ",
						id);
		return super.getById(query);
	}
        
}