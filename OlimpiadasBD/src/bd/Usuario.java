package bd;

import bd.base.UsuarioBase;
public class Usuario extends UsuarioBase {

	public Usuario() {
	}

	public Usuario(Usuario usuario) {
		super(usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof bd.Usuario))
			return false;
		return ((bd.Usuario) obj).getId().equals(this.getId());
	}

	@Override
	public int hashCode() {
		return (int) this.id;
	}
}