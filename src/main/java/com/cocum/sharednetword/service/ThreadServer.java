/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cocum.sharednetword.service;


import com.cocum.sharednetword.controlador.ServidorController;
import static com.cocum.sharednetword.controlador.ServidorController.clientes;
import com.cocum.sharednetword.dao.Usuario;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;



/**
 *
 * @author COCUN
 */
public class ThreadServer implements Runnable {

    ServerSocket ss;
    Socket s;
    boolean activo;
    public static List<Usuario> listUsuario=new ArrayList<>();
    ServidorController servidor;
    Usuario user;
   
 
    public ThreadServer(ServidorController servidor,ServerSocket ss) {
        this.ss=ss;
        this.servidor=servidor;
        activo = true;
    }

    @Override
    public void run() {
        try {
            
            Logger.getLogger(ThreadServer.class.getName()).log(Level.INFO, "SERVIDOR INICIADO: "+ss.getLocalPort());
            while (!Thread.interrupted()) {
                    Logger.getLogger(ThreadServer.class.getName()).log(Level.INFO, "ESPERANDO UNA CONEXION");
                    s = ss.accept();
                    Logger.getLogger(ThreadServer.class.getName()).log(Level.INFO, "SE ACEPTO UNA CONEXION"+ss.isClosed());
                    ThreadAtencionCliente cliente=new ThreadAtencionCliente(s,servidor);
                    clientes.add(cliente);
                    Logger.getLogger(ThreadServer.class.getName()).log(Level.INFO, "Cantidad de clientes conectados: "+ clientes.size());
                    Thread thread=new Thread(cliente);
                    thread.start();                
            } 
        } catch (IOException ex) {
            System.out.println("SERVIDOR TERMINADO");
            Logger.getLogger(ThreadServer.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
             if (!ss.isClosed()) {
                 try {
                     ss.close();
                 } catch (IOException ex) {
                     Logger.getLogger(ThreadServer.class.getName()).log(Level.SEVERE, null, ex);
                 }
            }
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
