package bd;

import bd.base.Usuario_appBase;
public class Usuario_app extends Usuario_appBase {

	public Usuario_app() {
	}

	public Usuario_app(Usuario_app usuario_app) {
		super(usuario_app);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof bd.Usuario_app))
			return false;
		return ((bd.Usuario_app) obj).getId().equals(this.getId());
	}

	@Override
	public int hashCode() {
		return (int) this.id;
	}
}