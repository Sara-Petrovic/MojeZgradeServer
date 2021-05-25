/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.nprog.zgradeserver.thread;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.nprog.zgradeserver.form.FrmMain;

/**
 *
 * @author Sara
 */
public class ServerThread extends Thread {

    ServerSocket serverSocket;
    FrmMain frmMain;
    private List<Socket> listaSoketaKlijenata;
    private List<ProcessClientsRequests> listaNiti;

//    public ServerThread getInstance(FrmMain forma) {
//        if (instance == null || !instance.isAlive()) {
//            instance = new ServerThread(forma);
//        }
//        return instance;
//    }
    public ServerThread(FrmMain frmMain) {
        this.frmMain = frmMain;
        listaSoketaKlijenata = new ArrayList<>();
        listaNiti = new ArrayList<>();
    }

    @Override
    public void run() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("config/serverconfig.properties"));
            String port = properties.getProperty("port");
            serverSocket = new ServerSocket(Integer.parseInt(port));
            while (true) {
                System.out.println("Waiting for connection...");
                Socket socket = serverSocket.accept();
                listaSoketaKlijenata.add(socket);
                System.out.println("Connected:");
                handleClient(socket);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Serverska nit je prekinuta!");

        }

    }

    private void handleClient(Socket socket) {
        ProcessClientsRequests processClientsRequests = new ProcessClientsRequests(socket, frmMain);
        listaNiti.add(processClientsRequests);
        processClientsRequests.start();
    }

    public void stopSocket() {
        try {
            serverSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
