package bd.base;
public class DelegacionBase {

	public Integer id = 0;
	public String nombre = "";
	public String nombre_corto = "";
	public String observaciones = "";
	public String imagen = "";
	public String color = "";

	public DelegacionBase() {
	}

	public DelegacionBase(DelegacionBase delegacionbase) {
		this.id = delegacionbase.getId();
		this.nombre = delegacionbase.getNombre();
		this.nombre_corto = delegacionbase.getNombre_corto();
		this.observaciones = delegacionbase.getObservaciones();
		this.imagen = delegacionbase.getImagen();
		this.color = delegacionbase.getColor();
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

	public String getNombre_corto() {
		return this.nombre_corto;
	}

	public DelegacionBase setNombre_corto(String nombre_corto) {
		this.nombre_corto = nombre_corto;
		return this;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public DelegacionBase setObservaciones(String observaciones) {
		this.observaciones = observaciones;
		return this;
	}

	public String getImagen() {
		return this.imagen;
	}

	public DelegacionBase setImagen(String imagen) {
		this.imagen = imagen;
		return this;
	}

	public String getColor() {
		return this.color;
	}

	public DelegacionBase setColor(String color) {
		this.color = color;
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