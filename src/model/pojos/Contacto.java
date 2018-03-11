/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.pojos;

import java.util.Date;

/**
 *
 * @author Manolo
 * @since 03/10/2018
 * @version 1.0
 */
public class Contacto {
    private int idContacto;
    private String nombre;
    private String telefono;
    private String email;
    private String direccion;
    private String apodo;
    private Date fechaNacimiento;

    public Contacto() {
    }
    
    public Contacto(String nombre, String telefono, String email, String direccion, String apodo,
            Date fechaNacimiento) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        this.apodo = apodo;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Contacto(int idContacto, String nombre, String telefono, String email, String direccion, String apodo,
            Date fechaNacimiento) {
        this.idContacto = idContacto;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        this.apodo = apodo;
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getIdContacto() {
        return idContacto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

}
