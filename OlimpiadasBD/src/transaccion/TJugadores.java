package transaccion;

import bd.Jugadores;
import java.util.List;
public class TJugadores extends TransaccionBase<Jugadores> {

	@Override
	public List<Jugadores> getList() {
		return super.getList("select * from jugadores ");
	}

	public Boolean actualizar(Jugadores jugadores) {
		return super.actualizar(jugadores, "id");
	}

	public Jugadores getById(Integer id) {
		String query = String.format(
				"select * from jugadores where jugadores.id = %d ", id);
		return super.getById(query);
	}
}