package bd.base;
public class Equipo_prueba_deportivaBase {

	public Integer id = 0;
	public Integer id_equipo = 0;
	public Integer id_prueba_deportiva = 0;

	public Equipo_prueba_deportivaBase() {
	}

	public Equipo_prueba_deportivaBase(
			Equipo_prueba_deportivaBase equipo_prueba_deportivabase) {
		this.id = equipo_prueba_deportivabase.getId();
		this.id_equipo = equipo_prueba_deportivabase.getId_equipo();
		this.id_prueba_deportiva = equipo_prueba_deportivabase
				.getId_prueba_deportiva();
	}

	public Integer getId() {
		return this.id;
	}

	public Equipo_prueba_deportivaBase setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getId_equipo() {
		return this.id_equipo;
	}

	public Equipo_prueba_deportivaBase setId_equipo(Integer id_equipo) {
		this.id_equipo = id_equipo;
		return this;
	}

	public Integer getId_prueba_deportiva() {
		return this.id_prueba_deportiva;
	}

	public Equipo_prueba_deportivaBase setId_prueba_deportiva(
			Integer id_prueba_deportiva) {
		this.id_prueba_deportiva = id_prueba_deportiva;
		return this;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof bd.base.Equipo_prueba_deportivaBase))
			return false;
		return ((bd.base.Equipo_prueba_deportivaBase) obj).getId().equals(
				this.getId());
	}

	@Override
	public int hashCode() {
		return (int) this.id;
	}
}