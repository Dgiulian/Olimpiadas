package bd;

import bd.base.DeporteBase;
public class Deporte extends DeporteBase {

	public Deporte() {
	}

	public Deporte(Deporte deporte) {
		super(deporte);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof bd.Deporte))
			return false;
		return ((bd.Deporte) obj).getId().equals(this.getId());
	}

	@Override
	public int hashCode() {
		return (int) this.id;
	}
}