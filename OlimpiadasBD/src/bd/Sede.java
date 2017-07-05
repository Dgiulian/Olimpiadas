package bd;

import bd.base.SedeBase;
public class Sede extends SedeBase {

	public Sede() {
	}

	public Sede(Sede sede) {
		super(sede);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof bd.Sede))
			return false;
		return ((bd.Sede) obj).getId().equals(this.getId());
	}

	@Override
	public int hashCode() {
		return (int) this.id;
	}
}