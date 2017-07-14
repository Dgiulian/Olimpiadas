package bd.base;
public class NovedadBase {

	public Integer id = 0;
	public Integer usuario_admin_id = 0;
	public String fecha = "";
	public String titulo = "";
	public String subtitulo = "";
	public String detalle = "";
	public String adjunto = "";
	public String imagen = "";
	public String youtube = "";

	public NovedadBase() {
	}

	public NovedadBase(NovedadBase novedadbase) {
		this.id = novedadbase.getId();
		this.usuario_admin_id = novedadbase.getUsuario_admin_id();
		this.fecha = novedadbase.getFecha();
		this.titulo = novedadbase.getTitulo();
		this.subtitulo = novedadbase.getSubtitulo();
		this.detalle = novedadbase.getDetalle();
		this.adjunto = novedadbase.getAdjunto();
		this.imagen = novedadbase.getImagen();
		this.youtube = novedadbase.getYoutube();
	}

	public Integer getId() {
		return this.id;
	}

	public NovedadBase setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getUsuario_admin_id() {
		return this.usuario_admin_id;
	}

	public NovedadBase setUsuario_admin_id(Integer usuario_admin_id) {
		this.usuario_admin_id = usuario_admin_id;
		return this;
	}

	public String getFecha() {
		return this.fecha;
	}

	public NovedadBase setFecha(String fecha) {
		this.fecha = fecha;
		return this;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public NovedadBase setTitulo(String titulo) {
		this.titulo = titulo;
		return this;
	}

	public String getSubtitulo() {
		return this.subtitulo;
	}

	public NovedadBase setSubtitulo(String subtitulo) {
		this.subtitulo = subtitulo;
		return this;
	}

	public String getDetalle() {
		return this.detalle;
	}

	public NovedadBase setDetalle(String detalle) {
		this.detalle = detalle;
		return this;
	}

	public String getAdjunto() {
		return this.adjunto;
	}

	public NovedadBase setAdjunto(String adjunto) {
		this.adjunto = adjunto;
		return this;
	}

	public String getImagen() {
		return this.imagen;
	}

	public NovedadBase setImagen(String imagen) {
		this.imagen = imagen;
		return this;
	}

	public String getYoutube() {
		return this.youtube;
	}

	public NovedadBase setYoutube(String youtube) {
		this.youtube = youtube;
		return this;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof bd.base.NovedadBase))
			return false;
		return ((bd.base.NovedadBase) obj).getId().equals(this.getId());
	}

	@Override
	public int hashCode() {
		return (int) this.id;
	}
}