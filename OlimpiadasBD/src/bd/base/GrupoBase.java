package bd.base;
public class GrupoBase {

	public Integer id = 0;
	public Integer id_categoria = 0;
	public String nombre_grupo = "";
	public String observacion = "";

	public GrupoBase() {
	}

	public GrupoBase(GrupoBase grupobase) {
		this.id = grupobase.getId();
		this.id_categoria = grupobase.getId_categoria();
		this.nombre_grupo = grupobase.getNombre_grupo();
		this.observacion = grupobase.getObservacion();
	}

	public Integer getId() {
		return this.id;
	}

	public GrupoBase setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getId_categoria() {
		return this.id_categoria;
	}

	public GrupoBase setId_categoria(Integer id_categoria) {
		this.id_categoria = id_categoria;
		return this;
	}

	public String getNombre_grupo() {
		return this.nombre_grupo;
	}

	public GrupoBase setNombre_grupo(String nombre_grupo) {
		this.nombre_grupo = nombre_grupo;
		return this;
	}

	public String getObservacion() {
		return this.observacion;
	}

	public GrupoBase setObservacion(String observacion) {
		this.observacion = observacion;
		return this;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof bd.base.GrupoBase))
			return false;
		return ((bd.base.GrupoBase) obj).getId().equals(this.getId());
	}

	@Override
	public int hashCode() {
		return (int) this.id;
	}
}