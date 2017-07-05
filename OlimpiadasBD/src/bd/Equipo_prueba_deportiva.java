package bd;

import bd.base.Equipo_prueba_deportivaBase;
public class Equipo_prueba_deportiva extends Equipo_prueba_deportivaBase {

	public Equipo_prueba_deportiva() {
	}

	public Equipo_prueba_deportiva(
			Equipo_prueba_deportiva equipo_prueba_deportiva) {
		super(equipo_prueba_deportiva);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof bd.Equipo_prueba_deportiva))
			return false;
		return ((bd.Equipo_prueba_deportiva) obj).getId().equals(this.getId());
	}

	@Override
	public int hashCode() {
		return (int) this.id;
	}
}