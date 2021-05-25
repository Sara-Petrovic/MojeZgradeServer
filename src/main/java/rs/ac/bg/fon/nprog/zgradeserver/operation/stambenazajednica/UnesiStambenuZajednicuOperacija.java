/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.nprog.zgradeserver.operation.stambenazajednica;

import rs.ac.bg.fon.nprog.zgradeserver.operation.AbstractGenericOperation;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.StambenaZajednica;

/**
 *
 * @author Sara
 */
public class UnesiStambenuZajednicuOperacija extends AbstractGenericOperation {

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof StambenaZajednica)) {
            throw new Exception("Sistem ne moze da zapamti stambenu zajednicu");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        try {
            repository.add((StambenaZajednica) param);
        }catch(Exception e){
            throw new Exception("Sistem ne moze da zapamti stambenu zajednicu");
        }
    }
}

