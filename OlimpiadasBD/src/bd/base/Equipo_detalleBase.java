package bd.base;
public class Equipo_detalleBase {

	public Integer id = 0;
	public Integer id_equipo = 0;
	public Integer id_jugador = 0;

	public Equipo_detalleBase() {
	}

	public Equipo_detalleBase(Equipo_detalleBase equipo_detallebase) {
		this.id = equipo_detallebase.getId();
		this.id_equipo = equipo_detallebase.getId_equipo();
		this.id_jugador = equipo_detallebase.getId_jugador();
	}

	public Integer getId() {
		return this.id;
	}

	public Equipo_detalleBase setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getId_equipo() {
		return this.id_equipo;
	}

	public Equipo_detalleBase setId_equipo(Integer id_equipo) {
		this.id_equipo = id_equipo;
		return this;
	}

	public Integer getId_jugador() {
		return this.id_jugador;
	}

	public Equipo_detalleBase setId_jugador(Integer id_jugador) {
		this.id_jugador = id_jugador;
		return this;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof bd.base.Equipo_detalleBase))
			return false;
		return ((bd.base.Equipo_detalleBase) obj).getId().equals(this.getId());
	}

	@Override
	public int hashCode() {
		return (int) this.id;
	}
}