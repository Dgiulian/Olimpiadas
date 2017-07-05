package bd;

import bd.base.JugadoresBase;
public class Jugadores extends JugadoresBase {

	public Jugadores() {
	}

	public Jugadores(Jugadores jugadores) {
		super(jugadores);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof bd.Jugadores))
			return false;
		return ((bd.Jugadores) obj).getId().equals(this.getId());
	}

	@Override
	public int hashCode() {
		return (int) this.id;
	}
}