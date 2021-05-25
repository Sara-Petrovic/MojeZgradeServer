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
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.Mesto;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.StambenaZajednica;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.VlasnikPosebnogDela;

/**
 *
 * @author Sara
 */
public class RepositoryDBVlasnikPosebnogDela implements DbRepository<VlasnikPosebnogDela> {

    @Override
    public List<VlasnikPosebnogDela> getAll(VlasnikPosebnogDela param) {
        try {
            String sql = "select v.vlasnikid as id,v.ime as ime,v.prezime as prezime, v.brojposebnogdela as broj, v.velicinaposebnogdela as velicina, "
                    + " v.mernajedinica as mernajedinica, v.kontaktvlasnika as kontakt, v.stambenazajednicaid as stambenazajednicaid, "
                    + "s.stambenazajednicaid as sid, s.mesto as mestoid, s.ulica as ulica, s.broj as sbroj, "
                    + "s.banka as banka, s.tekuciracun as racun, s.pib as pib, s.maticnibroj as  maticnibroj,"
                    + "m.naziv as mnaziv, m.ptt as mptt"
                    + " from vlasnik_posebnog_dela AS v inner join stambena_zajednica AS s on (s.stambenazajednicaid = v.stambenazajednicaid) "
                    + "inner join mesto AS m on (s.mesto = m.mestoid)";

            List<VlasnikPosebnogDela> vlasniciPosebnogDela = new ArrayList<>();
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                VlasnikPosebnogDela vlasnikPosebnogDela = new VlasnikPosebnogDela();
                vlasnikPosebnogDela.setVlasnikId(rs.getLong("id"));
                vlasnikPosebnogDela.setIme(rs.getString("ime"));
                vlasnikPosebnogDela.setPrezime(rs.getString("prezime"));
                vlasnikPosebnogDela.setBrojPosebnogDela(rs.getString("broj"));
                vlasnikPosebnogDela.setVelicinaPosebnogDela(rs.getDouble("velicina"));
                vlasnikPosebnogDela.setMernaJedinica((MernaJedinica.valueOf(rs.getString("mernajedinica"))));
                vlasnikPosebnogDela.setKontaktVlasnika(rs.getString("kontakt"));

                StambenaZajednica stambenaZajednica = new StambenaZajednica();
                stambenaZajednica.setStambenaZajednicaId(rs.getLong("stambenazajednicaid"));
                stambenaZajednica.setUlica(rs.getString("ulica"));
                stambenaZajednica.setBroj(rs.getString("sbroj"));
                stambenaZajednica.setTekuciRacun(rs.getString("racun"));
                stambenaZajednica.setBanka(rs.getString("banka"));
                stambenaZajednica.setPib(rs.getString("pib"));
                stambenaZajednica.setMaticniBroj(rs.getString("maticnibroj"));

                Mesto mesto = new Mesto();
                mesto.setMestoId(rs.getLong("mestoid"));
                mesto.setNaziv(rs.getString("mnaziv"));
                mesto.setPtt(rs.getString("mptt"));
                stambenaZajednica.setMesto(mesto);

                vlasnikPosebnogDela.setStambenaZajednica(stambenaZajednica);
                vlasniciPosebnogDela.add(vlasnikPosebnogDela);
            }
            rs.close();
            statement.close();
            return vlasniciPosebnogDela;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<VlasnikPosebnogDela> get(VlasnikPosebnogDela vlasnik) {
        try {
            String prezime = vlasnik.getPrezime();
            if(prezime==null)
                prezime = "";
            String sql = "select v.vlasnikid as id,v.ime as ime,v.prezime as prezime, v.brojposebnogdela as broj, v.velicinaposebnogdela as velicina, "
                    + " v.mernajedinica as mernajedinica, v.kontaktvlasnika as kontakt, v.stambenazajednicaid as stambenazajednicaid, "
                    + "s.stambenazajednicaid as sid, s.mesto as mestoid, s.ulica as ulica, s.broj as sbroj, "
                    + "s.banka as banka, s.tekuciracun as racun, s.pib as pib, s.maticnibroj as  maticnibroj,"
                    + "m.naziv as mnaziv, m.ptt as mptt"
                    + " from vlasnik_posebnog_dela AS v inner join stambena_zajednica AS s on (s.stambenazajednicaid = v.stambenazajednicaid) "
                    + "inner join mesto AS m on (s.mesto = m.mestoid) "
                    + "where v.prezime like '%" + prezime + "%' ";

            //za sednice: 
            StambenaZajednica sz = vlasnik.getStambenaZajednica();
            if(sz!=null){
                sql+= " and s.stambenazajednicaid="+sz.getStambenaZajednicaId();
            }
            List<VlasnikPosebnogDela> vlasniciPosebnogDela = new ArrayList<>();
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                VlasnikPosebnogDela vlasnikPosebnogDela = new VlasnikPosebnogDela();
                vlasnikPosebnogDela.setVlasnikId(rs.getLong("id"));
                vlasnikPosebnogDela.setIme(rs.getString("ime"));
                vlasnikPosebnogDela.setPrezime(rs.getString("prezime"));
                vlasnikPosebnogDela.setBrojPosebnogDela(rs.getString("broj"));
                vlasnikPosebnogDela.setVelicinaPosebnogDela(rs.getDouble("velicina"));
                vlasnikPosebnogDela.setMernaJedinica((MernaJedinica.valueOf(rs.getString("mernajedinica"))));
                vlasnikPosebnogDela.setKontaktVlasnika(rs.getString("kontakt"));

                StambenaZajednica stambenaZajednica = new StambenaZajednica();
                stambenaZajednica.setStambenaZajednicaId(rs.getLong("stambenazajednicaid"));
                stambenaZajednica.setUlica(rs.getString("ulica"));
                stambenaZajednica.setBroj(rs.getString("sbroj"));
                stambenaZajednica.setTekuciRacun(rs.getString("racun"));
                stambenaZajednica.setBanka(rs.getString("banka"));
                stambenaZajednica.setPib(rs.getString("pib"));
                stambenaZajednica.setMaticniBroj(rs.getString("maticnibroj"));

                Mesto mesto = new Mesto();
                mesto.setMestoId(rs.getLong("mestoid"));
                mesto.setNaziv(rs.getString("mnaziv"));
                mesto.setPtt(rs.getString("mptt"));
                stambenaZajednica.setMesto(mesto);

                vlasnikPosebnogDela.setStambenaZajednica(stambenaZajednica);
                vlasniciPosebnogDela.add(vlasnikPosebnogDela);
            }
            rs.close();
            statement.close();
            return vlasniciPosebnogDela;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void add(VlasnikPosebnogDela param) throws Exception {
        try {
            String sql = "INSERT into vlasnik_posebnog_dela VALUES (?,?,?,?,?,?,?,?)";

            Connection connection = DbConnectionFactory.getInstance().getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, param.getVlasnikId());
            statement.setString(2, param.getIme());
            statement.setString(3, param.getPrezime());
            statement.setString(4, param.getBrojPosebnogDela());
            statement.setDouble(5, param.getVelicinaPosebnogDela());
            statement.setString(6, param.getMernaJedinica().toString());
            statement.setString(7, param.getKontaktVlasnika());
            statement.setLong(8, param.getStambenaZajednica().getStambenaZajednicaId());

            statement.executeUpdate();
            statement.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Vlasnik posebnog dela nije sacuvan!");
        }
    }

    @Override
    public void edit(VlasnikPosebnogDela param) throws Exception {
        try {
            String sql = "UPDATE vlasnik_posebnog_dela SET "
                    + "ime='" + param.getIme() + "', "
                    + "prezime='" + param.getPrezime() + "', "
                    + "kontaktvlasnika='" + param.getKontaktVlasnika() + "',"
                    + "brojposebnogdela='" + param.getBrojPosebnogDela() + "',"
                    + "velicinaposebnogdela=" + param.getVelicinaPosebnogDela() + ", "
                    + "mernajedinica='" + param.getMernaJedinica() + "', "
                    + "stambenazajednicaid=" + param.getStambenaZajednica().getStambenaZajednicaId() + " "
                    + "WHERE vlasnikid=" + param.getVlasnikId();
            System.out.println(sql);
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Greska prilikom izmene vlasnika posebnog dela: \n" + ex.getMessage());
        }
    }

    @Override
    public void delete(VlasnikPosebnogDela param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
