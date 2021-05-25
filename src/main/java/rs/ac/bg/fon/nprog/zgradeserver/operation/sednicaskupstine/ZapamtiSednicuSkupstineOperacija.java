/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.nprog.zgradeserver.operation.sednicaskupstine;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.nprog.zgradeserver.operation.AbstractGenericOperation;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.SednicaSkupstine;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.SednicaVlasnik;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.VlasnikPosebnogDela;



/**
 *
 * @author Sara
 */
public class ZapamtiSednicuSkupstineOperacija extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object param) throws Exception {
         if (param == null || !(param instanceof SednicaSkupstine)) {
            throw new Exception("Sistem ne moze da zapamti sednicu skupstine");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        try {
            repository.edit((SednicaSkupstine) param);
            SednicaSkupstine sednica = (SednicaSkupstine) param;
            for (VlasnikPosebnogDela vlasnikPosebnogDela : sednica.getVlasnici()) {
                SednicaVlasnik sednicaVlasnik = new SednicaVlasnik(sednica, vlasnikPosebnogDela);
                repository.add(sednicaVlasnik);
            }
        }catch(Exception e){
            Logger.getLogger(ZapamtiSednicuSkupstineOperacija.class.getName()).log(Level.SEVERE,null,e);
            throw new Exception("Sistem ne moze da zapamti sednicu skupstine");
            
        }
    }
    
}
