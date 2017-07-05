package bd.base;
public class Prueba_deportivaBase {

	public Integer id = 0;
	public Integer id_deporte = 0;
	public Integer id_categoria = 0;
	public String observaciones = "";

	public Prueba_deportivaBase() {
	}

	public Prueba_deportivaBase(Prueba_deportivaBase prueba_deportivabase) {
		this.id = prueba_deportivabase.getId();
		this.id_deporte = prueba_deportivabase.getId_deporte();
		this.id_categoria = prueba_deportivabase.getId_categoria();
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