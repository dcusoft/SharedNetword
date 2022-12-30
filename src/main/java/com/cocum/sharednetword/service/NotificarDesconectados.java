/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cocum.sharednetword.service;


import com.cocum.sharednetword.dao.Mensaje;
import com.cocum.sharednetword.dao.Usuario;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author COCUN
 */
public class NotificarDesconectados implements Runnable {
    
    List<Usuario> listaUsuarios;
    Usuario usuario;
    ObjectInputStream ois;
    ObjectOutputStream oos;

    public NotificarDesconectados(List<Usuario> listaUsuarios,Usuario usuario) {
        this.listaUsuarios = listaUsuarios;
        this.usuario=usuario;
    }
    
    @Override
    public void run() {
        Socket notificar;
        for (Usuario u : listaUsuarios) {
            try {
                notificar = new Socket(u.getIp(), u.getPort());
                if (notificar.isConnected()) {
                    oos = new ObjectOutputStream(notificar.getOutputStream());
                    Mensaje notificacion = new Mensaje();
                    notificacion.setTipoMensaje(1);
                    notificacion.setMensaje("\n*"+usuario.getName() + " se ha desconectado*");
                    oos.writeObject(notificacion);
                    
                    ois=new ObjectInputStream(notificar.getInputStream());
                    Mensaje confirmacion=(Mensaje)ois.readObject();
                    System.out.println(confirmacion.getMensaje()+u.getName());
                }                
            } catch (IOException ex) {
                Logger.getLogger(NotificarDesconectados.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(NotificarDesconectados.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
}
