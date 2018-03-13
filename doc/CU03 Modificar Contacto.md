# Modificar Contacto
### Descripcion:
En este caso de uso, el usuario modifica los datos de un contacto seleccionado
### Flujo Normal:
1.- El usuario selecciona un contacto de la lista
2.- El sistema recupera al contacto de la base de datos para mostrarlos (EX01)
3.- El usuario llena los campos de información (nombre, apodo, telefono, dirección, email y fechaNacimiento)
4.- El usuario da click en "Guardar" (FA01) 
5.- El sistema valida los campos completos (FA02)
6.- El sistema envia la pregunta ¿Desea modificar a este contacto?"
7.- El usuario da click en Aceptar (FA03)
8.- El sistema almacena los datos del contacto en la base de datos (EX01)
9.- Fin

### Flujos Alternos
**FA01: Da click en Canelar**
1.- El sistema limpia los campos de datos
2.- Fin

**FA02: Campos incompletos**
1.- El sistema muestra una ventana con el mensaje "Campos Incompletos"
2.- Regresa al punto 1 del flujo normal

**FA02: Click en Cancelar**
2.- Regresa al punto 3 del flujo normal

### Excepciones
**EX01: Error con la conexión a la base de datos**
1.- El sistema imprime el mensaje en el log con la Excepción
2.- Fin