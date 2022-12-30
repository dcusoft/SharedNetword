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
    ThreadCliente tc;
    MainApp ventana;

    public ClienteController(int puerto, String host, MainApp ventana) {
        this.puerto = puerto;
        this.host = host;
        this.ventana = ventana;
    }

    public void Conectar(String usuario) {
        try {
            Send(usuario, 1);
            System.out.println("puerto de cliente:" + puertoCliente);
            tc = new ThreadCliente(puertoCliente, ventana);
            Thread escuchaCliente = new Thread(tc);
            escuchaCliente.start();
            Usuario u = new Usuario();
            u.setIp(InetAddress.getLocalHost().getHostAddress());
            u.setPort(puertoCliente);
            u.setName(usuario);
            System.out.println("Registro");
            Send(u, 2);
        } catch (IOException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void DesConectar() {
        tc.apagarCliente();
        try {
            s.close();
        } catch (IOException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Send(Object mensaje, int tipoMensaje) {
        try {
            s = new Socket(host, puerto);
            if (s.isClosed()) {
                System.err.println("Desconectado");
            } else {
                System.out.println("ENVIAR MENSAJE");
                oos = new ObjectOutputStream(s.getOutputStream());
                Mensaje data = new Mensaje();
                data.setTipoMensaje(tipoMensaje);
                data.setMensaje(mensaje);
                oos.writeObject(data);
                System.out.println("Mensaje Enviado : "+data.getMensaje());
                switch (tipoMensaje) {
                    case 1:
                        ventana.getjTextArea1().setText(mensaje + ": Conectando\n");
                        ois = new ObjectInputStream(s.getInputStream());
                        data = (Mensaje) ois.readObject();
                        puertoCliente = (int) data.getMensaje();
                        ventana.getjTextArea1().append(mensaje + ": puerto asignado " + puertoCliente +"\n");
                        ventana.getjTextArea1().append(mensaje + ": Conexion Exitosa");
                        break;
                    case 2:
                        System.out.println("Resgistrando");
                        ois = new ObjectInputStream(s.getInputStream());
                        data = (Mensaje) ois.readObject();
                        ventana.getjLabelEstado().setText((String) data.getMensaje());
                        break;
                    case 3:
                        ois = new ObjectInputStream(s.getInputStream());
                        data = (Mensaje) ois.readObject();
                        ventana.getjLabelEstado().setText((String) data.getMensaje());
                        break;
                    default:
                        break;
                }

            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
