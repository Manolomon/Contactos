# Contactos

[![GitHub release](https://img.shields.io/github/release/Manolomon/Contactos.svg?longCache=true&colorB=yellow&style=for-the-badge)](https://github.com/Manolomon/Contactos/releases)

Proyecto para Principios de Construcción de Software para el modelado de una aplicación java para el almacenamiento de Contactos.
Entre las actividades que se encuentran, esta:
- Almacenar Contactos
- Modificar Contactos
- Visualizar datos de Contactos
- Eliminar Contactos
- Realizar búsqueda de Contactos

La documentacón adicional para los Casos de Uso se adjunta en ```/doc/```

## Requerimientos
* [X] El Sistema deberá almacenar contactos
* [X] Se podrá modificar los contactos

## Prototipo

![Prototipo](/doc/Contactos.png)

## Versión Final

![Finale](/doc/Contactos_Finale.png)

## Base de Datos
Modelado de la base de datos en MySQL (Archivo almacenado en ```/database/Contactos.sql```)

| Field               | Type         | Null | Key | Default | Extra          |
| ------------------- | ------------ | ---- | --- | ------- | -------------- |
| idContacto          | int(11)      | NO   | PRI | NULL    | auto_increment |
| Nombre              | varchar(255) | NO   |     | NULL    |                |
| Correo electrónico  | varchar(100) | NO   |     | NULL    |                |
| Dirección           | text         | NO   |     | NULL    |                |
| Número Telefónico   | varchar(15)  | NO   |     | NULL    |                |
| Fecha de Nacimiento | date         | NO   |     | NULL    |                |
| Apodo (Opcional)    | varchar(255) | YES  |     | NULL    |                |
