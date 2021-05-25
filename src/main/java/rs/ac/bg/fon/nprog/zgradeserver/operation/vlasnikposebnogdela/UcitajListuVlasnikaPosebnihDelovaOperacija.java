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
public class UcitajListuVlasnikaPosebnihDelovaOperacija extends AbstractGenericOperation {

    List<VlasnikPosebnogDela> vlasnici;

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof VlasnikPosebnogDela)) {
            throw new Exception("Sistem ne moze da ucita listu vlasnika posebnih delova");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        try {
            this.vlasnici = repository.getAll((VlasnikPosebnogDela) param);
        } catch (Exception e) {
            throw new Exception("Sistem ne moze da ucita listu vlasnika posebnih delova");
        }
    }

    public List<VlasnikPosebnogDela> getVlasnici() {
        return vlasnici;
    }

}
