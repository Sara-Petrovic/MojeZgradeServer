/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.nprog.zgradeserver.repository.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.nprog.zgradeserver.repository.db.DbConnectionFactory;
import rs.ac.bg.fon.nprog.zgradeserver.repository.db.DbRepository;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.Mesto;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.StambenaZajednica;

/**
 *
 * @author Sara
 */
public class RepositoryDBStambenaZajednica implements DbRepository<StambenaZajednica> {

    @Override
    public List<StambenaZajednica> getAll(StambenaZajednica param) {
        try {
            String sql = "select s.stambenazajednicaid as id,s.mesto as mestoid,s.ulica as ulica, s.broj as broj,"
                    + " s.tekuciracun as tekuciracun, s.banka as banka, s.pib as pib, s.maticnibroj as maticnibroj,"
                    + " m.naziv as mnaziv, m.ptt as mptt "
                    + " from stambena_zajednica AS s inner join mesto AS m on (s.mesto = m.mestoid)";

            List<StambenaZajednica> stambeneZajednice = new ArrayList<>();
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                StambenaZajednica stambenaZajednica = new StambenaZajednica();
                stambenaZajednica.setStambenaZajednicaId(rs.getLong("id"));
                stambenaZajednica.setUlica(rs.getString("ulica"));
                stambenaZajednica.setBroj(rs.getString("broj"));
                stambenaZajednica.setTekuciRacun(rs.getString("tekuciracun"));
                stambenaZajednica.setBanka(rs.getString("banka"));
                stambenaZajednica.setPib(rs.getString("pib"));
                stambenaZajednica.setMaticniBroj(rs.getString("maticnibroj"));
                

                Mesto mesto = new Mesto();
                mesto.setMestoId(rs.getLong("mestoid"));
                mesto.setNaziv(rs.getString("mnaziv"));
                mesto.setPtt(rs.getString("mptt"));

                stambenaZajednica.setMesto(mesto);
                stambeneZajednice.add(stambenaZajednica);
            }
            rs.close();
            statement.close();
            return stambeneZajednice;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void add(StambenaZajednica param) throws Exception {
        try {
            String sql = "INSERT into stambena_zajednica VALUES (?,?,?,?,?,?,?,?)";

            Connection connection = DbConnectionFactory.getInstance().getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, param.getStambenaZajednicaId());
            statement.setLong(2, param.getMesto().getMestoId());
            statement.setString(3, param.getUlica());
            statement.setString(4, param.getBroj());
            statement.setString(5, param.getBanka());
            statement.setString(6, param.getTekuciRacun());
            statement.setString(7, param.getPib());
            statement.setString(8, param.getMaticniBroj());
            
            statement.executeUpdate();
            statement.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Stambena zajednica nije sacuvana!");
        }
    }

    @Override
    public void edit(StambenaZajednica param) throws Exception {
        try {
            String sql = "UPDATE stambena_zajednica SET "
                    + "mesto=" + param.getMesto().getMestoId()+ ", "
                    + "ulica='" + param.getUlica()+ "', "
                    + "broj='" + param.getBroj()+ "',"
                    + "banka='" + param.getBanka()+ "',"
                    + "tekuciracun='" + param.getTekuciRacun() + "', "
                    + "pib='" + param.getPib() + "', "
                    + "maticnibroj='" + param.getMaticniBroj()+ "' "
                    + "WHERE stambenazajednicaid=" + param.getStambenaZajednicaId();
            System.out.println(sql);
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Greska prilikom izmene stambene zajednice: \n" + ex.getMessage());
        }
    }

    @Override
    public void delete(StambenaZajednica param) throws Exception {
        try {
            String sql = "DELETE FROM stambena_zajednica WHERE stambenazajednicaid=" + param.getStambenaZajednicaId();
            System.out.println(sql);
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Greska prilikom brisanja stambene zajednice: \n" + ex.getMessage());
        }
    }

    @Override
    public List<StambenaZajednica> get(StambenaZajednica sz) {
        try {
            Mesto m = sz.getMesto();
            String ulica1 = sz.getUlica();
            if(ulica1==null)
                ulica1 = "";
            String sql = "select s.stambenazajednicaid as id,s.mesto as mestoid,s.ulica as ulica, s.broj as broj,"
                    + " s.tekuciracun as tekuciracun, s.banka as banka, s.pib as pib, s.maticnibroj as maticnibroj,"
                    + " m.naziv as mnaziv, m.ptt as mptt "
                    + " from stambena_zajednica AS s inner join mesto AS m on (s.mesto = m.mestoid)"
                    + " where ulica like '%" + ulica1 + "%' ";
            if(m!=null){
                sql += " and mestoid = "+m.getMestoId();
                //System.out.println(m.getMestoId());
                //System.out.println(sql);
            }

            List<StambenaZajednica> stambeneZajednice = new ArrayList<>();
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                StambenaZajednica stambenaZajednica = new StambenaZajednica();
                stambenaZajednica.setStambenaZajednicaId(rs.getLong("id"));
                stambenaZajednica.setUlica(rs.getString("ulica"));
                stambenaZajednica.setBroj(rs.getString("broj"));
                stambenaZajednica.setTekuciRacun(rs.getString("tekuciracun"));
                stambenaZajednica.setBanka(rs.getString("banka"));
                stambenaZajednica.setPib(rs.getString("pib"));
                stambenaZajednica.setMaticniBroj(rs.getString("maticnibroj"));
                

                Mesto mesto = new Mesto();
                mesto.setMestoId(rs.getLong("mestoid"));
                mesto.setNaziv(rs.getString("mnaziv"));
                mesto.setPtt(rs.getString("mptt"));

                stambenaZajednica.setMesto(mesto);
                stambeneZajednice.add(stambenaZajednica);
                System.out.println("dodata " + stambenaZajednica.getUlica());
            }
            rs.close();
            statement.close();
            return stambeneZajednice;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
