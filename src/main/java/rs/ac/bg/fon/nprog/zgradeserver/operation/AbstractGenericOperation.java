/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.nprog.zgradeserver.operation;

import rs.ac.bg.fon.nprog.zgradeserver.repository.Repository;
import rs.ac.bg.fon.nprog.zgradeserver.repository.db.DbRepository;
import rs.ac.bg.fon.nprog.zgradeserver.repository.db.impl.RepositoryDBGeneric;

/**
 * Apstraktna klasa koja predstavlja opstu sistemsku operaciju.
 * 
 * AbstractGenericOperation ima repozitorijum preko koga se pristupa bazi podataka kao Repository.
 * 
 * @author Sara
 * @version 0.1
 *
 */
public abstract class AbstractGenericOperation { //OpstaSistemskaOperacija

	/**
	 * Repozitorijum kome se pristupa kao Repository.
	 */
    protected final Repository repository;

    /**
	 * Kontruktor koji inicijalizuje objekat i dodeljuje repozitorijumu objekat klase RepositoryDBGeneric.
	 */
    public AbstractGenericOperation() {
        this.repository = new RepositoryDBGeneric();
    }

    /**
	 * Poziva izvrsavanje apstraktnih metoda preconditions, startTransaction, executeOperation i commitTransaction u tom redosledu. Apstraktne metode su definisane u konkretnim sistemskim operacijama. Metoda execute se poziva kada treba da se izvrsi zeljena sistemska operacija. Predstavlja implementaciju template method paterna.
	 * 
	 * @param param Parametar kao Object.
	 * 
	 * @throws java.lang.Exception Ako nastane greska u bilo kojoj od cetiri metode koje se pozivaju.
	 */
    public final void execute(Object param) throws Exception {
        try {
            preconditions(param);
            startTransaction();
            executeOperation(param);
            commitTransaction();
        } catch (Exception ex) {
            ex.printStackTrace();
            rollbackTransaction();
            throw ex;
        } finally {
            disconnect();
        }
    }

    /**
  	 * 
  	 * Proverava da li su ispunjeni preduslovi kako bi se izvrsila sistemska operacija.
  	 * 
  	 * @param param Parametar nad kojim se proveravaju preduslovi kao Object.
  	 * 
  	 * @throws java.lang.Exception Ako nastane greska jer nisu ispunjeni preduslovi za izvrsenje sistemske operacije.
  	 */
    // Operation-specific method
    protected abstract void preconditions(Object param) throws Exception;

    /**
  	 * 
  	 * Dobija konekciju sa bazom i zapocinje transakciju.
  	 * 
  	 * 
  	 * @throws java.lang.Exception Ako nastane greska usled otvaranja konekcije.
  	 */
    private void startTransaction() throws Exception {
        ((DbRepository) repository).connect();
    }
    /**
  	 * 
  	 * Izvrsava konkretnu sistemsku operaciju na osnovu prosledjenog parametra.
  	 * 
  	 * @param param Parametar kao Object.
  	 * 
  	 * @throws java.lang.Exception Ako nastane greska u toku izvrsavanja sistemske operacije.
  	 */
    // Operation-specific method
    protected abstract void executeOperation(Object param) throws Exception;
    
    /**
  	 * 
  	 * Izvrsava commit nad bazom podataka ukoliko ne dodje do greske.
  	 * 
  	 * @throws java.lang.Exception Ako nastane greska u toku pokusaja da se izvrsi commit.
  	 */
    private void commitTransaction() throws Exception {
        ((DbRepository) repository).commit();
    }
    /**
  	 * 
  	 *  Izvrsava rollback nad bazom podataka ukoliko je doslo do greske.
  	 * 
  	 * 
  	 * @throws java.lang.Exception Ako nastane greska u toku pokusaja da se izvrsi rollback .
  	 */
    private void rollbackTransaction() throws Exception {
        ((DbRepository) repository).rollback();
    }
    /**
  	 * 
  	 * Zatvara konekciju sa bazom i zavrsava transakciju.
  	 * 
  	 * 
  	 * @throws java.lang.Exception  Ako nastane greska usled zatvaranja konekcije.
  	 */
    private void disconnect() throws Exception {
        ((DbRepository) repository).disconnect();
    }

}
