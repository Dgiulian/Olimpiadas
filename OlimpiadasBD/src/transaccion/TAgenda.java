package transaccion;

import bd.Agenda;
import java.util.List;
public class TAgenda extends TransaccionBase<Agenda> {

	@Override
	public List<Agenda> getList() {
		return super.getList("select * from agenda ");
	}

	public Boolean actualizar(Agenda agenda) {
		return super.actualizar(agenda, "id");
	}

	public Agenda getById(Integer id) {
		String query = String.format(
				"select * from agenda where agenda.id = %d ", id);
		return super.getById(query);
	}
}