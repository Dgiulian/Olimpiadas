package bd.base;
public class Usuario_appBase {

	public Integer id = 0;
	public String username = "";
	public String password = "";
	public String nombre = "";
	public String apellido = "";
	public String email = "";
	public String documento = "";
	public String fcreacion = "";
	public String ultimoacceso = "";
	public Integer activo = 0;
	public String gcm_id = "";
	public String facebook_id = "";
	public String telefono = "";
	public Integer provincia = 0;
	public Integer localidad = 0;
	public String imagen = "";

	public Usuario_appBase() {
	}

	public Usuario_appBase(Usuario_appBase usuario_appbase) {
		this.id = usuario_appbase.getId();
		this.username = usuario_appbase.getUsername();
		this.password = usuario_appbase.getPassword();
		this.nombre = usuario_appbase.getNombre();
		this.apellido = usuario_appbase.getApellido();
		this.email = usuario_appbase.getEmail();
		this.documento = usuario_appbase.getDocumento();
		this.fcreacion = usuario_appbase.getFcreacion();
		this.ultimoacceso = usuario_appbase.getUltimoacceso();
		this.activo = usuario_appbase.getActivo();
		this.gcm_id = usuario_appbase.getGcm_id();
		this.facebook_id = usuario_appbase.getFacebook_id();
		this.telefono = usuario_appbase.getTelefono();
		this.provincia = usuario_appbase.getProvincia();
		this.localidad = usuario_appbase.getLocalidad();
		this.imagen = usuario_appbase.getImagen();
	}

	public Integer getId() {
		return this.id;
	}

	public Usuario_appBase setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getUsername() {
		return this.username;
	}

	public Usuario_appBase setUsername(String username) {
		this.username = username;
		return this;
	}

	public String getPassword() {
		return this.password;
	}

	public Usuario_appBase setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getNombre() {
		return this.nombre;
	}

	public Usuario_appBase setNombre(String nombre) {
		this.nombre = nombre;
		return this;
	}

	public String getApellido() {
		return this.apellido;
	}

	public Usuario_appBase setApellido(String apellido) {
		this.apellido = apellido;
		return this;
	}

	public String getEmail() {
		return this.email;
	}

	public Usuario_appBase setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getDocumento() {
		return this.documento;
	}

	public Usuario_appBase setDocumento(String documento) {
		this.documento = documento;
		return this;
	}

	public String getFcreacion() {
		return this.fcreacion;
	}

	public Usuario_appBase setFcreacion(String fcreacion) {
		this.fcreacion = fcreacion;
		return this;
	}

	public String getUltimoacceso() {
		return this.ultimoacceso;
	}

	public Usuario_appBase setUltimoacceso(String ultimoacceso) {
		this.ultimoacceso = ultimoacceso;
		return this;
	}

	public Integer getActivo() {
		return this.activo;
	}

	public Usuario_appBase setActivo(Integer activo) {
		this.activo = activo;
		return this;
	}

	public String getGcm_id() {
		return this.gcm_id;
	}

	public Usuario_appBase setGcm_id(String gcm_id) {
		this.gcm_id = gcm_id;
		return this;
	}

	public String getFacebook_id() {
		return this.facebook_id;
	}

	public Usuario_appBase setFacebook_id(String facebook_id) {
		this.facebook_id = facebook_id;
		return this;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public Usuario_appBase setTelefono(String telefono) {
		this.telefono = telefono;
		return this;
	}

	public Integer getProvincia() {
		return this.provincia;
	}

	public Usuario_appBase setProvincia(Integer provincia) {
		this.provincia = provincia;
		return this;
	}

	public Integer getLocalidad() {
		return this.localidad;
	}

	public Usuario_appBase setLocalidad(Integer localidad) {
		this.localidad = localidad;
		return this;
	}

	public String getImagen() {
		return this.imagen;
	}

	public Usuario_appBase setImagen(String imagen) {
		this.imagen = imagen;
		return this;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof bd.base.Usuario_appBase))
			return false;
		return ((bd.base.Usuario_appBase) obj).getId().equals(this.getId());
	}

	@Override
	public int hashCode() {
		return (int) this.id;
	}
}