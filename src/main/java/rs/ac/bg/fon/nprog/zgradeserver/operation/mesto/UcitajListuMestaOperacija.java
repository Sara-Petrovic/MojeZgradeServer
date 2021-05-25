/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.nprog.zgradeserver.operation.mesto;

import java.util.List;
import rs.ac.bg.fon.nprog.zgradeserver.operation.AbstractGenericOperation;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.Mesto;

/**
 *
 * @author Sara
 */
public class UcitajListuMestaOperacija extends AbstractGenericOperation{

    List<Mesto> mesta;
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Mesto)) {
            throw new Exception("Sistem ne moze da ucita listu mesta");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
       try {
            this.mesta = repository.getAll((Mesto) param);
        }catch(Exception e){
            throw new Exception("Sistem ne moze da ucita listu mesta");
        }
    }

    public List<Mesto> getMesta() {
        return mesta;
    }

   
}
