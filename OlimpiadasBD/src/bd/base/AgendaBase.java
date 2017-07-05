package bd.base;
public class AgendaBase {

	public Integer id = 0;
	public String fecha_inicio = "";
	public String hora_inicio = "";
	public String fecha_fin = "";
	public String hora_fin = "";
	public Integer id_sede = 0;
	public Integer id_prueba_deportiva = 0;

	public AgendaBase() {
	}

	public AgendaBase(AgendaBase agendabase) {
		this.id = agendabase.getId();
		this.fecha_inicio = agendabase.getFecha_inicio();
		this.hora_inicio = agendabase.getHora_inicio();
		this.fecha_fin = agendabase.getFecha_fin();
		this.hora_fin = agendabase.getHora_fin();
		this.id_sede = agendabase.getId_sede();
		this.id_prueba_deportiva = agendabase.getId_prueba_deportiva();
	}

	public Integer getId() {
		return this.id;
	}

	public AgendaBase setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getFecha_inicio() {
		return this.fecha_inicio;
	}

	public AgendaBase setFecha_inicio(String fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
		return this;
	}

	public String getHora_inicio() {
		return this.hora_inicio;
	}

	public AgendaBase setHora_inicio(String hora_inicio) {
		this.hora_inicio = hora_inicio;
		return this;
	}

	public String getFecha_fin() {
		return this.fecha_fin;
	}

	public AgendaBase setFecha_fin(String fecha_fin) {
		this.fecha_fin = fecha_fin;
		return this;
	}

	public String getHora_fin() {
		return this.hora_fin;
	}

	public AgendaBase setHora_fin(String hora_fin) {
		this.hora_fin = hora_fin;
		return this;
	}

	public Integer getId_sede() {
		return this.id_sede;
	}

	public AgendaBase setId_sede(Integer id_sede) {
		this.id_sede = id_sede;
		return this;
	}

	public Integer getId_prueba_deportiva() {
		return this.id_prueba_deportiva;
	}

	public AgendaBase setId_prueba_deportiva(Integer id_prueba_deportiva) {
		this.id_prueba_deportiva = id_prueba_deportiva;
		return this;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof bd.base.AgendaBase))
			return false;
		return ((bd.base.AgendaBase) obj).getId().equals(this.getId());
	}

	@Override
	public int hashCode() {
		return (int) this.id;
	}
}