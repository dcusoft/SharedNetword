/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cocum.sharednetword.dao;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author COCUN
 */
@Setter
@Getter
@ToString
public class Mensaje implements Serializable{
    private String usuario;
    private int tipoMensaje;
    private Object mensaje;   
}
