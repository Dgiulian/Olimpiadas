package bd;

import bd.base.JugadorBase;
public class Jugador extends JugadorBase {

	public Jugador() {
	}

	public Jugador(Jugador jugador) {
		super(jugador);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof bd.Jugador))
			return false;
		return ((bd.Jugador) obj).getId().equals(this.getId());
	}

	@Override
	public int hashCode() {
		return (int) this.id;
	}
}