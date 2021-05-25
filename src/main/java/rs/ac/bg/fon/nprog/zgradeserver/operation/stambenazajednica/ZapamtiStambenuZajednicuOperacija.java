/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.nprog.zgradeserver.operation.stambenazajednica;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.nprog.zgradeserver.operation.AbstractGenericOperation;
import rs.ac.bg.fon.nprog.zgradeserver.operation.sednicaskupstine.ZapamtiSednicuSkupstineOperacija;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.StambenaZajednica;

/**
 *
 * @author Sara
 */
public class ZapamtiStambenuZajednicuOperacija extends AbstractGenericOperation {

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof StambenaZajednica)) {
            throw new Exception("Sistem ne moze da zapamti stambenu zajednicu");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        try {
//            StambenaZajednica pom = new StambenaZajednica();
//            pom.setStambenaZajednicaId(((StambenaZajednica) param).getStambenaZajednicaId());
//            List<StambenaZajednica> lista = repository.get(pom);
//            if (!lista.isEmpty()) {
//                repository.edit((StambenaZajednica) param);
//            } else {
//                repository.add((StambenaZajednica) param);
//            }
            repository.edit((StambenaZajednica) param);
        } catch (Exception e) {
            Logger.getLogger(ZapamtiStambenuZajednicuOperacija.class.getName()).log(Level.SEVERE, null, e);
            throw new Exception("Sistem ne moze da zapamti stambenu zajednicu");

        }
    }

}
