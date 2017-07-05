package bd.base;
public class JugadoresBase {

	public Integer id = 0;
	public String nombre_apellido = "";
	public String dni = "";
	public String fecha_nacimiento = "";
	public String matricula = "";
	public Integer id_delegacion = 0;

	public JugadoresBase() {
	}

	public JugadoresBase(JugadoresBase jugadoresbase) {
		this.id = jugadoresbase.getId();
		this.nombre_apellido = jugadoresbase.getNombre_apellido();
		this.dni = jugadoresbase.getDni();
		this.fecha_nacimiento = jugadoresbase.getFecha_nacimiento();
		this.matricula = jugadoresbase.getMatricula();
		this.id_delegacion = jugadoresbase.getId_delegacion();
	}

	public Integer getId() {
		return this.id;
	}

	public JugadoresBase setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getNombre_apellido() {
		return this.nombre_apellido;
	}

	public JugadoresBase setNombre_apellido(String nombre_apellido) {
		this.nombre_apellido = nombre_apellido;
		return this;
	}

	public String getDni() {
		return this.dni;
	}

	public JugadoresBase setDni(String dni) {
		this.dni = dni;
		return this;
	}

	public String getFecha_nacimiento() {
		return this.fecha_nacimiento;
	}

	public JugadoresBase setFecha_nacimiento(String fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
		return this;
	}

	public String getMatricula() {
		return this.matricula;
	}

	public JugadoresBase setMatricula(String matricula) {
		this.matricula = matricula;
		return this;
	}

	public Integer getId_delegacion() {
		return this.id_delegacion;
	}

	public JugadoresBase setId_delegacion(Integer id_delegacion) {
		this.id_delegacion = id_delegacion;
		return this;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof bd.base.JugadoresBase))
			return false;
		return ((bd.base.JugadoresBase) obj).getId().equals(this.getId());
	}

	@Override
	public int hashCode() {
		return (int) this.id;
	}
}