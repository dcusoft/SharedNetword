/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cocum.sharednetword.controlador;


import com.cocum.sharednetword.service.ActualizaConectados;
import com.cocum.sharednetword.service.ThreadServer;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author COCUN
 */

public class ServidorController {
    ServerSocket ss;
    Socket s;
    DataInputStream dis;
    DataOutputStream dos;
    Thread servidor;
    ThreadServer server;
    ActualizaConectados actualizaConectados;
    Thread conectados;
    public void iniciarServer(int port){
        System.out.println("INICIANDO SERVIDOR");
        server=new ThreadServer(port);
        servidor=new Thread(server);
        servidor.start();
        System.out.println("INICIANDO SINCRONIZACION CONECTADOS");
        actualizaConectados=new ActualizaConectados();
        conectados = new Thread(actualizaConectados);
        conectados.start();
    }
    
     public void apagarServer(int port){
        server.apagarServer();
        actualizaConectados.apagarActualizar();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ServidorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
