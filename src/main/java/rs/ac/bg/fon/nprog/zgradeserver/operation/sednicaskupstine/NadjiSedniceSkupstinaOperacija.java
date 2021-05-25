/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.nprog.zgradeserver.operation.sednicaskupstine;

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
public class NadjiSedniceSkupstinaOperacija extends AbstractGenericOperation{
    List<SednicaSkupstine> listaSednica;
     @Override
    protected void preconditions(Object param) throws Exception {
         if (param == null || !(param instanceof SednicaSkupstine)) {
            throw new Exception("Sistem ne moze da nadje sednice skupstina po zadatoj vrednosti");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        try {
            listaSednica = repository.get((SednicaSkupstine) param);
            SednicaVlasnik sednicaVlasnik = new SednicaVlasnik();
            for (SednicaSkupstine sednicaSkupstine : listaSednica) {
                sednicaVlasnik.setSednicaSkupstine(sednicaSkupstine);
                List<SednicaVlasnik> lista = repository.get(sednicaVlasnik);
                for (SednicaVlasnik sednicaVlasnik1 : lista) {
                    VlasnikPosebnogDela  vlasnik = sednicaVlasnik1.getVlasnikPosebnogDela();
                    sednicaSkupstine.getVlasnici().add(vlasnik);
                }
            }
        }catch(Exception e){
            Logger.getLogger(ZapamtiSednicuSkupstineOperacija.class.getName()).log(Level.SEVERE,null,e);
            throw new Exception("Sistem ne moze da nadje sednice skupstina po zadatoj vrednosti");
            
        }
    }

    public List<SednicaSkupstine> getListaSednica() {
        return listaSednica;
    }
    
}
