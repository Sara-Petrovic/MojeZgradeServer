/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.nprog.zgradeserver.operation.vlasnikposebnogdela;

import java.util.List;

import rs.ac.bg.fon.nprog.zgradeserver.operation.AbstractGenericOperation;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.VlasnikPosebnogDela;

/**
 *
 * @author Sara
 */
public class NadjiVlasnikePosebnihDelovaOperacija extends AbstractGenericOperation{

    List<VlasnikPosebnogDela> vlasnici;
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof VlasnikPosebnogDela)) {
            throw new Exception("Sistem ne moze da nadje vlasnike posebnih delova po zadatoj vrednosti");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
       try {
            this.vlasnici = repository.get((VlasnikPosebnogDela) param);
        }catch(Exception e){
            throw new Exception("Sistem ne moze da nadje vlasnike posebnih delova po zadatoj vrednosti");
        }
    }

    public List<VlasnikPosebnogDela> getVlasnici() {
        return vlasnici;
    }
    
    
}
