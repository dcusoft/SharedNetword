/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cocum.sharednetword.service;


import com.cocum.sharednetword.MainApp;
import com.cocum.sharednetword.controlador.ClienteController;
import com.cocum.sharednetword.dao.Mensaje;
import com.cocum.sharednetword.dao.Usuario;
import static com.cocum.sharednetword.service.ThreadServer.listUsuario;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

/**
 *
 * @author COCUN
 */
public class ThreadCliente implements Runnable {

    Socket s;
    ObjectInputStream ois;
    ObjectOutputStream oos;
    ObjectInputStream dois;
    ObjectOutputStream doos;
    MainApp ventana;

    public ThreadCliente(Socket s,MainApp ventana) {
        this.s = s;
        this.ventana=ventana;
    }

    @Override
    public void run() {
        try {
            do{
               
                Logger.getLogger(ThreadCliente.class.getName()).log(Level.INFO, "ESTADO CONEXION CLIENTE: " + s.isConnected());
                ois = new ObjectInputStream(s.getInputStream());
                Mensaje request = (Mensaje) ois.readObject();
                Logger.getLogger(ThreadCliente.class.getName()).log(Level.INFO, "Recibe de server :" + request.toString());
                Mensaje response = new Mensaje();
                switch (request.getTipoMensaje()) {
                    case 0://conectado
                        ventana.getjTextArea1().append((String)request.getUsuario()+": "+(String)request.getMensaje()+"\n");
                        break;
                    case 1:
                        ventana.getjTextArea1().append((String)request.getUsuario()+": "+(String)request.getMensaje()+"\n");
                        break;
                    case 2:
                        ventana.getjTextArea1().append((String)request.getUsuario()+": "+(String)request.getMensaje()+"\n");
                        break;
                    case 3:
                        ventana.getjTextArea1().append((String)request.getUsuario()+": "+(String)request.getMensaje()+"\n");
                        break;
                    case 4:
                        DefaultListModel<String> listModel = new DefaultListModel<>();
                        for(Usuario u:(ArrayList<Usuario>)request.getMensaje()){
                            listModel.addElement(u.toString());
                        }
                        ventana.getjListConectados().setModel(listModel);
                        break;

                }
            }while(s.isConnected());
            System.out.println("SE FINALIZO LA ATENCION");
        } catch (ClassNotFoundException ex) {
            System.out.println("CLIENTE CAIDO ClassNotFoundException");
            Logger.getLogger(ThreadCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.println("CLIENTE CAIDO IOException");
            Logger.getLogger(ThreadCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
