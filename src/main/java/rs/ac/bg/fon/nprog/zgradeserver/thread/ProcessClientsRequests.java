/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.nprog.zgradeserver.thread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.nprog.zgradeserver.controller.Controller;
import rs.ac.bg.fon.nprog.zgradeserver.form.FrmMain;
import rs.ac.bg.fon.nprog.zgradeserver.form.ModelTabeleKlijenti;
import rs.ac.bg.fon.nprog.zgradezajednicki.communication.Receiver;
import rs.ac.bg.fon.nprog.zgradezajednicki.communication.Request;
import rs.ac.bg.fon.nprog.zgradezajednicki.communication.Response;
import rs.ac.bg.fon.nprog.zgradezajednicki.communication.Sender;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.Korisnik;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.SednicaSkupstine;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.StambenaZajednica;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.VlasnikPosebnogDela;

/**
 *
 * @author Sara
 */
public class ProcessClientsRequests extends Thread {

    Socket socket;//radi sa klijentom
    FrmMain frmMain;
    Korisnik korisnik2;
    private boolean kraj = false;

    public ProcessClientsRequests(Socket socket, FrmMain frmMain) {
        this.socket = socket;
        this.frmMain = frmMain;
    }

    @Override
    public void run() {
        Sender sender = new Sender(socket);
        Receiver receiver = new Receiver(socket);
        while (!kraj) {
            try {
                Request request = (Request) primiZahtev();
             

                Response response = new Response();//ili je nesto ili exception
                try {
    
                    switch (request.getOperation()) {
                        case LOGIN:
                            Korisnik korisnik = (Korisnik) request.getArgument();//salje nam usera klijent preko requesta
                            response.setResult(Controller.getInstance().login(korisnik.getKorisnickoIme(), korisnik.getLozinka()));//ako pukne login bice exeption
                            Korisnik ulogovaniKorisnik = (Korisnik) response.getResult();
                            ((ModelTabeleKlijenti) frmMain.getTblKorisnici().getModel()).dodajKorisnika(ulogovaniKorisnik);

                            this.korisnik2 = ulogovaniKorisnik;
                            break;
                        case UCITAJ_LISTU_MESTA:
                            response.setResult(Controller.getInstance().ucitajListuMesta());
                            break;
                        case UCITAJ_LISTU_STAMBENIH_ZAJEDNICA:
                            response.setResult(Controller.getInstance().ucitajListuStambenihZajednica());
                            break;
                        case UCITAJ_LISTU_VLASNIKA_POSEBNIH_DELOVA:
                            response.setResult(Controller.getInstance().ucitajListuVlasnikaPosebnihDelova());
                            break;
                        case ZAPAMTI_STAMBENU_ZAJEDNICU:
                            StambenaZajednica sz1 = (StambenaZajednica) request.getArgument();
                            Controller.getInstance().zapamtiStambenuZajednicu(sz1);
                            break;
                        case OBRISI_STAMBENU_ZAJEDNICU:
                            StambenaZajednica sz2 = (StambenaZajednica) request.getArgument();
                            Controller.getInstance().obrisiStambenuZajednicu(sz2);
                            break;
                        case NADJI_STAMBENE_ZAJEDNICE:
                            StambenaZajednica sz3 = (StambenaZajednica) request.getArgument();
                            response.setResult(Controller.getInstance().nadjiStambeneZajednice(sz3));
                            break;
                        case UCITAJ_STAMBENU_ZAJEDNICU:
                            StambenaZajednica sz4 = (StambenaZajednica) request.getArgument();
                            response.setResult(Controller.getInstance().ucitajStambenuZajednicu(sz4));
                            break;
                        case NADJI_VLASNIKE_POSEBNIH_DELOVA:
                            VlasnikPosebnogDela v1 = (VlasnikPosebnogDela) request.getArgument();
                            response.setResult(Controller.getInstance().nadjiVlasnikePosebnihDelova(v1));
                            break;
                        case UNESI_VLASNIKA_POSEBNOG_DELA:
                            VlasnikPosebnogDela v2 = (VlasnikPosebnogDela) request.getArgument();
                            Controller.getInstance().unesiVlasnikaPosebnogDela(v2);
                            break;
                        case ZAPAMTI_VLASNIKA_POSEBNOG_DELA:
                            VlasnikPosebnogDela v3 = (VlasnikPosebnogDela) request.getArgument();
                            Controller.getInstance().zapamtiVlasnikaPosebnogDela(v3);
                            break;
                        case UCITAJ_VLASNIKA_POSEBNOG_DELA:
                            VlasnikPosebnogDela v4 = (VlasnikPosebnogDela) request.getArgument();
                            response.setResult(Controller.getInstance().ucitajVlasnikaPosebnogDela(v4));
                            break;
                        case KREIRAJ_SEDNICU_SKUPSTINE:
                            SednicaSkupstine s1 = (SednicaSkupstine) request.getArgument();
                            response.setResult(Controller.getInstance().kreirajSednicuSkupstine(s1));
                            break;
                        case ZAPAMTI_SEDNICU_SKUPSTINE:
                            SednicaSkupstine ss1 = (SednicaSkupstine) request.getArgument();
                            Controller.getInstance().zapamtiSednicuSkupstine(ss1);
                            break;
                        case NADJI_SEDNICE_SKUPSTINA:
                            SednicaSkupstine s2 = (SednicaSkupstine) request.getArgument();
                            response.setResult(Controller.getInstance().nadjiSedniceSkupstina(s2));
                            break;

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    response.setException(e);
                }
                posaljiOdgovor(response);//vracamo klijentu odgovor

            } catch (Exception ex) {
                Logger.getLogger(ProcessClientsRequests.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        ((ModelTabeleKlijenti) frmMain.getTblKorisnici().getModel()).izbrisiKorisnika(korisnik2);
    }

    private Request primiZahtev() {
        Request request = new Request();

        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            request = (Request) ois.readObject();
        } catch (Exception ex) {
            System.out.println("Klijent je prekinuo konekciju");
            kraj = true;
        }

        return request;
    }

    private void posaljiOdgovor(Response response) {
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(response);
        } catch (IOException ex) {
            Logger.getLogger(ProcessClientsRequests.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void setKraj(boolean kraj) {
        this.kraj = kraj;
    }

}
