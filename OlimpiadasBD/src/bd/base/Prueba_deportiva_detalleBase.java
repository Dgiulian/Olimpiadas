package bd.base;
public class Prueba_deportiva_detalleBase {

	public Integer id = 0;
	public Integer id_equipo = 0;
	public String resultado = "";

	public Prueba_deportiva_detalleBase() {
	}

	public Prueba_deportiva_detalleBase(
			Prueba_deportiva_detalleBase prueba_deportiva_detallebase) {
		this.id = prueba_deportiva_detallebase.getId();
		this.id_equipo = prueba_deportiva_detallebase.getId_equipo();
		this.resultado = prueba_deportiva_detallebase.getResultado();
	}

	public Integer getId() {
		return this.id;
	}

	public Prueba_deportiva_detalleBase setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getId_equipo() {
		return this.id_equipo;
	}

	public Prueba_deportiva_detalleBase setId_equipo(Integer id_equipo) {
		this.id_equipo = id_equipo;
		return this;
	}

	public String getResultado() {
		return this.resultado;
	}

	public Prueba_deportiva_detalleBase setResultado(String resultado) {
		this.resultado = resultado;
		return this;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof bd.base.Prueba_deportiva_detalleBase))
			return false;
		return ((bd.base.Prueba_deportiva_detalleBase) obj).getId().equals(
				this.getId());
	}

	@Override
	public int hashCode() {
		return (int) this.id;
	}
}