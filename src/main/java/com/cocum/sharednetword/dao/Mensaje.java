/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cocum.sharednetword.dao;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author COCUN
 */
public class Mensaje implements Serializable{
    private String usuarioDestino;
    private String ipDestino;
    private int puertoDestino;
    private int tipoMensaje;
    private Object mensaje;
    private List<byte[]> listaArchivos;

    public String getUsuarioDestino() {
        return usuarioDestino;
    }

    public void setUsuarioDestino(String usuarioDestino) {
        this.usuarioDestino = usuarioDestino;
    }

    public String getIpDestino() {
        return ipDestino;
    }

    public void setIpDestino(String ipDestino) {
        this.ipDestino = ipDestino;
    }

    public int getPuertoDestino() {
        return puertoDestino;
    }

    public void setPuertoDestino(int puertoDestino) {
        this.puertoDestino = puertoDestino;
    }

    public int getTipoMensaje() {
        return tipoMensaje;
    }

    public void setTipoMensaje(int tipoMensaje) {
        this.tipoMensaje = tipoMensaje;
    }

    public Object getMensaje() {
        return mensaje;
    }

    public void setMensaje(Object mensaje) {
        this.mensaje = mensaje;
    }

    public List<byte[]> getListaArchivos() {
        return listaArchivos;
    }

    public void setListaArchivos(List<byte[]> listaArchivos) {
        this.listaArchivos = listaArchivos;
    }

    
}
