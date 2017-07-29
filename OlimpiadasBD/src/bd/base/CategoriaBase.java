package bd.base;
public class CategoriaBase {

	public Integer id = 0;
	public String nombre = "";
	public String nombre_corto = "";
	public String detalle = "";
	public String observacion = "";
	public Integer id_deporte = 0;
	public Integer tipo_puntaje = 0;
	public Integer orden_puntaje = 0;

	public CategoriaBase() {
	}

	public CategoriaBase(CategoriaBase categoriabase) {
		this.id = categoriabase.getId();
		this.nombre = categoriabase.getNombre();
		this.nombre_corto = categoriabase.getNombre_corto();
		this.detalle = categoriabase.getDetalle();
		this.observacion = categoriabase.getObservacion();
		this.id_deporte = categoriabase.getId_deporte();
		this.tipo_puntaje = categoriabase.getTipo_puntaje();
		this.orden_puntaje = categoriabase.getOrden_puntaje();
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

	public String getNombre_corto() {
		return this.nombre_corto;
	}

	public CategoriaBase setNombre_corto(String nombre_corto) {
		this.nombre_corto = nombre_corto;
		return this;
	}

	public String getDetalle() {
		return this.detalle;
	}

	public CategoriaBase setDetalle(String detalle) {
		this.detalle = detalle;
		return this;
	}

	public String getObservacion() {
		return this.observacion;
	}

	public CategoriaBase setObservacion(String observacion) {
		this.observacion = observacion;
		return this;
	}

	public Integer getId_deporte() {
		return this.id_deporte;
	}

	public CategoriaBase setId_deporte(Integer id_deporte) {
		this.id_deporte = id_deporte;
		return this;
	}

	public Integer getTipo_puntaje() {
		return this.tipo_puntaje;
	}

	public CategoriaBase setTipo_puntaje(Integer tipo_puntaje) {
		this.tipo_puntaje = tipo_puntaje;
		return this;
	}

	public Integer getOrden_puntaje() {
		return this.orden_puntaje;
	}

	public CategoriaBase setOrden_puntaje(Integer orden_puntaje) {
		this.orden_puntaje = orden_puntaje;
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