package bd;

import bd.base.NovedadBase;
public class Novedad extends NovedadBase {

	public Novedad() {
	}

	public Novedad(Novedad novedad) {
		super(novedad);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof bd.Novedad))
			return false;
		return ((bd.Novedad) obj).getId().equals(this.getId());
	}

	@Override
	public int hashCode() {
		return (int) this.id;
	}
}