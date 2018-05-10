CREATE DATABASE Contactos;
USE Contactos;

CREATE TABLE Contacto(
    idContacto int NOT NULL auto_increment,
    nombre varchar(255) NOT NULL,
    apodo varchar(255),
    telefono varchar(15) NOT NULL,
    email varchar(100) NOT NULL,
    direccion text NOT NULL,
    fechaNacimiento DATE NOT NULL,
    primary key (idContacto)
);