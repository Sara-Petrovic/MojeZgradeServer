/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.nprog.zgradeserver.repository.db.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.nprog.zgradeserver.repository.db.DbConnectionFactory;
import rs.ac.bg.fon.nprog.zgradeserver.repository.db.DbRepository;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.GenericEntity;

/**
 *
 * @author Sara
 */
public class RepositoryDBGeneric implements DbRepository<GenericEntity> { //ovde su implementirane genericke metode brokera baze podataka

    @Override
    public List<GenericEntity> getAll(GenericEntity entity) throws Exception {
        try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT * FROM ")
                    .append(entity.getTableName()).append(" ")
                    .append(entity.getAlijas()).append(" ")
                    .append(entity.getJoin()).append(" ");
            String query = sb.toString();
            System.out.println(query);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            return entity.makeList(rs);

        } catch (SQLException ex) {
            throw ex;
        }
    }

    @Override
    public List<GenericEntity> get(GenericEntity entity) throws Exception {
        try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            if(entity!=null) {
            sb.append("SELECT * FROM ")
                    .append(entity.getTableName()).append(" ")
                    .append(entity.getAlijas()).append(" ")
                    .append(entity.getJoin()).append(" ")
                    .append(entity.selectWhere());
            String query = sb.toString();
            System.out.println(query);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            return entity.makeList(rs);
            }else {
            	return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RepositoryDBGeneric.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }

    }

    @Override
    public void add(GenericEntity entity) throws Exception {
        try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO ")
                    .append(entity.getTableName())
                    .append(" (").append(entity.getColumnNamesForInsert()).append(")")
                    .append(" VALUES (")
                    .append(entity.getInsertValues())
                    .append(")");
            String query = sb.toString();
            System.out.println(query);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rsKey = statement.getGeneratedKeys();
            if (rsKey.next()) {
                Long id = rsKey.getLong(1);
                entity.setId(id);
                System.out.println("setovan id na entity na " + id);
            }
            statement.close();
            rsKey.close();

        } catch (SQLException ex) {
            throw ex;
        }
    }

    @Override
    public void edit(GenericEntity entity) throws Exception {
        try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();

            sb.append("UPDATE ")
                    .append(entity.getTableName())
                    .append(" SET ")
                    .append(entity.getUpdateValues())
                    .append(" WHERE ")
                    .append(entity.getPrimaryKeyValue());
            String query = sb.toString();
            System.out.println(query);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
           
            statement.close();
           

        } catch (SQLException ex) {
            throw ex;
        }
    }

    @Override
    public void delete(GenericEntity entity) throws Exception {
        try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("DELETE FROM ")
                    .append(entity.getTableName())
                    .append(" WHERE ")
                    .append(entity.getPrimaryKeyValue());
            String query = sb.toString();
            System.out.println(query);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();

        } catch (SQLException ex) {
            throw ex;
        }
    }

}
