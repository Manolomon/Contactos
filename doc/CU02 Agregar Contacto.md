# Agregar Contacto
### Descripcion:
En este caso de uso, el usuario agrega a un nuevo contacto a la base de datos
### Flujo Normal:
1.- El usuario llena los campos de información (nombre, apodo, telefono, dirección, email y fechaNacimiento)
2.- El usuario da click en "Guardar" (FA01) 
3.- El sistema valida los campos completos (FA02)
4.- El sistema almacena al nuevo contacto en la base de datos (EX01)
5.- Fin

### Flujos Alternos
**FA01: Da click en Canelar**
1.- El sistema limpia los campos de datos
2.- Fin

**FA02: Campos incompletos**
1.- El sistema muestra una ventana con el mensaje "Campos Incompletos"
2.- Regresa al punto 1 del flujo normal

### Excepciones
**EX01: Error con la conexión a la base de datos**
1.- El sistema imprime el mensaje en el log con la Excepción
2.- Fin