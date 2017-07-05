package bd;

import bd.base.Prueba_deportivaBase;
public class Prueba_deportiva extends Prueba_deportivaBase {

	public Prueba_deportiva() {
	}

	public Prueba_deportiva(Prueba_deportiva prueba_deportiva) {
		super(prueba_deportiva);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof bd.Prueba_deportiva))
			return false;
		return ((bd.Prueba_deportiva) obj).getId().equals(this.getId());
	}

	@Override
	public int hashCode() {
		return (int) this.id;
	}
}