package bd;

import bd.base.Tipo_usuarioBase;
public class Tipo_usuario extends Tipo_usuarioBase {

	public Tipo_usuario() {
	}

	public Tipo_usuario(Tipo_usuario tipo_usuario) {
		super(tipo_usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof bd.Tipo_usuario))
			return false;
		return ((bd.Tipo_usuario) obj).getId().equals(this.getId());
	}

	@Override
	public int hashCode() {
		return (int) this.id;
	}
}