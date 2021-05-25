/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.nprog.zgradeserver.form.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import rs.ac.bg.fon.nprog.zgradeserver.form.FrmMain;
import rs.ac.bg.fon.nprog.zgradeserver.form.FrmSettings;
import rs.ac.bg.fon.nprog.zgradeserver.form.ModelTabeleKlijenti;
import rs.ac.bg.fon.nprog.zgradeserver.thread.ServerThread;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.Korisnik;

/**
 *
 * @author Sara
 */
public class MainController {

    private FrmMain frmMain;
    private ServerThread serverThread;
    private MainController instance;

    public MainController(FrmMain frmMain) {
        this.frmMain = frmMain;
        addActionListener();
        frmMain.getTblKorisnici().setModel(new ModelTabeleKlijenti());
        frmMain.getBtnStop().setEnabled(false);

    }

    public void openForm() {
        frmMain.setVisible(true); //otvara formu servera
        frmMain.getJlbStudent().setText("Sara Petrovic 58/17");
        frmMain.getJlbProgramName().setText("Dobrodosli u program Moje zgrade");
        frmMain.getJlbProgramName().setForeground(Color.red);
        
        
        
    }

    private void addActionListener() {
        frmMain.getBtnStart().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startServer();
                frmMain.getBtnStart().setEnabled(false);
                frmMain.getBtnStop().setEnabled(true);
            }

            private void startServer() {
                serverThread = new ServerThread(frmMain);
                serverThread.start();
                frmMain.getJlbProgramName().setForeground(Color.green);
            }
        });
        frmMain.getBtnStop().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopServer();
                frmMain.getBtnStop().setEnabled(false);
                frmMain.getBtnStart().setEnabled(true);
            }

            private void stopServer() {
                        serverThread.interrupt();
                        serverThread.stopSocket();
                        frmMain.getJlbProgramName().setForeground(Color.red);
                        ((ModelTabeleKlijenti)(frmMain.getTblKorisnici().getModel())).setKorisnici(new ArrayList<Korisnik>());
                    }
                });
                frmMain.getJmiSettings().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        SettingsController settingsController = new SettingsController(new FrmSettings(frmMain, true));
                        settingsController.openForm();
                    }
                });
            }

            public FrmMain getFrmMain() {
                return frmMain;
            }

        }
