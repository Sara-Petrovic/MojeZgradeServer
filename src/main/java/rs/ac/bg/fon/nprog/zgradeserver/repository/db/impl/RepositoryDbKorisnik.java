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
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.Korisnik;

/**
 *
 * @author Sara
 */
public class RepositoryDbKorisnik implements DbRepository<Korisnik>{
    @Override
    public List<Korisnik> getAll(Korisnik param) {
        try {
            String sql = "select * from Korisnik";
          
            List<Korisnik> korisnici = new ArrayList<>();
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                Korisnik korisnik = new Korisnik();
                korisnik.setId(rs.getLong("id"));
                korisnik.setIme(rs.getString("ime"));
                korisnik.setPrezime(rs.getString("prezime"));
                korisnik.setLozinka(rs.getString("lozinka"));
                korisnik.setKorisnickoIme(rs.getString("korisnickoIme"));
                korisnici.add(korisnik);
            }
            rs.close();
            statement.close();
            return korisnici;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void add(Korisnik param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(Korisnik param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Korisnik param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public List<Korisnik> get(Korisnik param) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

}
