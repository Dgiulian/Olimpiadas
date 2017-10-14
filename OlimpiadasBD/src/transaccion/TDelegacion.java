package transaccion;

import bd.Delegacion;
import bd.Jugador;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import utils.TFecha;

public class TDelegacion extends TransaccionBase<Delegacion> {

    @Override
    public List<Delegacion> getList() {
        return super.getList("select * from delegacion");
    }

    public Boolean actualizar(Delegacion delegacion) {
        return super.actualizar(delegacion, "id");
    }

    public Delegacion getById(Integer id) {
        String query = String.format(
                "select * from delegacion where delegacion.id = %d ", id);
        return super.getById(query);
    }

    public List<String> cargar_jugadores(int id_delegacion, String jugadores) {

        Gson gs = new Gson();
        List<String> errores = new ArrayList<String>();
        Type collectionType = new TypeToken<List<Jugador>>() {
        }.getType();
        List<Jugador> lista_entradas = gs.fromJson(jugadores, collectionType);
        Iterator it_entradas = lista_entradas.iterator();
        while (it_entradas.hasNext()) {
            Jugador jugador = (Jugador) it_entradas.next();

            String doc = jugador.getDni().replace(".", "");
            jugador.setDni(doc);
            jugador.setFecha_nacimiento(TFecha.formatearFechaVistaBd(jugador.getFecha_nacimiento()));
            jugador.setId_delegacion(id_delegacion);
            if (new TJugador().alta(jugador) <= 0) {
                errores.add(jugador.getNombre_apellido());
            }
        }

        return errores;

    }
}
