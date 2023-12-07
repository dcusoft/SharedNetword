/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cocum.sharednetword.controlador;


import com.cocum.sharednetword.MainApp;
import com.cocum.sharednetword.dao.Mensaje;
import com.cocum.sharednetword.dao.Usuario;
import com.cocum.sharednetword.service.ThreadCliente;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author COCUN
 */
public class ClienteController {

    Socket s;
    int puerto;
    int puertoCliente;
    String host;
    ObjectInputStream ois;
    ObjectOutputStream oos;
    MainApp ventana;

    public ClienteController(int puerto, String host, MainApp ventana) {
        this.puerto = puerto;
        this.host = host;
        this.ventana = ventana;
    }

    public void Conectar(String usuario) {
        try {
            s = new Socket(host, puerto);
            Logger.getLogger(ClienteController.class.getName()).log(Level.INFO, "CLIENTE CONECTADO: "+usuario);
            Thread atencion=new Thread(new ThreadCliente(s,ventana));
            atencion.start();
            Mensaje m=new Mensaje();
            m.setUsuario(usuario);
            m.setTipoMensaje(0);
            Usuario u=new Usuario();
            u.setIp(InetAddress.getLocalHost().getHostAddress());
            u.setName(usuario);
            m.setMensaje(u);
            Send(m);
            
        } catch (IOException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void DesConectar() {
        try {
            s.close();
        } catch (IOException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Send(Object mensaje) {
        try {
            if(s!=null){
            
            if (s.isClosed()) {
                System.err.println("Desconectado");
            } else {
                oos = new ObjectOutputStream(s.getOutputStream());
                oos.writeObject(mensaje);
                Logger.getLogger(ClienteController.class.getName()).log(Level.INFO, "Mensaje Enviado : "+((Mensaje)mensaje).toString());
            }
            }
        } catch (IOException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
