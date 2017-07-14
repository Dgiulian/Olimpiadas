package bd;

import bd.base.Prueba_deportiva_detalleBase;
public class Prueba_deportiva_detalle extends Prueba_deportiva_detalleBase {

	public Prueba_deportiva_detalle() {
	}

	public Prueba_deportiva_detalle(
			Prueba_deportiva_detalle prueba_deportiva_detalle) {
		super(prueba_deportiva_detalle);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof bd.Prueba_deportiva_detalle))
			return false;
		return ((bd.Prueba_deportiva_detalle) obj).getId().equals(this.getId());
	}

	@Override
	public int hashCode() {
		return (int) this.id;
	}
}