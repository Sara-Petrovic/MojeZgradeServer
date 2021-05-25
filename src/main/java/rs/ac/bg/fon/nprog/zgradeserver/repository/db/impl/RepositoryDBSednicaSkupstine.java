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
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.MernaJedinica;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.SednicaSkupstine;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.StambenaZajednica;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.VlasnikPosebnogDela;

/**
 *
 * @author Sara
 */
public class RepositoryDBSednicaSkupstine implements DbRepository<SednicaSkupstine> {

    @Override
    public List<SednicaSkupstine> getAll(SednicaSkupstine param) {
        try {
            String sql = "select ss.sednicaskupstineid as id,ss.datumodrzavanja as datum,ss.brojprisutnih as brojprisutnih, ss.dnevnired as dnevnired, ss.stambenazajednicaid as szid "
                    + " from sednica_skupstine AS ss  ";

            List<SednicaSkupstine> sednice = new ArrayList<>();
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            System.out.println("sql");
            while (rs.next()) {
                SednicaSkupstine sednica = new SednicaSkupstine();
                sednica.setSednicaSkupstineId(rs.getLong("id"));
                sednica.setDatumOdrzavanja(rs.getDate("datum"));
                sednica.setBrojPrisutnih(rs.getInt("brojprisutnih"));
                sednica.setDnevniRed(rs.getString("dnevnired"));

                StambenaZajednica stambenaZajednica = new StambenaZajednica();
                stambenaZajednica.setStambenaZajednicaId(rs.getLong("szid"));

                sql = "select ss.sednicaskupstineid as id, v.vlasnikid as vid,v.ime as ime,v.prezime as prezime,"
                        + " v.brojposebnogdela as broj, v.velicinaposebnogdela as velicina, "
                        + " v.mernajedinica as mernajedinica, v.kontaktvlasnika as kontakt "
                        + "from sednica_skupstine AS ss "
                        + "inner join sednica_vlasnik AS sv on (ss.sednicaskupstineid = sv.sednicaskupstineid) "
                        + "inner join vlasnik_posebnog_dela AS v on (sv.vlasnikposebnogdelaid = v.vlasnikid) "
                        + " where ss.sednicaskupstineid=" + sednica.getSednicaSkupstineId();

                System.out.println("sql");
                //Statement statement1 =connection.createStatement(); 
                ResultSet rs1 = statement.executeQuery(sql);
                while (rs1.next()) {
                    VlasnikPosebnogDela vlasnikPosebnogDela = new VlasnikPosebnogDela();
                    vlasnikPosebnogDela.setVlasnikId(rs.getLong("id"));
                    vlasnikPosebnogDela.setIme(rs.getString("ime"));
                    vlasnikPosebnogDela.setPrezime(rs.getString("prezime"));
                    vlasnikPosebnogDela.setBrojPosebnogDela(rs.getString("broj"));
                    vlasnikPosebnogDela.setVelicinaPosebnogDela(rs.getDouble("velicina"));
                    vlasnikPosebnogDela.setMernaJedinica((MernaJedinica.valueOf(rs.getString("mernajedinica"))));
                    vlasnikPosebnogDela.setKontaktVlasnika(rs.getString("kontakt"));

                    sednica.getVlasnici().add(vlasnikPosebnogDela);
                    System.out.println(vlasnikPosebnogDela);
                }

                sednica.setStambenaZajednica(stambenaZajednica);
                sednice.add(sednica);
            }
            //rs.close();

            statement.close();
            return sednice;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<SednicaSkupstine> get(SednicaSkupstine param) {
        try {
            String sql = "select ss.sednicaskupstineid as id,ss.datumodrzavanja as datum,ss.brojprisutnih as brojprisutnih, ss.dnevnired as dnevnired, ss.stambenazajednicaid as szid, "
                    + "s.stambenazajednicaid as sid, s.mesto as mestoid, s.ulica as ulica, s.broj as sbroj,"
                    + "s.banka as banka, s.tekuciracun as racun, s.pib as pib, s.maticnibroj as  maticnibroj"
                    + " from sednica_skupstine AS ss inner join stambena_zajednica AS s on (s.stambenazajednicaid = ss.stambenazajednicaid) "
                    + " where s.stambenazajednicaid = " + param.getStambenaZajednica().getStambenaZajednicaId();

            System.out.println(sql);

            List<SednicaSkupstine> sednice = new ArrayList<>();
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                SednicaSkupstine sednica = new SednicaSkupstine();
                sednica.setSednicaSkupstineId(rs.getLong("id"));
                sednica.setDatumOdrzavanja(rs.getDate("datum"));
                sednica.setBrojPrisutnih(rs.getInt("brojprisutnih"));
                sednica.setDnevniRed(rs.getString("dnevnired"));

                StambenaZajednica stambenaZajednica = new StambenaZajednica();
                stambenaZajednica.setStambenaZajednicaId(rs.getLong("szid"));

                sql = "select ss.sednicaskupstineid as id, v.vlasnikid as vid,v.ime as ime,v.prezime as prezime,"
                        + " v.brojposebnogdela as broj, v.velicinaposebnogdela as velicina, "
                        + " v.mernajedinica as mernajedinica, v.kontaktvlasnika as kontakt "
                        + "from sednica_skupstine AS ss "
                        + "inner join sednica_vlasnik AS sv on (ss.sednicaskupstineid = sv.sednicaskupstineid) "
                        + "inner join vlasnik_posebnog_dela AS v on (sv.vlasnikposebnogdelaid = v.vlasnikid) "
                        + " where ss.sednicaskupstineid=" + sednica.getSednicaSkupstineId();
                System.out.println(sql);
                rs = statement.executeQuery(sql);
                while (rs.next()) {
                    VlasnikPosebnogDela vlasnikPosebnogDela = new VlasnikPosebnogDela();
                    vlasnikPosebnogDela.setVlasnikId(rs.getLong("id"));
                    vlasnikPosebnogDela.setIme(rs.getString("ime"));
                    vlasnikPosebnogDela.setPrezime(rs.getString("prezime"));
                    vlasnikPosebnogDela.setBrojPosebnogDela(rs.getString("broj"));
                    vlasnikPosebnogDela.setVelicinaPosebnogDela(rs.getDouble("velicina"));
                    vlasnikPosebnogDela.setMernaJedinica((MernaJedinica.valueOf(rs.getString("mernajedinica"))));
                    vlasnikPosebnogDela.setKontaktVlasnika(rs.getString("kontakt"));

                    sednica.getVlasnici().add(vlasnikPosebnogDela);
                }
                //rs.close();
                sednica.setStambenaZajednica(stambenaZajednica);
                sednice.add(sednica);
            }
            // rs.close();
            statement.close();
            return sednice;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void add(SednicaSkupstine param) throws Exception {
        try {
            String sql = "INSERT into sednica_skupstine VALUES (?,?,?,?,?)";

            Connection connection = DbConnectionFactory.getInstance().getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, param.getSednicaSkupstineId());
            statement.setDate(2, new java.sql.Date(param.getDatumOdrzavanja().getTime()));
            statement.setInt(3, param.getBrojPrisutnih());
            statement.setString(4, param.getDnevniRed());
            statement.setLong(5, param.getStambenaZajednica().getStambenaZajednicaId());

            statement.executeUpdate();

            sql = "insert into sednica_vlasnik values (?,?)";
            statement = connection.prepareStatement(sql);
            for (VlasnikPosebnogDela vlasnik : param.getVlasnici()) {
                statement.setLong(1, param.getSednicaSkupstineId());
                statement.setLong(2, vlasnik.getVlasnikId());

                statement.executeUpdate();
            }
            statement.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Sednica skupstine nije sacuvana!");
        }
    }

    @Override
    public void edit(SednicaSkupstine param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(SednicaSkupstine param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
