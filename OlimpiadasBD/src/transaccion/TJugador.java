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

    public Jugador getByDni(String documento) {
        String query = String.format(
                "select * from jugador where jugador.dni = %s ", documento);
        return super.getById(query);
    }

    public Jugador getById(Integer id) {
        String query = String.format(
                "select * from jugador where jugador.id = %d ", id);
        return super.getById(query);
    }

    public Jugador getByUsername(String username) {
        String query = "select jugador.* "
                + "from jugador inner join usuario_app on jugador.dni=usuario_app.documento "
                + "where usuario_app.username= '" + username + "'";
        System.out.println(query);
        return super.getById(query);
    }

    public List<Jugador> getById_delegacion(Integer id_delegacion) {
        String query = String.format("select * from jugador where jugador.id_delegacion = %d ", id_delegacion);
        return this.getList(query);
    }
}
