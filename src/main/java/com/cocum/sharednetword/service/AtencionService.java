/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cocum.sharednetword.service;


import com.cocum.sharednetword.dao.Mensaje;
import com.cocum.sharednetword.dao.Usuario;
import static com.cocum.sharednetword.service.ThreadServer.listUsuario;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author COCUN
 */
public class AtencionService implements Runnable {

    Socket s;
    ObjectInputStream ois;
    ObjectOutputStream oos;
    ObjectInputStream dois;
    ObjectOutputStream doos;
    Usuario u;

    public AtencionService(Socket s) {
        this.s = s;

    }

    @Override
    public void run() {
        try {
            System.out.println("ATENDIENDO CLIENTE");
            u = new Usuario();
            if (s.isConnected()) {
                System.out.println("CLIENTE CONECTADO: " + s.isConnected());
                ois = new ObjectInputStream(s.getInputStream());
                Mensaje request = (Mensaje) ois.readObject();
                Mensaje response = new Mensaje();
                switch (request.getTipoMensaje()) {
                    case 1://conectado
                        System.out.println("conectando a servidor");
                        oos = new ObjectOutputStream(s.getOutputStream());
                        response.setTipoMensaje(1);
                        response.setMensaje(getPort(s.getInetAddress().getHostAddress()));
                        oos.writeObject(response);
                        break;
                    case 2:
                        System.out.println("Registrando en servidor");
                        listUsuario.add((Usuario) request.getMensaje());
                        oos = new ObjectOutputStream(s.getOutputStream());
                        response.setTipoMensaje(2);
                        response.setMensaje("Usuario Registrado");
                        oos.writeObject(response);
                        break;
                    case 3://envio de mensaje
                        Socket destino;
                        System.out.println("reenviando mensaje del servidor a :"+listUsuario.size());
                        for (Usuario user : listUsuario) {
                            //if (!(s.getInetAddress().getHostAddress().equals(u.getIp()) && s.getPort() == u.getPort())) {
                                destino = new Socket(user.getIp(), user.getPort());
                                if (destino.isConnected()) {
                                    Mensaje mesajesDestino = new Mensaje();
                                    doos = new ObjectOutputStream(destino.getOutputStream());
                                    mesajesDestino.setTipoMensaje(2);//reenvio a destino
                                    mesajesDestino.setMensaje(request.getMensaje());
                                    doos.writeObject(mesajesDestino);
                                    dois = new ObjectInputStream(destino.getInputStream());
                                    mesajesDestino = (Mensaje) dois.readObject();
                                    System.out.println("mensaje a " + u.getName() + " " + mesajesDestino.getMensaje());
                                }
                                destino.close();
                            //}
                        }
                        oos = new ObjectOutputStream(s.getOutputStream());
                        response.setTipoMensaje(3);//
                        response.setMensaje("mensaje entregado");
                        oos.writeObject(response);

                }

                if (doos != null) {
                    doos.close();
                }
                if (oos != null) {
                    oos.close();
                }
            }
            System.out.println("SE FINALIZO LA ATENCION");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AtencionService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AtencionService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private int getPort(String ip) {
        int newPort = 6000;
        System.out.println("obteniendo puerto");
        System.out.println("cantidad de usuarios: " + listUsuario.size());
        for (Usuario u : listUsuario) {
            System.out.println("Usuario: " + u.getName());
            System.out.println("puerto: " + u.getPort());
            System.out.println("ip: " + u.getIp());
            System.out.println("nueva ip: " + ip);
            if (ip.equals(u.getIp())) {
                if (u.getPort() >= newPort) {
                    newPort = u.getPort() + 1;
                }
            }
        }
        return newPort;
    }

}
