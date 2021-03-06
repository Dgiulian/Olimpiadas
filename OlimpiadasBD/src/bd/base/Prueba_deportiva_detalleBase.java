package bd.base;

public class Prueba_deportiva_detalleBase {

    public Integer id = 0;
    public Integer id_prueba = 0;
    public Integer id_equipo = 0;
    public Integer puntos = 0;
    public String resultado = "";
    public Integer resultado_encontra = 0;
    public String medalla = "";

    public Prueba_deportiva_detalleBase() {
    }

    public Prueba_deportiva_detalleBase(
            Prueba_deportiva_detalleBase prueba_deportiva_detallebase) {
        this.id = prueba_deportiva_detallebase.getId();
        this.id_prueba = prueba_deportiva_detallebase.getId_prueba();
        this.id_equipo = prueba_deportiva_detallebase.getId_equipo();
        this.resultado = prueba_deportiva_detallebase.getResultado();
        this.resultado_encontra = prueba_deportiva_detallebase.getResultado_encontra();
        this.puntos = prueba_deportiva_detallebase.getPuntos();
        this.medalla = prueba_deportiva_detallebase.getMedalla();
    }

    public Integer getId() {
        return this.id;
    }

    public Prueba_deportiva_detalleBase setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getId_prueba() {
        return this.id_prueba;
    }

    public Prueba_deportiva_detalleBase setId_prueba(Integer id_prueba) {
        this.id_prueba = id_prueba;
        return this;
    }

    public Integer getId_equipo() {
        return this.id_equipo;
    }

    public Prueba_deportiva_detalleBase setId_equipo(Integer id_equipo) {
        this.id_equipo = id_equipo;
        return this;
    }

    public String getResultado() {
        return this.resultado;
    }

    public Prueba_deportiva_detalleBase setResultado(String resultado) {
        this.resultado = resultado;
        return this;
    }

    public String getMedalla() {
        return this.medalla;
    }

    public Prueba_deportiva_detalleBase setMedalla(String medalla) {
        this.medalla = medalla;
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
        if (!(obj instanceof bd.base.Prueba_deportiva_detalleBase)) {
            return false;
        }
        return ((bd.base.Prueba_deportiva_detalleBase) obj).getId().equals(
                this.getId());
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    public Integer getResultado_encontra() {
        return resultado_encontra;
    }

    public void setResultado_encontra(Integer resultado_encontra) {
        this.resultado_encontra = resultado_encontra;
    }

    @Override
    public int hashCode() {
        return (int) this.id;
    }
}
