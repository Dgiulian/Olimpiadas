/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.detalle;

import bd.Delegacion;
import bd.Equipo;
import java.util.HashMap;
import transaccion.TDelegacion;

/**
 *
 * @author JuanMa
 */
public class EquipoDet extends Equipo {

    Delegacion delegacion;
    public static final HashMap<Integer, Delegacion> mapDelegaciones = new TDelegacion().getMap();

    public EquipoDet(Equipo equipo) {
        super(equipo);
        delegacion = mapDelegaciones.get(equipo.getId_delegacion());
    }
}
