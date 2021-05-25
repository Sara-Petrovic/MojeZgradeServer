/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.nprog.zgradeserver.form;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

import rs.ac.bg.fon.nprog.zgradezajednicki.domain.Korisnik;

/**
 *
 * @author Sara
 */
public class ModelTabeleKlijenti extends AbstractTableModel {

    private List<Korisnik> korisnici;
    private String[] kolone;

    public ModelTabeleKlijenti() {
        korisnici = new ArrayList<>();
        kolone = new String[]{"Ime", "Prezime", "Username"};
    }

    @Override
    public int getRowCount() {
        return korisnici.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        switch (i1) {
            case 0:
                return korisnici.get(i).getIme();
            case 1:
                return korisnici.get(i).getPrezime();
            case 2:
                return korisnici.get(i).getKorisnickoIme();
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }

    public void dodajKorisnika(Korisnik korisnik) {
        if (korisnici.contains(korisnik)) {
            return;
        }
        korisnici.add(korisnik);
        fireTableDataChanged();
    }

    public void izbrisiKorisnika(Korisnik korisnik) {
        if (korisnik == null || !korisnici.contains(korisnik)) {
            return;
        }
        korisnici.remove(korisnik);
        fireTableDataChanged();
    }

    public void setKorisnici(List<Korisnik> korisnici) {
        this.korisnici = korisnici;
        fireTableDataChanged();
    }
    
}
