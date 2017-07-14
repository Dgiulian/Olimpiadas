package bd.base;
public class Prueba_deportivaBase {

	public Integer id = 0;
	public Integer id_deporte = 0;
	public Integer id_categoria = 0;
	public Integer id_grupo = 0;
	public Integer fecha = 0;
	public Integer hora = 0;
	public Integer tipo_puntaje = 0;
	public Integer orden_puntaje = 0;
	public Integer id_sede = 0;
	public String observaciones = "";

	public Prueba_deportivaBase() {
	}

	public Prueba_deportivaBase(Prueba_deportivaBase prueba_deportivabase) {
		this.id = prueba_deportivabase.getId();
		this.id_deporte = prueba_deportivabase.getId_deporte();
		this.id_categoria = prueba_deportivabase.getId_categoria();
		this.id_grupo = prueba_deportivabase.getId_grupo();
		this.fecha = prueba_deportivabase.getFecha();
		this.hora = prueba_deportivabase.getHora();
		this.tipo_puntaje = prueba_deportivabase.getTipo_puntaje();
		this.orden_puntaje = prueba_deportivabase.getOrden_puntaje();
		this.id_sede = prueba_deportivabase.getId_sede();
		this.observaciones = prueba_deportivabase.getObservaciones();
	}

	public Integer getId() {
		return this.id;
	}

	public Prueba_deportivaBase setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getId_deporte() {
		return this.id_deporte;
	}

	public Prueba_deportivaBase setId_deporte(Integer id_deporte) {
		this.id_deporte = id_deporte;
		return this;
	}

	public Integer getId_categoria() {
		return this.id_categoria;
	}

	public Prueba_deportivaBase setId_categoria(Integer id_categoria) {
		this.id_categoria = id_categoria;
		return this;
	}

	public Integer getId_grupo() {
		return this.id_grupo;
	}

	public Prueba_deportivaBase setId_grupo(Integer id_grupo) {
		this.id_grupo = id_grupo;
		return this;
	}

	public Integer getFecha() {
		return this.fecha;
	}

	public Prueba_deportivaBase setFecha(Integer fecha) {
		this.fecha = fecha;
		return this;
	}

	public Integer getHora() {
		return this.hora;
	}

	public Prueba_deportivaBase setHora(Integer hora) {
		this.hora = hora;
		return this;
	}

	public Integer getTipo_puntaje() {
		return this.tipo_puntaje;
	}

	public Prueba_deportivaBase setTipo_puntaje(Integer tipo_puntaje) {
		this.tipo_puntaje = tipo_puntaje;
		return this;
	}

	public Integer getOrden_puntaje() {
		return this.orden_puntaje;
	}

	public Prueba_deportivaBase setOrden_puntaje(Integer orden_puntaje) {
		this.orden_puntaje = orden_puntaje;
		return this;
	}

	public Integer getId_sede() {
		return this.id_sede;
	}

	public Prueba_deportivaBase setId_sede(Integer id_sede) {
		this.id_sede = id_sede;
		return this;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public Prueba_deportivaBase setObservaciones(String observaciones) {
		this.observaciones = observaciones;
		return this;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof bd.base.Prueba_deportivaBase))
			return false;
		return ((bd.base.Prueba_deportivaBase) obj).getId()
				.equals(this.getId());
	}

	@Override
	public int hashCode() {
		return (int) this.id;
	}
}