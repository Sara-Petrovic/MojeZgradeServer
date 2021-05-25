/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.nprog.zgradeserver.operation.vlasnikposebnogdela;


import rs.ac.bg.fon.nprog.zgradeserver.operation.AbstractGenericOperation;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.VlasnikPosebnogDela;

/**
 *
 * @author Sara
 */
public class UcitajVlasnikaPosebnogDela extends AbstractGenericOperation{
    VlasnikPosebnogDela vlasnikPosebnogDela;
    
    @Override
    protected void preconditions(Object param) throws Exception {
       if (param == null || !(param instanceof VlasnikPosebnogDela)) {
            throw new Exception("Sistem ne moze da ucita vlasnika posebnog dela");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
       try {
            this.vlasnikPosebnogDela = (VlasnikPosebnogDela) (repository.get((VlasnikPosebnogDela) param).get(0));
        }catch(Exception e){
            throw new Exception("Sistem ne moze da ucita vlasnika posebnog dela");
        }
    }

    public VlasnikPosebnogDela getVlasnikPosebnogDela() {
        return vlasnikPosebnogDela;
    }

   
}
