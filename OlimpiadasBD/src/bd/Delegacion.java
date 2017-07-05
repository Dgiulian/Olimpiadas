package bd;

import bd.base.DelegacionBase;
public class Delegacion extends DelegacionBase {

	public Delegacion() {
	}

	public Delegacion(Delegacion delegacion) {
		super(delegacion);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof bd.Delegacion))
			return false;
		return ((bd.Delegacion) obj).getId().equals(this.getId());
	}

	@Override
	public int hashCode() {
		return (int) this.id;
	}
}