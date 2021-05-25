/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.nprog.zgradeserver.repository.db.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.nprog.zgradeserver.repository.db.DbConnectionFactory;
import rs.ac.bg.fon.nprog.zgradeserver.repository.db.DbRepository;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.Mesto;

/**
 *
 * @author Sara
 */
public class RepositoryDBMesto implements DbRepository<Mesto> {

    @Override
    public List<Mesto> getAll(Mesto param) {
        try {
            String sql = "select * from mesto";
            List<Mesto> mesta = new ArrayList<>();
            Connection connection = DbConnectionFactory.getInstance().getConnection();

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Mesto mesto = new Mesto();
                mesto.setMestoId(rs.getLong("mestoid"));
                mesto.setNaziv(rs.getString("naziv"));
                mesto.setPtt(rs.getString("ptt"));      
                mesta.add(mesto);
            }
            rs.close();
            statement.close();
            return mesta;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void add(Mesto param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(Mesto param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Mesto param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  

    @Override
    public List<Mesto> get(Mesto param) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
