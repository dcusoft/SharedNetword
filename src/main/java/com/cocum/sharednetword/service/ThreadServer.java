/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cocum.sharednetword.service;


import com.cocum.sharednetword.dao.Usuario;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author COCUN
 */
public class ThreadServer implements Runnable {

    ServerSocket ss;
    Socket s;
    int port;
    boolean activo;
    public static List<Usuario> listUsuario;
    Usuario user;
    
 
    public ThreadServer(int port) {
        this.port = port;
        activo = true;
    }

    @Override
    public void run() {
        try {
            listUsuario=new ArrayList<>();
            ss = new ServerSocket(port);
            System.out.println("SERVIDOR INICIADO");
            while (activo) {
                s = ss.accept();
                System.out.println("CLIENTE CONECTADO: " + s.isConnected());
                if (!s.isClosed()) {
                    Thread atencion = new Thread(new AtencionService(s));
                    atencion.start();
                }
            }
            
            
        } catch (IOException ex) {
            System.out.println("SERVIDOR TERMINADO");
            Logger.getLogger(ThreadServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void apagarServer() {
        activo = false;
        try {
            
            System.out.println("ss.isClosed()"+ss.isClosed());
            if(!ss.isClosed()){
            ss.close();
            }
            System.out.println("s.isConnected()"+s.isConnected());
            if(s.isConnected()){
            s.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(ThreadServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
