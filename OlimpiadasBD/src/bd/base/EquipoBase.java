package bd.base;
public class EquipoBase {

	public Integer id = 0;
	public String nombre = "";
	public Integer id_delegacion = 0;
	public String observaciones = "";

	public EquipoBase() {
	}

	public EquipoBase(EquipoBase equipobase) {
		this.id = equipobase.getId();
		this.nombre = equipobase.getNombre();
		this.id_delegacion = equipobase.getId_delegacion();
		this.observaciones = equipobase.getObservaciones();
	}

	public Integer getId() {
		return this.id;
	}

	public EquipoBase setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getNombre() {
		return this.nombre;
	}

	public EquipoBase setNombre(String nombre) {
		this.nombre = nombre;
		return this;
	}

	public Integer getId_delegacion() {
		return this.id_delegacion;
	}

	public EquipoBase setId_delegacion(Integer id_delegacion) {
		this.id_delegacion = id_delegacion;
		return this;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public EquipoBase setObservaciones(String observaciones) {
		this.observaciones = observaciones;
		return this;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof bd.base.EquipoBase))
			return false;
		return ((bd.base.EquipoBase) obj).getId().equals(this.getId());
	}

	@Override
	public int hashCode() {
		return (int) this.id;
	}
}