/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cocum.sharednetword.service;


import com.cocum.sharednetword.MainApp;
import com.cocum.sharednetword.dao.Mensaje;
import com.cocum.sharednetword.dao.Usuario;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

/**
 *
 * @author COCUN
 */
public class ThreadCliente implements Runnable {
    
    ServerSocket ss;
    Socket s;
    int port;
    boolean activo;
    MainApp ventana;
    
    public ThreadCliente(int port, MainApp ventana) {
        this.port = port;
        this.ventana = ventana;
        activo = true;
    }
    
    @Override
    public void run() {
        try {
            ss = new ServerSocket(port);
            System.out.println("CLIENTE INICIADO : " + port);
             ObjectOutputStream oos;
            while (activo) {
                s = ss.accept();
                System.out.println("ss.isClosed()"+ss.isClosed());
                System.out.println("s.isConnected()"+s.isConnected());
                ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                Mensaje recibido = (Mensaje) ois.readObject();
                Mensaje respuesta = new Mensaje();
                switch (recibido.getTipoMensaje()) {
                    case 0:
                        System.out.println("CLIENTE RECIBIENDO LISTA DE USUARIOS");
                        DefaultListModel<String> m = new DefaultListModel<>();
                        ArrayList<Usuario> lista=(ArrayList<Usuario>)recibido.getMensaje();
                        for (Usuario u : lista) {
                            m.addElement(u.getName());
                        }
                        ventana.getjListConectados().setModel(m);
                        oos = new ObjectOutputStream(s.getOutputStream());
                        respuesta.setTipoMensaje(0);
                        respuesta.setMensaje("Conectados actualizados");
                        oos.writeObject(respuesta);
                        break;
                    case 1:
                        System.out.println("CLIENTE RECIBIENDO NOTIFICACION DE DESCONECTADO");
                        ventana.getjTextArea1().append((String)recibido.getMensaje());
                        oos = new ObjectOutputStream(s.getOutputStream());
                        respuesta.setMensaje("Notificacion con Exito ");
                        oos.writeObject(respuesta);
                       break;
                    case 2:
                        System.out.println("CLIENTE RECIBIENDO MENSAJE");
                        ventana.getjTextArea1().append((String)recibido.getMensaje());
                        oos = new ObjectOutputStream(s.getOutputStream());
                        respuesta.setMensaje("Notificacion con Exito ");
                        oos.writeObject(respuesta);
                       break;
                }
                
            }
            
            
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("CLIENTE TERMINADO");
            Logger.getLogger(ThreadCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void apagarCliente() {
        activo = false;
        try {
            
            if(!ss.isClosed()){
            ss.close();
            }
            if(s.isConnected()){
            s.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(ThreadCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
