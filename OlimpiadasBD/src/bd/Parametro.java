package bd;

import bd.base.ParametroBase;
public class Parametro extends ParametroBase {

	public Parametro() {
	}

	public Parametro(Parametro parametro) {
		super(parametro);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof bd.Parametro))
			return false;
		return ((bd.Parametro) obj).getId().equals(this.getId());
	}

	@Override
	public int hashCode() {
		return (int) this.id;
	}
}