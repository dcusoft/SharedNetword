/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cocum.sharednetword.service;


import com.cocum.sharednetword.MainApp;
import com.cocum.sharednetword.controlador.ServidorController;
import static com.cocum.sharednetword.controlador.ServidorController.clientes;
import com.cocum.sharednetword.dao.Mensaje;
import com.cocum.sharednetword.dao.Usuario;
import static com.cocum.sharednetword.service.ThreadServer.listUsuario;
import com.sun.webkit.ThemeClient;
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
public class ThreadAtencionCliente implements Runnable {
    
    
    Socket s;
    boolean activo;
    public Usuario usuario;
    ServidorController server;
    ObjectInputStream ois;
    ObjectOutputStream oos;
    
    public ThreadAtencionCliente(Socket s,ServidorController server) {
     this.s=s; 
     activo=true;
     this.server=server;
    }
    
    @Override
    public void run() {
        try {
            Logger.getLogger(ThreadAtencionCliente.class.getName()).log(Level.INFO, "HILO DE ATENCION CLIENTE INICIADO:"+s.isConnected());
            
            Mensaje recibido=null;
            while (activo) {
                Logger.getLogger(ThreadAtencionCliente.class.getName()).log(Level.INFO, "ESPERANDO NUEVO MENSAJE");
                ois = new ObjectInputStream(s.getInputStream());
                recibido = (Mensaje) ois.readObject();
                Mensaje respuesta = new Mensaje();
                respuesta.setUsuario(recibido.getUsuario());
                respuesta.setTipoMensaje(recibido.getTipoMensaje());
                switch (recibido.getTipoMensaje()) {
                    case 0:
                        Logger.getLogger(ThreadAtencionCliente.class.getName()).log(Level.INFO, "NOTIFICA USUARIO "+recibido.getUsuario()+" CONECTADO");
                        respuesta.setMensaje("Se ha Conectado");
                        usuario=(Usuario)recibido.getMensaje();
                        listUsuario.add(usuario);
                        server.broadcast(respuesta, this);
                        respuesta.setTipoMensaje(4);
                        respuesta.setMensaje(listUsuario);
                        server.broadcast(respuesta, this);
                        break;
                    case 1:
                        Logger.getLogger(ThreadAtencionCliente.class.getName()).log(Level.INFO, recibido.getUsuario() + ": " +"ENVIO DE MENSAJE A TODOS");
                        respuesta.setMensaje(recibido.getMensaje());
                        server.broadcast(respuesta, this);
                       break;
                    case 2:
                        Logger.getLogger(ThreadAtencionCliente.class.getName()).log(Level.INFO, recibido.getUsuario() + ": " +"ENVIO DESCONEXION A TODOS");
                        respuesta.setMensaje(recibido.getMensaje());
                        server.broadcast(respuesta, this);
                        for(Usuario u:listUsuario){
                            if(u.getName().equals(recibido.getUsuario())){
                            usuario=u;
                            }
                        }
                        listUsuario.remove(usuario);
                        respuesta.setTipoMensaje(4);
                        respuesta.setMensaje(listUsuario);
                        server.broadcast(respuesta, this);
                        activo=false;
                       break;
                    case 3:
                        Logger.getLogger(ThreadAtencionCliente.class.getName()).log(Level.INFO, "APGANDO HILO DE CIERRE");
                        activo=false;
                        clientes.remove(this);
                }
                
            }
        Logger.getLogger(ThreadAtencionCliente.class.getName()).log(Level.INFO, "Se cerro la atencion de: "+ recibido.getUsuario());    
        clientes.remove(this);
        } catch (IOException ex) {
            Logger.getLogger(ThreadAtencionCliente.class.getName()).log(Level.SEVERE, "IOException", ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ThreadAtencionCliente.class.getName()).log(Level.SEVERE, "ClassNotFoundException", ex);
        }
    }
    
    
    public void send(Mensaje mensaje){
        
        try {
            oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(mensaje);
        } catch (IOException ex) {
            Logger.getLogger(ThreadAtencionCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendAll(){
    
    }
    
    public void apagarCliente() {
        activo = false;
        try {
            if(s!=null){
            s.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(ThreadAtencionCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
