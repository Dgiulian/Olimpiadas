package transaccion;

import bd.Usuario_app;
import java.util.List;
public class TUsuario_app extends TransaccionBase<Usuario_app> {

	@Override
	public List<Usuario_app> getList() {
		return super.getList("select * from usuario_app ");
	}

	public Boolean actualizar(Usuario_app usuario_app) {
		return super.actualizar(usuario_app, "id");
	}

	public Usuario_app getById(Integer id) {
		String query = String.format(
				"select * from usuario_app where usuario_app.id = %d ", id);
		return super.getById(query);
	}
}