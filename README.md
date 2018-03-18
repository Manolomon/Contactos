# Contactos
Proyecto para Principios de Construcción de Software para modelado de aplicación java de contactos

## Requerimientos
* [ ] El Sistema deberá almacenar contactos
* [ ] Se podrá modificar los contactos

## Prototipo

![Prototipo](/doc/Contactos.png)

## Base de Datos
| Field               | Type         | Null | Key | Default | Extra          |
| ------------------- | ------------ | ---- | --- | ------- | -------------- |
| idContacto          | int(11)      | NO   | PRI | NULL    | auto_increment |
| Nombre              | varchar(255) | NO   |     | NULL    |                |
| Correo electrónico  | varchar(100) | NO   |     | NULL    |                |
| Dirección           | text         | NO   |     | NULL    |                |
| Número Telefónico   | varchar(15)  | NO   |     | NULL    |                |
| Fecha de Nacimiento | date         | NO   |     | NULL    |                |
| Apodo (Opcional)    | varchar(255) | YES  |     | NULL    |                |