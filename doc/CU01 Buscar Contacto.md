# Buscar Contactos
### Descripcion:
En este caso de uso, el usuario busca por un contacto con un nombre en específico
### Flujo Normal:
1. El usuario ingresa el nombren del contacto deseado
2. El usuario da click en "Buscar" 
3. El sistema busca en la base de datos por los contactos semejantes (EX-1)
4. El sistema muestra los contactos encontrados (FA-1) 
5. Fin

### Flujos Alternos
**FA01: No hay contactos encontrados**
1. El sistema muestra la lista vacía
2. Fin

### Excepciones
**EX01: Error con la conexión a la base de datos**
1. El sistema imprime el mensaje en el log con la Excepción
2. Fin