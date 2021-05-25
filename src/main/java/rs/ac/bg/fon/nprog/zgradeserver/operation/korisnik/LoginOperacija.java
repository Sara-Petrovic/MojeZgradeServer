/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.nprog.zgradeserver.operation.korisnik;

import rs.ac.bg.fon.nprog.zgradeserver.operation.AbstractGenericOperation;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.Korisnik;

/**
 *
 * @author Sara
 */
public class LoginOperacija extends AbstractGenericOperation {

    Korisnik korisnik; 
    
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Korisnik)) {
            throw new Exception("Nepoznat korisnik!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        try {
            this.korisnik = (Korisnik) repository.get((Korisnik) param).get(0);
        } catch (Exception e) {
            throw new Exception("Nepoznat korisnik!");
        }
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }
    

}
