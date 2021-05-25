/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.nprog.zgradeserver.form.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.nprog.zgradeserver.form.FrmSettings;

/**
 *
 * @author Sara
 */
public class SettingsController {

    FrmSettings frmSettings;

    public SettingsController(FrmSettings frmSettings) {
        this.frmSettings = frmSettings;
    }

    public void openForm() {
        addActionListeners();
        frmSettings.setLocationRelativeTo(null);
        try {
            ucitajPodatkeUFormu();
        } catch (Exception ex) {
            Logger.getLogger(SettingsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        frmSettings.setVisible(true);
    }

    private void addActionListeners() {
        frmSettings.getBtnSave().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sacuvajDatabaseConfigPodatke();
            }

            private void sacuvajDatabaseConfigPodatke() {
                FileOutputStream out = null;
                try {
                    Properties props = new Properties();
                    out = new FileOutputStream("config/dbconfig.properties");
                    props.setProperty("url", frmSettings.getTxtUrl().getText());
                    props.setProperty("username", frmSettings.getTxtUsername().getText());
                    props.setProperty("password",new String( frmSettings.getTxtPassword().getPassword()));
                    props.store(out, null);
                    out.close();
                    JOptionPane.showMessageDialog(frmSettings, "Uspesno ste promenili database config podatke.");
                    frmSettings.dispose();
                } catch (Exception ex) {
                    Logger.getLogger(SettingsController.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        out.close();
                    } catch (IOException ex) {
                        Logger.getLogger(SettingsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }

    private void ucitajPodatkeUFormu() throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("config/dbconfig.properties"));
        String url = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
        frmSettings.getTxtUrl().setText(url);
        frmSettings.getTxtUsername().setText(username);
        frmSettings.getTxtPassword().setText(password);
    }

}
