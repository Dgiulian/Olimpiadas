package bd.base;
public class Grupo_detalleBase {

	public Integer id = 0;
	public Integer id_grupo = 0;
	public Integer id_equipo = 0;
	public Integer medalla = 0;

	public Grupo_detalleBase() {
	}

	public Grupo_detalleBase(Grupo_detalleBase grupo_detallebase) {
		this.id = grupo_detallebase.getId();
		this.id_grupo = grupo_detallebase.getId_grupo();
		this.id_equipo = grupo_detallebase.getId_equipo();
		this.medalla = grupo_detallebase.getMedalla();
	}

	public Integer getId() {
		return this.id;
	}

	public Grupo_detalleBase setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getId_grupo() {
		return this.id_grupo;
	}

	public Grupo_detalleBase setId_grupo(Integer id_grupo) {
		this.id_grupo = id_grupo;
		return this;
	}

	public Integer getId_equipo() {
		return this.id_equipo;
	}

	public Grupo_detalleBase setId_equipo(Integer id_equipo) {
		this.id_equipo = id_equipo;
		return this;
	}

	public Integer getMedalla() {
		return this.medalla;
	}

	public Grupo_detalleBase setMedalla(Integer medalla) {
		this.medalla = medalla;
		return this;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof bd.base.Grupo_detalleBase))
			return false;
		return ((bd.base.Grupo_detalleBase) obj).getId().equals(this.getId());
	}

	@Override
	public int hashCode() {
		return (int) this.id;
	}
}