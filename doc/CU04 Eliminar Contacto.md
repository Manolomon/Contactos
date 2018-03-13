# Eliminar Contacto
### Descripcion:
En este caso de uso, el usuario elimina a un contacto seleccionado
### Flujo Normal:
1.- El usuario selecciona un contacto de la lista
2.- El sistema recupera al contacto de la base de datos para mostrarlos (EX01)
3.- El usuario da click en Eliminar
4.- El sistema pregunta ¿Desea eliminar al contacto?
5.- El usuario da click en Aceptar (FA01)
6.- El sistema elimina el registro del contacto de la base de datos (EX01)
7.- El sistema envía la confirmación
8.- Fin

### Flujos Alternos
**FA01: Da click en Canelar**
1.- El sistema limpia los campos de datos
2.- Fin

### Excepciones
**EX01: Error con la conexión a la base de datos**
1.- El sistema imprime el mensaje en el log con la Excepción
2.- Fin