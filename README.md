Esta es una API REST diseñada para la gestión de usuarios, basada en la especificación OpenAPI 3.0. Permite registrar, autenticar y actualizar usuarios. Forma parte de un sistema de autenticación que implementa buenas prácticas de diseño y comunicación entre servicios.

🌐 **Documentación oficial de OpenAPI y Swagger**: [Swagger](https://swagger.io)  
**El contrato se encuentra en**: [GitHub - ms-users-swagger](https://github.com/Jos3Luis/ms-users-swagger)

## Instalación

Para la instalación, debes ejecutar el siguiente comando:

mvn clean install

Y luego, ejecutar el comando para iniciar la aplicación:

java -jar NOMBREJAR.jar

Se usó el puerto: `8785`

## Acceso a la Base de Datos en Memoria

Para acceder a la base de datos en memoria, puedes usar la siguiente URL:

[http://localhost:8785/h2-console/login.jsp?jsessionid=638e7616da0f63f1053ca314824171f7](http://localhost:8785/h2-console/login.jsp?jsessionid=638e7616da0f63f1053ca314824171f7)

### Datos de conexión:

- **JDBC URL**: `jdbc:h2:mem:testdb`
- **Username**: `sa`
- **Password**: (dejar en blanco)
