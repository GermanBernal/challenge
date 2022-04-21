# MyHotel challenge

## Enunciado del problema:

Una empresa de arriendo de vehículos necesita una aplicación para administrar los distintos tipos de vehículos que posee. Para ello, se solicita crear la capa de servicios (API) que permita ingresar nuevos vehículos, editarlos, eliminarlos, listar y/o buscar alguno en específico (CRUD). Además se requiere que el sistema sea capaz llevar un registro de las mantenciones realizadas a cada vehículo.

## Estructura:
```
.
|-- docker
`-- src
    |-- main
    |   |-- java
    |   |   `-- com
    |   |       `-- myhotel
    |   |           `-- challenge
    |   |               |-- configuration
    |   |               |-- domain
    |   |               |   |-- model
    |   |               |   |   `-- consultas
    |   |               |   |-- repository
    |   |               |   `-- usecase
    |   |               |       |-- dto
    |   |               |       |   `-- consultas
    |   |               |       |-- impl
    |   |               |       `-- mapper
    |   |               |-- exceptions
    |   |               |-- infraestructure
    |   |               |   |-- interceptors
    |   |               |   `-- rest
    |   |               `-- utils
    |   |                   `-- enums
    |   `-- resources
    `-- test
        `-- java
            `-- com
                `-- myhotel
                    `-- challenge
                        |-- infraestructure
                        |   `-- rest
                        `-- utils

```
## Stack Tecnológico

1. Java 11 JDK
2. [Spring boot](https://spring.io/projects/spring-boot) - Framework de java
3. [Hibernate](https://hibernate.org/) - Framework que implementa JPA.
4. [Junit](https://junit.org/junit5/docs/current/user-guide/) - Tests.
5. [Project Lombok](https://projectlombok.org/) - Libreria que mediante anotaciones reduce el boilerplate
6. [ModelMapper](http://modelmapper.org/) - Mapeo de propiedades entre tipos de objetos
7. [MySQL](https://www.mysql.com/) - Motor de base de datos
8. [Swagger](https://swagger.io/specification/) - Herramientas que implementan la especificacion de openAPI
9. [Docker](https://www.docker.com/) - Contenedores


## Prerequisitos


### Docker
Verificar que está instalado docker:
```
docker --version
```
```
Docker version 20.10.14, build a224086
```
Verificar que está instalado docker-compose:
```
docker-compose --version
```
```
docker-compose version 1.29.2, build 5becea4c
```

## Ejecución:
Clonar el repositorio en la carpeta que se desee. 

### Levantamos el contenedor:
Nos posicionamos en la carpeta que contiene el archivo docker-compose.yaml y ejecutamos (este paso puede demorar)
```
docker-compose up -d
```
Al finalizar, veremos un mensaje en consola como el siguiente:
```
Creating myhotel-challenge-database ... done
Creating myhotel-challenge-app      ... done
```
Verificamos que se levantaron correctamente los contenedores, para ello ejecutamos el siguiente comando:
```
docker ps
```
Deberiamos visualizar al menos 2 contenedores con los nombres "myhotel-challenge-app" y "myhotel-challenge-database"
```
CONTAINER ID   IMAGE        COMMAND                  CREATED          STATUS              PORTS                               NAMES
5288e7cc13d3   web:latest   "./mvnw spring-boot:…"   21 minutes ago   Up About a minute   0.0.0.0:8080->8080/tcp              myhotel-challenge-app
212b9f62b88e   mysql:8.0    "docker-entrypoint.s…"   25 minutes ago   Up About a minute   0.0.0.0:3306->3306/tcp, 33060/tcp   myhotel-challenge-database
```
Si todos los pasos fueron correctos, la aplicación se encontrará ejecutando en el contenedor, exponiendo el puerto 8080. 
Podemos ingresar a swagger accediendo al siguiente link [MyHotel-challenge API](http://localhost:8080/documentation.html)

> *Aclaracion:* La primera vez que se ejecute, debe cargar y ejecutar el archivo initdb.sql por lo tanto dicha demora retrasa el tiempo de arranque del proyecto de spring, y por lo tanto, al intentar acceder al link anterior puede fallar. Se debe esperar unos segundos a que reintente levantar el proyecto de spring.

## Swagger
Veremos tres segmentos, correspondientes a cada uno de los controllers desarrollados:
* Camion controller
* Auto controller
* Mantenimiento controller
* Consultas controller

Ademas, veremos en la pestaña Schemas, los DTOs, cada uno documentado con sus tipos, propiedades y ejemplos:
* CamionDto
* AutoDto
* MantenimientoDto

Se puede probar la API desde la misma UI de swagger, pero tambien es posible obtener la colección de postman, haciendo click en el link "/documentation" que se encuentra debajo del titulo "Myhotel API"

## Aclaración sobre Tests
Se crea solamente un test de la capa rest de la clase VehiculoCamionController, la misma es a modo de demostración.
No obstante, el Dockerfile ejecuta el comando que contiene -DskipTest.
```
RUN ./mvnw clean install -DskipTests
```
Si se desea correr los tests, se puede mediante consola ejecutando el siguiente comando:
```
./mvnw test
```

## Aclaración sobre ejercicio 2:
Se realizan las queries en SQL y se dejan en archivo "ejercicio-2.sql". 
Ademas de poder ser ejecutadas, las mismas fueron utilizadas a modo de "native query" en el proyecto para resolver el punto 3.


## Autor

* **Germán Bernal** 