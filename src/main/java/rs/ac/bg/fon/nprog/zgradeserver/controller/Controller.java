/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.nprog.zgradeserver.controller;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

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
 * Kontroler koji instancira i poziva sistemske operacije.
 * 
 * 
 * @author Sara
 * @version 0.1
 *
 */
public class Controller {


	/**
	 * Kontroler kao Controller. Potreban je za realizaciju Singlton paterna.
	 */
    private static Controller controller;

    /**
	 * Kontruktor koji inicijalizuje objekat i nista vise.
	 */
    private Controller() {
       
    }

    /**
	 * Vraca jedinstvenu instancu klase Controller.
	 * 
	 * @return Kontroler kao Controller.
	 */
    public static Controller getInstance() {
        if (controller == null) {
            controller = new Controller();
        }
        return controller;
    }

    /**
	 * Izvrsava prijavu korisnika na sistem.
	 * 
	 * @param username Username korisnika kao String.
	 * @param password Lozinka korisnika kao String.
	 *
	 * @return Korisnik koji je ulogovan ukoliko je unet ispravan username i password.
	 * 
	 * @throws java.lang.Exception ako dodje do greske prilikom izvrsavanja prijave korisnika
	 */
    public Korisnik login(String username, String password) throws Exception {
        LoginOperacija operacija = new LoginOperacija();
        Korisnik korisnik = new Korisnik();
        korisnik.setKorisnickoIme(username);
        korisnik.setLozinka(password);
        operacija.execute(korisnik);
        return operacija.getKorisnik();
    }

    /**
	 * Ucitava listu svih mesta koja postoje u bazi. Koristi se prilikom popunjavanja combo box-a.
	 * 
     * @return Lista sa svim mestima iz baze.
	 * 
	 * @throws java.lang.Exception ako dodje do greske prilikom izvrsavanja sistemske operacije UcitajListuMestaOperacija.
	 */
    public List<Mesto> ucitajListuMesta() throws Exception {
        UcitajListuMestaOperacija operacija = new UcitajListuMestaOperacija();
        operacija.execute(new Mesto());
        return operacija.getMesta();
    }

    /**
	 * Unosi novu stambenu zajednicu u bazu.
	 * 
	 * @param stambenaZajednica Stambena zajednica koju treba uneti u bazu.
	 * 
	 * @throws java.lang.Exception ako dodje do greske prilikom izvrsavanja sistemske operacije UnesiStambenuZajednicuOperacija.
	 */
    public void unesiStambenuZajednicu(StambenaZajednica stambenaZajednica) throws Exception {
        UnesiStambenuZajednicuOperacija operacija = new UnesiStambenuZajednicuOperacija();
        operacija.execute(stambenaZajednica);
    }

    /**
	 * Ucitava sve stambene zajednice.
	 * 
     * @return Lista sa svim stambenim zajednicama iz baze.
	 * 
	 * @throws java.lang.Exception ako dodje do greske prilikom izvrsavanja sistemske operacije UcitajListuStambenihZajednicaOperacija.
	 */
    public List<StambenaZajednica> ucitajListuStambenihZajednica() throws Exception {//getAll
        UcitajListuStambenihZajednicaOperacija operacija = new UcitajListuStambenihZajednicaOperacija();
        operacija.execute(new StambenaZajednica());
        return operacija.getStambeneZajednice();
    }

    /**
	 * Brise stambenu zajednicu iz baze.
	 * 
	 * @param stambenaZajednica Stambena zajednica koju treba obrisati.
	 * 
	 * @throws java.lang.Exception ako dodje do greske prilikom izvrsavanja sistemske operacije ObrisiStambenuZajednicuOperacija.
	 */
    public void obrisiStambenuZajednicu(StambenaZajednica stambenaZajednica) throws Exception {
        ObrisiStambenuZajednicuOperacija operacija = new ObrisiStambenuZajednicuOperacija();
        operacija.execute(stambenaZajednica);
    }

    /**
   	 * Menja podatke postojece stambene zajednice u bazi.
   	 * 
   	 * @param stambenaZajednica Stambena zajednica koju treba upisati u bazu umesto postojece stambene zajednice sa istim id-jem.
   	 * 
   	 * @throws java.lang.Exception ako dodje do greske prilikom izvrsavanja sistemske operacije ZapamtiStambenuZajednicuOperacija.
   	 */
    public void zapamtiStambenuZajednicu(StambenaZajednica stambenaZajednica) throws Exception {//edit
        ZapamtiStambenuZajednicuOperacija operacija = new ZapamtiStambenuZajednicuOperacija();
        operacija.execute(stambenaZajednica);
    }

    /**
   	 * Pronalazi sve stambene zajednice koje ispunjavaju zadate uslove.
   	 * 
   	 * @param stambenaZajednica Stambena zajednica na osnovu koje treba pronaci odgovarajuce stambene zajednice u bazi.
   	 * 
   	 * @return Lista sa pronadjenim stambenim zajednicama.
   	 * 
   	 * @throws java.lang.Exception ako dodje do greske prilikom izvrsavanja sistemske operacije NadjiStambeneZajedniceOperacija.
   	 */
    public List<StambenaZajednica> nadjiStambeneZajednice(StambenaZajednica sz) throws Exception {
        NadjiStambeneZajedniceOperacija operacija = new NadjiStambeneZajedniceOperacija();
        operacija.execute(sz);
        return operacija.getStambeneZajednice();
    }

    /**
   	 * Pronalazi odgovarajucu stambenu zajednicu i ucitava je.
   	 * 
   	 * @param stambenaZajednica Stambena zajednica na osnovu koje se vrsi pretraga u bazi.
   	 * 
   	 * @return Stambena zajednica koja je pronadjena.
   	 * 
   	 * @throws java.lang.Exception ako dodje do greske prilikom izvrsavanja sistemske operacije UcitajStambenuZajednicuOperacija.
   	 */
    public StambenaZajednica ucitajStambenuZajednicu(StambenaZajednica sz) throws Exception {
        UcitajStambenuZajednicuOperacija operacija = new UcitajStambenuZajednicuOperacija();
        operacija.execute(sz);
        return operacija.getStambenaZajednica();
    }

    /**
   	 * Unosi novog vlasnika posebnog dela u bazu.
   	 * 
   	 * @param vlasnikPosebnogDela Vlasnik posebnog dela koga treba uneti u bazu.
   	 *    	 
   	 * @throws java.lang.Exception ako dodje do greske prilikom izvrsavanja sistemske operacije UnesiVlasnikaPosebnogDelaOperacija.
   	 */
    public void unesiVlasnikaPosebnogDela(VlasnikPosebnogDela vlasnikPosebnogDela) throws Exception {
        UnesiVlasnikaPosebnogDelaOperacija operacija = new UnesiVlasnikaPosebnogDelaOperacija();
        operacija.execute(vlasnikPosebnogDela);
    }

    /**
   	 * Menja podatke postojeceg vlasnika posebnog dela u bazi.
   	 * 
   	 * @param vlasnikPosebnogDela Vlasnik posebnog dela koji treba da bude upisan u bazu umesto postojeceg vlasnika.
   	 *    	 
   	 * @throws java.lang.Exception ako dodje do greske prilikom izvrsavanja sistemske operacije ZapamtiVlasnikaPosebnogDelaOperacija.
   	 */
    public void zapamtiVlasnikaPosebnogDela(VlasnikPosebnogDela vlasnik) throws Exception {
        ZapamtiVlasnikaPosebnogDelaOperacija operacija = new ZapamtiVlasnikaPosebnogDelaOperacija();
        operacija.execute(vlasnik);
    }

    /**
   	 * Pronalazi sve vlasnike posebnih delova koji ispunjavaju zadate kriterijume.
   	 * 
   	 * @param vlasnikPosebnogDela Vlasnik posebnog dela na osnovu koga treba pronaci odgovarajuce vlasnike u bazi.
   	 * 
   	 * @return Lista sa pronadjenim vlasnicima posebnog dela.
   	 * 
   	 * @throws java.lang.Exception ako dodje do greske prilikom izvrsavanja sistemske operacije NadjiVlasnikePosebnihDelovaOperacija.
   	 */
    public List<VlasnikPosebnogDela> nadjiVlasnikePosebnihDelova(VlasnikPosebnogDela vlasnik) throws Exception {
        NadjiVlasnikePosebnihDelovaOperacija operacija = new NadjiVlasnikePosebnihDelovaOperacija();
        operacija.execute(vlasnik);
        return operacija.getVlasnici();

    }

    /**
   	 * Pronalazi sve vlasnike posebnog dela i ucitava ih. Koristi se za popunjavanje combo boxa.
   	 * 
   	 * 
   	 * @return Lista sa svim vlasnicima posebnog dela.
   	 * 
   	 * @throws java.lang.Exception ako dodje do greske prilikom izvrsavanja sistemske operacije UcitajListuVlasnikaPosebnihDelovaOperacija.
   	 */
    public List<VlasnikPosebnogDela> ucitajListuVlasnikaPosebnihDelova() throws Exception {
        UcitajListuVlasnikaPosebnihDelovaOperacija operacija = new UcitajListuVlasnikaPosebnihDelovaOperacija();
        operacija.execute(new VlasnikPosebnogDela());
        return operacija.getVlasnici();
    }

    /**
   	 * Pronalazi vlasnika posebnog dela koji ispunjava zadate kriterijume i ucitava ga.
   	 * 
   	 * @param vlasnikPosebnogDela Vlasnik posebnog dela na osnovu koga treba pronaci vlasnika u bazi.
   	 * 
   	 * @return Vlasnik posebnog dela koji je pronadjen.
   	 * 
   	 * @throws java.lang.Exception ako dodje do greske prilikom izvrsavanja sistemske operacije UcitajVlasnikaPosebnogDela.
   	 */
    public VlasnikPosebnogDela ucitajVlasnikaPosebnogDela(VlasnikPosebnogDela vlasnik) throws Exception {
        UcitajVlasnikaPosebnogDela operacija = new UcitajVlasnikaPosebnogDela();
        operacija.execute(vlasnik);
        return operacija.getVlasnikPosebnogDela();
    }

    /**
   	 * Kreira novu sednicu skupstine i upisuje je u bazu.
   	 * 
   	 * @param sednicaSkupstine Sednica skupstine koju treba upisati u bazu.
   	 * 
   	 * @return Sednica skupstine koja je kreirana.
   	 * 
   	 * @throws java.lang.Exception ako dodje do greske prilikom izvrsavanja sistemske operacije KreirajSednicuSkupstineOperacija.
   	 */
    public SednicaSkupstine kreirajSednicuSkupstine(SednicaSkupstine sednicaSkupstine) throws Exception {
        KreirajSednicuSkupstineOperacija operacija = new KreirajSednicuSkupstineOperacija();
        operacija.execute(sednicaSkupstine);
        return sednicaSkupstine;
    }

    /**
   	 * Menja postojecu sednicu skupstine u bazi.
   	 * 
   	 * @param sednicaSkupstine Sednica skupstine koja treba da bude upisana u bazu umesto postojece.
   	 * 
   	 * @throws java.lang.Exception ako dodje do greske prilikom izvrsavanja sistemske operacije ZapamtiSednicuSkupstineOperacija.
   	 */
    public void zapamtiSednicuSkupstine(SednicaSkupstine sednicaSkupstine) throws Exception {
        ZapamtiSednicuSkupstineOperacija operacija = new ZapamtiSednicuSkupstineOperacija();
        operacija.execute(sednicaSkupstine);
        List<SednicaSkupstine> sednice=null;
        //upisujem u json fajl, prvo citam sve postojece sednice pa dodam novu na njih:
        try(FileReader fileR = new FileReader("sedniceskupstine.json")){//try with resources, automatski ce zatvoriti filewriter cak i ako pukne exception
        	
        	Type typeToken = new TypeToken<LinkedList<SednicaSkupstine>>(){}.getType();
			
        	Gson gson = new Gson();
			sednice = gson.fromJson(fileR, typeToken);
			if(sednice!=null) {
			sednice.add(sednicaSkupstine);
			System.out.println("nije null");
			}else {
				sednice = new LinkedList<SednicaSkupstine>();
				sednice.add(sednicaSkupstine);
			}
        	
		}catch(Exception e) {
			e.printStackTrace();
		}
        try(FileWriter fileW = new FileWriter("sedniceskupstine.json")){//try with resources, automatski ce zatvoriti filewriter cak i ako pukne exception
        	
        
        	Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();

			gson.toJson(sednice, fileW); 
			
		}catch(Exception e) {
			e.printStackTrace();
		}
    }


    /**
   	 * Pronalazi sve sednice skupstine koje zadovoljavaju zadate kriterijume i vraca ih kao listu.
   	 * 
   	 * @param sednicaSkupstine Sednica skupstine na osnovu koje se vrsi pretraga postojecih sednica.
   	 * 
   	 * @return Lista pronadjenih sednica skupstine.
   	 * 
   	 * @throws java.lang.Exception ako dodje do greske prilikom izvrsavanja sistemske operacije NadjiSedniceSkupstinaOperacija.
   	 */
    public List<SednicaSkupstine> nadjiSedniceSkupstina(SednicaSkupstine sednicaSkupstine) throws Exception {
        NadjiSedniceSkupstinaOperacija operacija = new NadjiSedniceSkupstinaOperacija();
        operacija.execute(sednicaSkupstine);
        return operacija.getListaSednica();
    }

}
