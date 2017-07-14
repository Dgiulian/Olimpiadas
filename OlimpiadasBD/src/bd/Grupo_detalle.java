package bd;

import bd.base.Grupo_detalleBase;
public class Grupo_detalle extends Grupo_detalleBase {

	public Grupo_detalle() {
	}

	public Grupo_detalle(Grupo_detalle grupo_detalle) {
		super(grupo_detalle);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof bd.Grupo_detalle))
			return false;
		return ((bd.Grupo_detalle) obj).getId().equals(this.getId());
	}

	@Override
	public int hashCode() {
		return (int) this.id;
	}
}