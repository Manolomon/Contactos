CREATE TABLE Contacto(
    idContacto int NOT NULL auto_increment,
    nombre varchar(255) NOT NULL,
    apellido varchar(255) NOT NULL,
    telefono varchar(15) NOT NULL,
    email varchar(100) NOT NULL,
    primary key (idContacto)
);