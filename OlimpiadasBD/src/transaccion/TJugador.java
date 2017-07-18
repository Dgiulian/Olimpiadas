package transaccion;

import bd.Jugador;
import java.util.List;
public class TJugador extends TransaccionBase<Jugador> {

	@Override
	public List<Jugador> getList() {
            return super.getList("select * from jugador ");
	}

	public Boolean actualizar(Jugador jugador) {
            return super.actualizar(jugador, "id");
	}

	public Jugador getById(Integer id) {
            String query = String.format(
                            "select * from jugador where jugador.id = %d ", id);
            return super.getById(query);
	}

    public List<Jugador> getById_delegacion(Integer id_delegacion) {
        String query =  String.format("select * from jugador where jugador.id_delegacion = %d ", id_delegacion);        
        return this.getList(query);
    }
}