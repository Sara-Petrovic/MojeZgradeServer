/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.nprog.zgradeserver.operation.vlasnikposebnogdela;

import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.nprog.zgradeserver.operation.AbstractGenericOperation;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.VlasnikPosebnogDela;

/**
 *
 * @author Sara
 */
public class UnesiVlasnikaPosebnogDelaOperacija extends AbstractGenericOperation{
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof VlasnikPosebnogDela)) {
            throw new Exception("Sistem ne moze da zapamti vlasnika posebnog dela");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        try {
            repository.add((VlasnikPosebnogDela) param);
        }catch(Exception e){
            Logger.getLogger(UnesiVlasnikaPosebnogDelaOperacija.class.getName()).log(Level.SEVERE,null,e);
            throw new Exception("Sistem ne moze da zapamti vlasnika posebnog dela");
            
        }
    }
}
