/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.nprog.zgradeserver.main;

import rs.ac.bg.fon.nprog.zgradeserver.form.FrmMain;
import rs.ac.bg.fon.nprog.zgradeserver.form.controller.MainController;

/**
 *
 * @author Sara
 */
public class Main {
    public static void main(String[] args) {
         MainController mainController=new MainController(new FrmMain());
         mainController.openForm();
    }
}
