package bd.base;
public class DeporteBase {

	public Integer id = 0;
	public String nombre = "";
	public Integer tipo = 0;
	public Integer cantidad_jugadores = 0;
	public String reglamento = "";

	public DeporteBase() {
	}

	public DeporteBase(DeporteBase deportebase) {
		this.id = deportebase.getId();
		this.nombre = deportebase.getNombre();
		this.tipo = deportebase.getTipo();
		this.cantidad_jugadores = deportebase.getCantidad_jugadores();
		this.reglamento = deportebase.getReglamento();
	}

	public Integer getId() {
		return this.id;
	}

	public DeporteBase setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getNombre() {
		return this.nombre;
	}

	public DeporteBase setNombre(String nombre) {
		this.nombre = nombre;
		return this;
	}

	public Integer getTipo() {
		return this.tipo;
	}

	public DeporteBase setTipo(Integer tipo) {
		this.tipo = tipo;
		return this;
	}

	public Integer getCantidad_jugadores() {
		return this.cantidad_jugadores;
	}

	public DeporteBase setCantidad_jugadores(Integer cantidad_jugadores) {
		this.cantidad_jugadores = cantidad_jugadores;
		return this;
	}

	public String getReglamento() {
		return this.reglamento;
	}

	public DeporteBase setReglamento(String reglamento) {
		this.reglamento = reglamento;
		return this;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof bd.base.DeporteBase))
			return false;
		return ((bd.base.DeporteBase) obj).getId().equals(this.getId());
	}

	@Override
	public int hashCode() {
		return (int) this.id;
	}
}