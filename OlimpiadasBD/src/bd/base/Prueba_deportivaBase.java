package bd.base;

public class Prueba_deportivaBase {

    public Integer id = 0;
    public Integer id_deporte = 0;
    public Integer id_categoria = 0;
    public Integer id_grupo = 0;
    public Integer id_estado = 0;
    public String fecha = "";
    public String hora = "";
    public Integer id_sede = 0;
    public String observaciones = "";
    public String detalle_resultado = "";

    public Prueba_deportivaBase() {
    }

    public Prueba_deportivaBase(Prueba_deportivaBase prueba_deportivabase) {
        this.id = prueba_deportivabase.getId();
        this.id_deporte = prueba_deportivabase.getId_deporte();
        this.id_categoria = prueba_deportivabase.getId_categoria();
        this.id_grupo = prueba_deportivabase.getId_grupo();
        this.id_estado = prueba_deportivabase.getId_estado();
        this.fecha = prueba_deportivabase.getFecha();
        this.hora = prueba_deportivabase.getHora();
        this.id_sede = prueba_deportivabase.getId_sede();
        this.detalle_resultado = prueba_deportivabase.getDetalle_resultado();
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

    public Integer getId_grupo() {
        return this.id_grupo;
    }

    public Prueba_deportivaBase setId_grupo(Integer id_grupo) {
        this.id_grupo = id_grupo;
        return this;
    }

    public Integer getId_estado() {
        return this.id_estado;
    }

    public Prueba_deportivaBase setId_estado(Integer id_estado) {
        this.id_estado = id_estado;
        return this;
    }

    public String getFecha() {
        return this.fecha;
    }

    public Prueba_deportivaBase setFecha(String fecha) {
        this.fecha = fecha;
        return this;
    }

    public String getHora() {
        return this.hora;
    }

    public Prueba_deportivaBase setHora(String hora) {
        this.hora = hora;
        return this;
    }

    public Integer getId_sede() {
        return this.id_sede;
    }

    public Prueba_deportivaBase setId_sede(Integer id_sede) {
        this.id_sede = id_sede;
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
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof bd.base.Prueba_deportivaBase)) {
            return false;
        }
        return ((bd.base.Prueba_deportivaBase) obj).getId()
                .equals(this.getId());
    }

    public String getDetalle_resultado() {
        return detalle_resultado;
    }

    public void setDetalle_resultado(String detalle_resultado) {
        this.detalle_resultado = detalle_resultado;
    }

    @Override
    public int hashCode() {
        return (int) this.id;
    }
}
