package bd;

import bd.base.AgendaBase;
public class Agenda extends AgendaBase {

	public Agenda() {
	}

	public Agenda(Agenda agenda) {
		super(agenda);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof bd.Agenda))
			return false;
		return ((bd.Agenda) obj).getId().equals(this.getId());
	}

	@Override
	public int hashCode() {
		return (int) this.id;
	}
}