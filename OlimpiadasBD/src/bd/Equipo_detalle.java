package bd;

import bd.base.Equipo_detalleBase;
public class Equipo_detalle extends Equipo_detalleBase {

	public Equipo_detalle() {
	}

	public Equipo_detalle(Equipo_detalle equipo_detalle) {
		super(equipo_detalle);
	}

	@Override
	public boolean equals(Object obj) {
            if (obj == this)
                    return true;
            if (obj == null)
                    return false;
            if (!(obj instanceof bd.Equipo_detalle))
                    return false;
            return ((bd.Equipo_detalle) obj).getId().equals(this.getId());
	}

	@Override
	public int hashCode() {
		return (int) this.id;
	}
}