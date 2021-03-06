package bd.base;

public class GrupoBase {

    public Integer id = 0;
    public Integer id_categoria = 0;
    public String nombre = "";
    public String observacion = "";
    public Integer tipo_vista_tabla = 0;

    public GrupoBase() {
    }

    public GrupoBase(GrupoBase grupobase) {
        this.id = grupobase.getId();
        this.id_categoria = grupobase.getId_categoria();
        this.nombre = grupobase.getNombre();
        this.observacion = grupobase.getObservacion();
        this.tipo_vista_tabla = grupobase.getTipo_vista_tabla();
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

    public String getNombre() {
        return this.nombre;
    }

    public GrupoBase setNombre(String nombre) {
        this.nombre = nombre;
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
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof bd.base.GrupoBase)) {
            return false;
        }
        return ((bd.base.GrupoBase) obj).getId().equals(this.getId());
    }

    public Integer getTipo_vista_tabla() {
        return tipo_vista_tabla;
    }

    public void setTipo_vista_tabla(Integer tipo_vista_tabla) {
        this.tipo_vista_tabla = tipo_vista_tabla;
    }

    @Override
    public int hashCode() {
        return (int) this.id;
    }
}
