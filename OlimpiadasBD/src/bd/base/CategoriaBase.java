package bd.base;
public class CategoriaBase {

	public Integer id = 0;
	public String nombre = "";
	public String detalle = "";
	public Integer id_deporte = 0;
	public String observacion = "";

	public CategoriaBase() {
	}

	public CategoriaBase(CategoriaBase categoriabase) {
		this.id = categoriabase.getId();
		this.nombre = categoriabase.getNombre();
		this.detalle = categoriabase.getDetalle();
		this.id_deporte = categoriabase.getId_deporte();
		this.observacion = categoriabase.getObservacion();
	}

	public Integer getId() {
		return this.id;
	}

	public CategoriaBase setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getNombre() {
		return this.nombre;
	}

	public CategoriaBase setNombre(String nombre) {
		this.nombre = nombre;
		return this;
	}

	public String getDetalle() {
		return this.detalle;
	}

	public CategoriaBase setDetalle(String detalle) {
		this.detalle = detalle;
		return this;
	}

	public Integer getId_deporte() {
		return this.id_deporte;
	}

	public CategoriaBase setId_deporte(Integer id_deporte) {
		this.id_deporte = id_deporte;
		return this;
	}

	public String getObservacion() {
		return this.observacion;
	}

	public CategoriaBase setObservacion(String observacion) {
		this.observacion = observacion;
		return this;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof bd.base.CategoriaBase))
			return false;
		return ((bd.base.CategoriaBase) obj).getId().equals(this.getId());
	}

	@Override
	public int hashCode() {
		return (int) this.id;
	}
}