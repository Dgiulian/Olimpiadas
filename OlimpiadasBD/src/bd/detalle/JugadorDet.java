/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.detalle;

import bd.Delegacion;
import bd.Jugador;
import java.util.HashMap;
import transaccion.TDelegacion;

/**
 *
 * @author JuanMa
 */
public class JugadorDet extends Jugador {

    public static final HashMap<Integer, Delegacion> mapDelegaciones = new TDelegacion().getMap();
    DelegacionDet delegacion;

    public JugadorDet(Jugador c) {
        super(c);
        Delegacion d = mapDelegaciones.get(c.getId_delegacion());
        delegacion = new DelegacionDet(d);
        delegacion.setearMedallero();

    }

}
