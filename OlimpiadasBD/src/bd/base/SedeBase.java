package bd.base;
public class SedeBase {

	public Integer id = 0;
	public String nombre = "";
	public String direccion = "";
	public String telefono = "";
	public String observaciones = "";
	public String imagen = "";

	public SedeBase() {
	}

	public SedeBase(SedeBase sedebase) {
		this.id = sedebase.getId();
		this.nombre = sedebase.getNombre();
		this.direccion = sedebase.getDireccion();
		this.telefono = sedebase.getTelefono();
		this.observaciones = sedebase.getObservaciones();
		this.imagen = sedebase.getImagen();
	}

	public Integer getId() {
		return this.id;
	}

	public SedeBase setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getNombre() {
		return this.nombre;
	}

	public SedeBase setNombre(String nombre) {
		this.nombre = nombre;
		return this;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public SedeBase setDireccion(String direccion) {
		this.direccion = direccion;
		return this;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public SedeBase setTelefono(String telefono) {
		this.telefono = telefono;
		return this;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public SedeBase setObservaciones(String observaciones) {
		this.observaciones = observaciones;
		return this;
	}

	public String getImagen() {
		return this.imagen;
	}

	public SedeBase setImagen(String imagen) {
		this.imagen = imagen;
		return this;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof bd.base.SedeBase))
			return false;
		return ((bd.base.SedeBase) obj).getId().equals(this.getId());
	}

	@Override
	public int hashCode() {
		return (int) this.id;
	}
}