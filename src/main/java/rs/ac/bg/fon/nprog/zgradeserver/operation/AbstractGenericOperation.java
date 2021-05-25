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
 *
 * @author Sara
 */
public abstract class AbstractGenericOperation { //OpstaSistemskaOperacija

    protected final Repository repository;

    public AbstractGenericOperation() {
        this.repository = new RepositoryDBGeneric();
    }

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

    // Operation-specific method
    protected abstract void preconditions(Object param) throws Exception;

    private void startTransaction() throws Exception {
        ((DbRepository) repository).connect();
    }

    // Operation-specific method
    protected abstract void executeOperation(Object param) throws Exception;

    private void commitTransaction() throws Exception {
        ((DbRepository) repository).commit();
    }

    private void rollbackTransaction() throws Exception {
        ((DbRepository) repository).rollback();
    }

    private void disconnect() throws Exception {
        ((DbRepository) repository).disconnect();
    }

}
