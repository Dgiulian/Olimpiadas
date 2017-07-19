/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ollimpiadasbd;

import bd.Delegacion;
import conexion.Conexion;
import java.util.List;
import transaccion.TDelegacion;
import transaccion.TUsuario;

/**
 *
 * @author Diego
 */
public class OllimpiadasBD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Conexion conexion = new Conexion();
        conexion.conectarse();
        conexion.desconectarse();

        TDelegacion tdelegacion = new TDelegacion();
        List<Delegacion> lista = tdelegacion.getList();
        System.out.println(lista.size());
    }

}
