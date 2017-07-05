package bd.base;
public class DelegacionBase {

	public Integer id = 0;
	public String nombre = "";
	public String observaciones = "";

	public DelegacionBase() {
	}

	public DelegacionBase(DelegacionBase delegacionbase) {
		this.id = delegacionbase.getId();
		this.nombre = delegacionbase.getNombre();
		this.observaciones = delegacionbase.getObservaciones();
	}

	public Integer getId() {
		return this.id;
	}

	public DelegacionBase setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getNombre() {
		return this.nombre;
	}

	public DelegacionBase setNombre(String nombre) {
		this.nombre = nombre;
		return this;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public DelegacionBase setObservaciones(String observaciones) {
		this.observaciones = observaciones;
		return this;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof bd.base.DelegacionBase))
			return false;
		return ((bd.base.DelegacionBase) obj).getId().equals(this.getId());
	}

	@Override
	public int hashCode() {
		return (int) this.id;
	}
}