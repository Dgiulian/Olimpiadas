/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.detalle;

import bd.Delegacion;
import bd.vista.MedalleroDelegacion;
import transaccion_vista.TMedalleroDelegacion;

/**
 *
 * @author JuanMa
 */
public class DelegacionDet extends Delegacion {

    MedalleroDelegacion medallero;

    public DelegacionDet(Delegacion delegacion) {
        super(delegacion);
    }

    public void setearMedallero() {
        TMedalleroDelegacion tm = new TMedalleroDelegacion();
        MedalleroDelegacion me = tm.recuperar_medallero_delegacion(this.getId());
        this.medallero = me;
    }

    public MedalleroDelegacion getMedallero() {
        return medallero;
    }

    public void setMedallero(MedalleroDelegacion medallero) {
        this.medallero = medallero;
    }

}
