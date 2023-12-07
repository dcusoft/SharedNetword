/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cocum.sharednetword.controlador;

import com.cocum.sharednetword.dao.Mensaje;
import com.cocum.sharednetword.service.ThreadAtencionCliente;
import com.cocum.sharednetword.service.ThreadServer;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
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
    Thread conectados;
    boolean activo;
    public static Set<ThreadAtencionCliente> clientes = new HashSet<>();

    public ServidorController() {
        this.activo = true;
    }

    public void iniciarServer(int port) {
        Logger.getLogger(ServidorController.class.getName()).log(Level.INFO, "INICIANDO SERVIDOR");
        try {
            ss = new ServerSocket(port);
            
            Logger.getLogger(ServidorController.class.getName()).log(Level.INFO, "SERVIDOR CREADO");
            server = new ThreadServer(this,ss);
            servidor = new Thread(server);
            servidor.start();
        } catch (IOException ex) {
            Logger.getLogger(ServidorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void broadcast(Mensaje mensaje, ThreadAtencionCliente sender) {
        Logger.getLogger(ServidorController.class.getName()).log(Level.INFO, "MENSAJE A ENVIAR: "+mensaje.getMensaje().toString());
        
        Logger.getLogger(ServidorController.class.getName()).log(Level.INFO, "lista clientes:"+clientes.size());
        for (ThreadAtencionCliente client : clientes) {
                Logger.getLogger(ServidorController.class.getName()).log(Level.INFO, "SE ENVIA A :"+mensaje.getUsuario());
                client.send(mensaje);
        }
        if(mensaje.getTipoMensaje() == 2){
            clientes.remove(sender);
        }
    }

    public void apagarServer() {
        activo = false;
 try {
            
            Mensaje mensaje=new Mensaje();
            mensaje.setUsuario(ss.getInetAddress().getHostAddress());
            mensaje.setTipoMensaje(3);
            mensaje.setMensaje("Servidor desconectado");
            broadcast(mensaje,null);
            servidor.interrupt();
//             mensaje.setTipoMensaje(3);
//            ObjectOutputStream oos = new ObjectOutputStream(new Socket(ss.getInetAddress(), ss.getLocalPort()).getOutputStream());
//            oos.writeObject(mensaje);
            if (!ss.isClosed()) {
                
                ss.close();
            }
//            if (s.isConnected()) {
//                s.close();
//            }
        } catch (IOException ex) {
            Logger.getLogger(ServidorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
