/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.nprog.zgradeserver.repository;

import java.util.List;


/**
 *
 * @author Sara
 */
public interface Repository<T>  {
    List<T> getAll(T param) throws Exception;
    List<T> get(T param)throws Exception;
    void add(T param) throws Exception;
    void edit(T param) throws Exception;
    void delete(T param)throws Exception;

    
}
