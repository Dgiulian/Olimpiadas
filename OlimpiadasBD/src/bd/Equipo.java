package bd;

import bd.base.EquipoBase;
public class Equipo extends EquipoBase {

	public Equipo() {
	}

	public Equipo(Equipo equipo) {
		super(equipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof bd.Equipo))
			return false;
		return ((bd.Equipo) obj).getId().equals(this.getId());
	}

	@Override
	public int hashCode() {
		return (int) this.id;
	}
}