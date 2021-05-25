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
public class UcitajStambenuZajednicuOperacija extends AbstractGenericOperation{

    StambenaZajednica stambenaZajednica;
    
    @Override
    protected void preconditions(Object param) throws Exception {
       if (param == null || !(param instanceof StambenaZajednica)) {
            throw new Exception("Sistem ne moze da ucita stambenu zajednicu");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
       try {
            this.stambenaZajednica = (StambenaZajednica) (repository.get((StambenaZajednica) param).get(0));
        }catch(Exception e){
            throw new Exception("Sistem ne moze da ucita stambenu zajednicu");
        }
    }

    public StambenaZajednica getStambenaZajednica() {
        return stambenaZajednica;
    }
    
    
}
