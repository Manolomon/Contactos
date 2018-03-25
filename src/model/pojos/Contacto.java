/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.pojos;

import java.util.Date;

/**
 * Clase principal para modelar los contactos
 * @author Manolo
 * @since 03/10/2018
 * @version 2.0
 */
public class Contacto {
    private int idContacto;
    private String nombre;
    private String telefono;
    private String email;
    private String direccion;
    private String apodo;
    private Date fechaNacimiento;

    /**
     * Constructor por defecto 
     */
    public Contacto() {
    }

    /**
     * Constructor del Contacto para la Base de Datos
     */
    public Contacto(String nombre, String telefono, String email, String direccion, String apodo,
            Date fechaNacimiento) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        this.apodo = apodo;
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * @return El identificador del Contacto en la Base de Datos
     */
    public int getIdContacto() {
        return idContacto;
    }

    /**
     * @return El nombre del contacto
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre El nombre del contacto
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return El telefono del contacto
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono El telefono del contacto
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return El correo electrónico del contacto
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email El correo electrónico del contacto
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return La dirección del contacto
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion La dirección del contacto
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return direccion El apodo (opcional) del contacto
     */
    public String getApodo() {
        return apodo;
    }

    /**
     * @param direccion El apodo (opcional) del contacto
     */
    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    /**
     * @return La fecha de nacimiento del contacto
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @param fechaNacimiento La fecha de nacimiento del contacto
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

}
