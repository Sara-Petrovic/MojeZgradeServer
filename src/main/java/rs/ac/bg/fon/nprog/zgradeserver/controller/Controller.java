/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.nprog.zgradeserver.controller;

import java.util.List;
import rs.ac.bg.fon.nprog.zgradeserver.operation.korisnik.LoginOperacija;
import rs.ac.bg.fon.nprog.zgradeserver.operation.mesto.UcitajListuMestaOperacija;
import rs.ac.bg.fon.nprog.zgradeserver.operation.sednicaskupstine.KreirajSednicuSkupstineOperacija;
import rs.ac.bg.fon.nprog.zgradeserver.operation.sednicaskupstine.NadjiSedniceSkupstinaOperacija;
import rs.ac.bg.fon.nprog.zgradeserver.operation.stambenazajednica.NadjiStambeneZajedniceOperacija;
import rs.ac.bg.fon.nprog.zgradeserver.operation.vlasnikposebnogdela.NadjiVlasnikePosebnihDelovaOperacija;
import rs.ac.bg.fon.nprog.zgradeserver.operation.stambenazajednica.ObrisiStambenuZajednicuOperacija;
import rs.ac.bg.fon.nprog.zgradeserver.operation.stambenazajednica.UcitajStambenuZajednicuOperacija;
import rs.ac.bg.fon.nprog.zgradeserver.operation.stambenazajednica.UnesiStambenuZajednicuOperacija;
import rs.ac.bg.fon.nprog.zgradeserver.operation.vlasnikposebnogdela.UnesiVlasnikaPosebnogDelaOperacija;
import rs.ac.bg.fon.nprog.zgradeserver.operation.sednicaskupstine.ZapamtiSednicuSkupstineOperacija;
import rs.ac.bg.fon.nprog.zgradeserver.operation.stambenazajednica.UcitajListuStambenihZajednicaOperacija;
import rs.ac.bg.fon.nprog.zgradeserver.operation.stambenazajednica.ZapamtiStambenuZajednicuOperacija;
import rs.ac.bg.fon.nprog.zgradeserver.operation.vlasnikposebnogdela.UcitajListuVlasnikaPosebnihDelovaOperacija;
import rs.ac.bg.fon.nprog.zgradeserver.operation.vlasnikposebnogdela.UcitajVlasnikaPosebnogDela;
import rs.ac.bg.fon.nprog.zgradeserver.operation.vlasnikposebnogdela.ZapamtiVlasnikaPosebnogDelaOperacija;
import rs.ac.bg.fon.nprog.zgradeserver.repository.Repository;
import rs.ac.bg.fon.nprog.zgradeserver.repository.db.DbRepository;
import rs.ac.bg.fon.nprog.zgradeserver.repository.db.impl.RepositoryDBGeneric;
import rs.ac.bg.fon.nprog.zgradeserver.repository.db.impl.RepositoryDBMesto;
import rs.ac.bg.fon.nprog.zgradeserver.repository.db.impl.RepositoryDBSednicaSkupstine;
import rs.ac.bg.fon.nprog.zgradeserver.repository.db.impl.RepositoryDBStambenaZajednica;
import rs.ac.bg.fon.nprog.zgradeserver.repository.db.impl.RepositoryDBVlasnikPosebnogDela;
import rs.ac.bg.fon.nprog.zgradeserver.repository.db.impl.RepositoryDbKorisnik;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.Korisnik;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.Mesto;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.SednicaSkupstine;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.StambenaZajednica;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.VlasnikPosebnogDela;

/**
 *
 * @author Sara
 */
public class Controller {

    private final Repository repositoryKorisnik;
    private final Repository repositoryMesto;
    private final Repository repositoryStambenaZajednica;
    private final Repository repositoryVlasnikPosebnogDela;
    private final Repository repositorySednicaSkupstine;
    private final Repository repositoryGeneric;

    private static Controller controller;

    private Controller() {
        this.repositoryKorisnik = new RepositoryDbKorisnik();
        this.repositoryMesto = new RepositoryDBMesto();
        this.repositoryStambenaZajednica = new RepositoryDBStambenaZajednica();
        this.repositoryVlasnikPosebnogDela = new RepositoryDBVlasnikPosebnogDela();
        this.repositorySednicaSkupstine = new RepositoryDBSednicaSkupstine();
        this.repositoryGeneric = new RepositoryDBGeneric();
    }

    public static Controller getInstance() {
        if (controller == null) {
            controller = new Controller();
        }
        return controller;
    }

    public Korisnik login(String username, String password) throws Exception {
        LoginOperacija operacija = new LoginOperacija();
        Korisnik korisnik = new Korisnik();
        korisnik.setKorisnickoIme(username);
        korisnik.setLozinka(password);
        operacija.execute(korisnik);
        return operacija.getKorisnik();
    }

    public List<Mesto> ucitajListuMesta() throws Exception {
        UcitajListuMestaOperacija operacija = new UcitajListuMestaOperacija();
        operacija.execute(new Mesto());
        return operacija.getMesta();
    }

    public void unesiStambenuZajednicu(StambenaZajednica stambenaZajednica) throws Exception {
        UnesiStambenuZajednicuOperacija operacija = new UnesiStambenuZajednicuOperacija();
        operacija.execute(stambenaZajednica);
    }

    public List<StambenaZajednica> ucitajListuStambenihZajednica() throws Exception {//getAll
        UcitajListuStambenihZajednicaOperacija operacija = new UcitajListuStambenihZajednicaOperacija();
        operacija.execute(new StambenaZajednica());
        return operacija.getStambeneZajednice();
    }

    public void obrisiStambenuZajednicu(StambenaZajednica stambenaZajednica) throws Exception {
        ObrisiStambenuZajednicuOperacija operacija = new ObrisiStambenuZajednicuOperacija();
        operacija.execute(stambenaZajednica);
    }

    public void zapamtiStambenuZajednicu(StambenaZajednica stambenaZajednica) throws Exception {//edit
        ZapamtiStambenuZajednicuOperacija operacija = new ZapamtiStambenuZajednicuOperacija();
        operacija.execute(stambenaZajednica);
    }

    public List<StambenaZajednica> nadjiStambeneZajednice(StambenaZajednica sz) throws Exception {
        NadjiStambeneZajedniceOperacija operacija = new NadjiStambeneZajedniceOperacija();
        operacija.execute(sz);
        return operacija.getStambeneZajednice();
    }

    public StambenaZajednica ucitajStambenuZajednicu(StambenaZajednica sz) throws Exception {
        UcitajStambenuZajednicuOperacija operacija = new UcitajStambenuZajednicuOperacija();
        operacija.execute(sz);
        return operacija.getStambenaZajednica();
    }

    public void unesiVlasnikaPosebnogDela(VlasnikPosebnogDela vlasnikPosebnogDela) throws Exception {
        UnesiVlasnikaPosebnogDelaOperacija operacija = new UnesiVlasnikaPosebnogDelaOperacija();
        operacija.execute(vlasnikPosebnogDela);
    }

    public void zapamtiVlasnikaPosebnogDela(VlasnikPosebnogDela vlasnik) throws Exception {
        ZapamtiVlasnikaPosebnogDelaOperacija operacija = new ZapamtiVlasnikaPosebnogDelaOperacija();
        operacija.execute(vlasnik);
    }

    public List<VlasnikPosebnogDela> nadjiVlasnikePosebnihDelova(VlasnikPosebnogDela vlasnik) throws Exception {
        NadjiVlasnikePosebnihDelovaOperacija operacija = new NadjiVlasnikePosebnihDelovaOperacija();
        operacija.execute(vlasnik);
        return operacija.getVlasnici();

    }

    public List<VlasnikPosebnogDela> ucitajListuVlasnikaPosebnihDelova() throws Exception {
        UcitajListuVlasnikaPosebnihDelovaOperacija operacija = new UcitajListuVlasnikaPosebnihDelovaOperacija();
        operacija.execute(new VlasnikPosebnogDela());
        return operacija.getVlasnici();
    }

    public VlasnikPosebnogDela ucitajVlasnikaPosebnogDela(VlasnikPosebnogDela vlasnik) throws Exception {
        UcitajVlasnikaPosebnogDela operacija = new UcitajVlasnikaPosebnogDela();
        operacija.execute(vlasnik);
        return operacija.getVlasnikPosebnogDela();
    }

    public SednicaSkupstine kreirajSednicuSkupstine(SednicaSkupstine sednicaSkupstine) throws Exception {
        KreirajSednicuSkupstineOperacija operacija = new KreirajSednicuSkupstineOperacija();
        operacija.execute(sednicaSkupstine);
        return sednicaSkupstine;
    }

    public void zapamtiSednicuSkupstine(SednicaSkupstine sednicaSkupstine) throws Exception {
        ZapamtiSednicuSkupstineOperacija operacija = new ZapamtiSednicuSkupstineOperacija();
        operacija.execute(sednicaSkupstine);
    }


    public List<SednicaSkupstine> nadjiSedniceSkupstina(SednicaSkupstine sednicaSkupstine) throws Exception {
        NadjiSedniceSkupstinaOperacija operacija = new NadjiSedniceSkupstinaOperacija();
        operacija.execute(sednicaSkupstine);
        return operacija.getListaSednica();
    }

}
