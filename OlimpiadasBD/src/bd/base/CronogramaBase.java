/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.base;

import bd.Cronograma;

/**
 *
 * @author JuanMa
 */
public class CronogramaBase {

    public Integer id;
    public String fecha = "";
    public String hora = "";
    public String descripcion = "";
    public String lugar = "";
    public String latitud = "";
    public String longitud = "";

    public CronogramaBase() {
    }

    public CronogramaBase(Cronograma cronograma) {
        this.id = cronograma.getId();
        this.fecha = cronograma.getFecha();
        this.hora = cronograma.getHora();
        this.descripcion = cronograma.getDescripcion();
        this.lugar = cronograma.getLugar();
        this.latitud = cronograma.getLatitud();
        this.longitud = cronograma.getLongitud();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }
    
    @Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof bd.base.CronogramaBase))
			return false;
		return ((bd.base.CronogramaBase) obj).getId().equals(this.getId());
	}

	@Override
	public int hashCode() {
		return (int) this.id;
	}

}
