package bd.base;
public class JugadorBase {

	public Integer id = 0;
	public String nombre_apellido = "";
	public String dni = "";
	public String fecha_nacimiento = "";
	public String matricula = "";
	public Integer id_delegacion = 0;
	public String mail = "";

	public JugadorBase() {
	}

	public JugadorBase(JugadorBase jugadorbase) {
		this.id = jugadorbase.getId();
		this.nombre_apellido = jugadorbase.getNombre_apellido();
		this.dni = jugadorbase.getDni();
		this.fecha_nacimiento = jugadorbase.getFecha_nacimiento();
		this.matricula = jugadorbase.getMatricula();
		this.id_delegacion = jugadorbase.getId_delegacion();
		this.mail = jugadorbase.getMail();
	}

	public Integer getId() {
		return this.id;
	}

	public JugadorBase setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getNombre_apellido() {
		return this.nombre_apellido;
	}

	public JugadorBase setNombre_apellido(String nombre_apellido) {
		this.nombre_apellido = nombre_apellido;
		return this;
	}

	public String getDni() {
		return this.dni;
	}

	public JugadorBase setDni(String dni) {
		this.dni = dni;
		return this;
	}

	public String getFecha_nacimiento() {
		return this.fecha_nacimiento;
	}

	public JugadorBase setFecha_nacimiento(String fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
		return this;
	}

	public String getMatricula() {
		return this.matricula;
	}

	public JugadorBase setMatricula(String matricula) {
		this.matricula = matricula;
		return this;
	}

	public Integer getId_delegacion() {
		return this.id_delegacion;
	}

	public JugadorBase setId_delegacion(Integer id_delegacion) {
		this.id_delegacion = id_delegacion;
		return this;
	}

	public String getMail() {
		return this.mail;
	}

	public JugadorBase setMail(String mail) {
		this.mail = mail;
		return this;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof bd.base.JugadorBase))
			return false;
		return ((bd.base.JugadorBase) obj).getId().equals(this.getId());
	}

	@Override
	public int hashCode() {
		return (int) this.id;
	}
}