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
import java.net.ConnectException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author COCUN
 */
public class ActualizaConectados implements Runnable {

    Socket s;
    ObjectInputStream ois;
    ObjectOutputStream oos;
    boolean estado=true;
    @Override
    public void run() {
        
        while (estado) {
            List<Usuario> lista=new ArrayList<>();
                    lista.addAll(ThreadServer.listUsuario);
            Usuario usuario=new Usuario();
            try {
                
                for (Usuario u : lista) {
                    usuario=u;
                    System.out.println("Actualizando conectados");
                    s = new Socket(u.getIp(), u.getPort());

                    if (s != null && s.isConnected()) {
                        oos = new ObjectOutputStream(s.getOutputStream());
                        Mensaje data = new Mensaje();
                        data.setTipoMensaje(0);
                        data.setMensaje(ThreadServer.listUsuario);
                        oos.writeObject(data);
                        ois = new ObjectInputStream(s.getInputStream());
                        data = (Mensaje) ois.readObject();
                        System.out.println(data.getMensaje());

                    } else {
                        ThreadServer.listUsuario.remove(u);
                        Thread notificarDesconectados = new Thread(new NotificarDesconectados(ThreadServer.listUsuario, u));
                        notificarDesconectados.start();
                    }
                    
                    Thread.sleep(2000);

                }
            } catch (ConnectException ex) {
                ThreadServer.listUsuario.remove(usuario);
                Thread notificarDesconectados = new Thread(new NotificarDesconectados(ThreadServer.listUsuario, usuario));
                notificarDesconectados.start();
                Logger.getLogger(ActualizaConectados.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ActualizaConectados.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(ActualizaConectados.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ActualizaConectados.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public void apagarActualizar() {
        estado=false;
    }

}
