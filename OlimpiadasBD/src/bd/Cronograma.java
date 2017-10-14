/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import bd.base.CronogramaBase;

/**
 *
 * @author JuanMa
 */
public class Cronograma extends CronogramaBase{

    public Cronograma() {
    }

    public Cronograma(Cronograma cronograma) {
        super();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof bd.Cronograma)) {
            return false;
        }
        return ((bd.Cronograma) obj).getId().equals(this.getId());
    }

    @Override
    public int hashCode() {
        return (int) this.id;
    }

}
