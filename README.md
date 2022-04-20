# MyHotel challenge

## Enunciado del problema:

Una empresa de arriendo de vehículos necesita una aplicación para administrar los distintos tipos de vehículos que posee. Para ello, se solicita crear la capa de servicios (API) que permita ingresar nuevos vehículos, editarlos, eliminarlos, listar y/o buscar alguno en específico (CRUD). Además se requiere que el sistema sea capaz llevar un registro de las mantenciones realizadas a cada vehículo.

## Estructura:
```
.
|-- docker
|   `-- database
|       `-- tmp
`-- src
    |-- main
    |   |-- java
    |   |   `-- com
    |   |       `-- myhotel
    |   |           `-- challenge
    |   |               |-- configuration
    |   |               |-- controllers
    |   |               |-- domain
    |   |               |   |-- model
    |   |               |   |-- repository
    |   |               |   `-- usecase
    |   |               |       |-- dto
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

## Estructura con archivos:
```
.
|-- HELP.md
|-- README.md
|-- docker
|   |-- database
|   |   |-- initdb.sql
|   |   `-- tmp
|   `-- docker-compose.yaml
|-- mvnw
|-- mvnw.cmd
|-- pom.xml
`-- src
    |-- main
    |   |-- java
    |   |   `-- com
    |   |       `-- myhotel
    |   |           `-- challenge
    |   |               |-- ChallengeApplication.java
    |   |               |-- configuration
    |   |               |   `-- OpenApiConfig.java
    |   |               |-- controllers
    |   |               |-- domain
    |   |               |   |-- model
    |   |               |   |   |-- Auto.java
    |   |               |   |   |-- Camion.java
    |   |               |   |   |-- Mantenimiento.java
    |   |               |   |   `-- Vehiculo.java
    |   |               |   |-- repository
    |   |               |   |   |-- AutoRepository.java
    |   |               |   |   |-- CamionRepository.java
    |   |               |   |   |-- MantenimientoRepository.java
    |   |               |   |   `-- VehiculoRepository.java
    |   |               |   `-- usecase
    |   |               |       |-- MantenimientoService.java
    |   |               |       |-- VehiculoService.java
    |   |               |       |-- dto
    |   |               |       |   |-- AutoDto.java
    |   |               |       |   |-- CamionDto.java
    |   |               |       |   |-- MantenimientoDto.java
    |   |               |       |   |-- MantenimientoFullResponseDto.java
    |   |               |       |   `-- VehiculoDto.java
    |   |               |       |-- impl
    |   |               |       |   |-- AutomovilServiceImpl.java
    |   |               |       |   |-- CamionServiceImpl.java
    |   |               |       |   `-- MantenimientoServiceImpl.java
    |   |               |       `-- mapper
    |   |               |           `-- VehiculosMapper.java
    |   |               |-- exceptions
    |   |               |   |-- MyHotelHttpException.java
    |   |               |   `-- ResourceNotFoundException.java
    |   |               |-- infraestructure
    |   |               |   |-- interceptors
    |   |               |   |   `-- ExceptionAdvice.java
    |   |               |   `-- rest
    |   |               |       |-- MantenimientoController.java
    |   |               |       |-- VehiculoAutomovilController.java
    |   |               |       `-- VehiculoCamionController.java
    |   |               `-- utils
    |   |                   |-- ErrorDetails.java
    |   |                   |-- ResponseEntityBuilder.java
    |   |                   `-- enums
    |   |                       `-- Location.java
    |   `-- resources
    |       `-- application.properties
    `-- test
        `-- java
            `-- com
                `-- myhotel
                    `-- challenge
                        |-- ChallengeApplicationTests.java
                        |-- infraestructure
                        |   `-- rest
                        |       `-- VehiculoCamionControllerTest.java
                        `-- utils
                            `-- TestUtils.java

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
Verificar que esta instalado Docker:
```
docker --version
```
```
Docker version 20.10.14, build a224086
```

## Ejecucion:
Clonar el repositorio en la carpeta que se desee. 

### Levantamos el contenedor:
Nos posicionamos en la carpeta que contiene el archivo docker-compose.yaml y ejecutamos 
```
docker-compose -up
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
Si todos los pasos fueron correctos, la aplicacion se encontrar ejecutando en el contenedor, exponiendo el puerto 8080, podemos ingresar a swagger accediendo al siguiente link [MyHotel-challenge API](http://localhost:8080/documentation.html)

## Swagger
Veremos tres segmentos, correspondientes a cada uno de los controllers desarrollados:
* Camion controller
* Auto controller
* Mantenimiento controller

Ademas, veremos en la pestaña Schemas, los DTOs, cada uno documentado con sus tipos, propiedades y ejemplos:
* CamionDto
* AutoDto
* MantenimientoDto

Se puede probar la API desde la misma UI de swagger, pero tambien es posible obtener la coleccion de postman, haciendo click en el link "/documentation" que se encuentra debajo del titulo "Myhotel API"

## Aclaracion sobre Tests
Se crea solamente un test de la capa rest de la clase VehiculoCamionController, la misma es a modo de demostracion.
No obstante, el Dockerfile ejecuta el comando que contiene -DskipTest.
```
RUN ./mvnw clean install -DskipTests
```
Si se desea correr los tests, se puede mediante consola ejecutando el siguiente comando:
```
./mvnw test
```


## Autor

* **Germán Bernal** 