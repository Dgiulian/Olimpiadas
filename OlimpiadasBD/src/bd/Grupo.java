package bd;

import bd.base.GrupoBase;
public class Grupo extends GrupoBase {

	public Grupo() {
	}

	public Grupo(Grupo grupo) {
		super(grupo);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof bd.Grupo))
			return false;
		return ((bd.Grupo) obj).getId().equals(this.getId());
	}

	@Override
	public int hashCode() {
		return (int) this.id;
	}
}