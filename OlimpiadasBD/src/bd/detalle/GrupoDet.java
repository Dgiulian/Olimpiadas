/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.detalle;

import bd.Equipo;
import bd.Grupo;
import java.util.List;
import transaccion.TEquipo;

/**
 *
 * @author JuanMa
 */
public class GrupoDet extends Grupo {

    List<Equipo> detalle;

    public GrupoDet(Grupo g) {
        super(g);
        detalle = new TEquipo().getById_grupo(g.getId());
    }

    public List<Equipo> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<Equipo> detalle) {
        this.detalle = detalle;
    }
    
    
}
