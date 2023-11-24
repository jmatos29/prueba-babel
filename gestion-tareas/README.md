# Proyecto de Gestión de Tareas

Este es un proyecto de gestión de tareas desarrollado con Spring Boot.

## Requisitos Previos
- Java y Spring Boot
- Maven
- Base de Datos relacional compatible con AWS
- Test

## Configuración del Proyecto

1. Clona el repositorio:

   ```bash
   git clone https://github.com/tu-usuario/proyecto-gestion-tareas.git

2. Navega al directorio:
    
   ```bash
   cd gestion-tareas
   
3. Configura la Base de datos:

    La base de datos ya esta en la nube en AWS con RDS.

4. Ahora procedemos a correr la aplicacion:
    
    ```bash
   mvn spring-boot:run
La aplicacion se ejecutará en http://localhost:8080

# Uso

- Accede a la aplicación en tu navegador: http://localhost:8080
- Hay un usuario registrado en la base de datos, para probar utiliza este curl y puedes usar postman para importarlo:
    ```bash
  curl --location 'localhost:8080/users' \
    --header 'Authorization: Basic dXNlckB1c2VyOnBhc3N3b3Jk' \
    --header 'Cookie: JSESSIONID=49F09191AA678C78FEB1EDBAACF71797'
- Utiliza las API para realizar operaciones CRUD en tareas y usuarios.

- Endpoints que pueden utilizar:
   - Obtener todas las tareas:
     http://localhost:8080/tasks

   - Obtener todas las tareas asignadas a un usuario (reemplaza {userId} con el ID de usuario real):
     http://localhost:8080/users/{userId}/tasks
    
# Preguntas

- ¿Cómo manejarías la paginación y la ordenación de las tareas?
    Spring Data nos provee herramientas para hacer paginacion, asi que podemos hacer uso de ellas y aplicar paginacion.
- ¿Qué medidas tomarías para garantizar la seguridad de la aplicación?
    Lo que haria es poner autenticacion, en esta pequena prueba he agregado pero una mas robusta, con sesiones que expiren, agregar token, mantener todo actualizado, entre otras medidas.
- ¿Cómo escalarías esta aplicación si el número de usuarios y tareas aumentara significativamente?
    La escalabilidad es muy importante, esto para garantizar el rendimiento y y estabilidad de nuestro serivio, algunas medidas que se pueden tomar serian, aumentar la capacidad de recursos del servidor de base de datos, migrando a uno mas potente o cambiar las configuraciones del que esta para mejor rendimiento.
    Otra cosa a tomar en cuenta podria ser la implementacion del caching, un sistema de almacenamiento en cache para reducir la carga de la base de datos y mejorar la velocidad de respuesta. Balanceo de carga, microservicios, entre otras cosas.
    
- ¿Cómo gestionarías las actualizaciones de la base de datos sin tiempo de inactividad?
    Esta tarea es algo critico de hacer actualizaciones a base de datos sin tiempo de inactividad pero utilizando herramientas de migracion de base de datos como Flyway o Liquibase, estas herramientas perminten versionar y aplicar scripts de migracion de manera controlada.