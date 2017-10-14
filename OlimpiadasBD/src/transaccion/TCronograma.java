/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transaccion;

import bd.Cronograma;
import java.util.List;

/**
 *
 * @author JuanMa
 */
public class TCronograma extends TransaccionBase<Cronograma> {

    @Override
    public List<Cronograma> getList() {
        return super.getList("select * from cronograma ");
    }

    public Boolean actualizar(Cronograma cronograma) {
        return super.actualizar(cronograma, "id");
    }

    public Cronograma getById(Integer id) {
        String query = String.format(
                "select * from cronograma where cronograma.id = %d ", id);
        return super.getById(query);
    }

}
