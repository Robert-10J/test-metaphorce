# Documentación del Proyecto - Prueba Técnica

Este proyecto fue desarrollado como parte de una prueba técnica para evaluar competencias en bases de datos, estructuras de datos y algoritmos, configuración de servidores y comunicación entre microservicios.

## Contenido

1. [Bases de Datos](#1-bases-de-datos)
2. [Estructuras de Datos y Algoritmos en Java](#2-estructuras-de-datos-y-algoritmos-en-java)
3. [Configuración de Servidores y Docker](#3-configuración-de-servidores-y-docker)
4. [Comunicación entre Componentes](#4-comunicación-entre-componentes)
5. [Tecnologías Utilizadas](#5-tecnologías-utilizadas)
6. [Ejecución del Proyecto](#6-ejecución-del-proyecto)

---

## 1. Bases de Datos

Se diseñó una base de datos relacional utilizando **PostgreSQL**. El esquema incluye las siguientes tablas:

- **Participantes**
  - `id` (PK)
  - `correo`
  - `institucion`
  - `nombre`

- **Sesiones**
  - `id` (PK)
  - `fecha`
  - `hora`
  - `ponente_principal`
  - `titulo`

- **Registros**
  - `id` (PK)
  - `fecha_registro`
  - `participante_id` (FK)
  - `sesion_id` (FK)

Estas tablas permiten almacenar y gestionar la información relevante de una conferencia, incluyendo los asistentes, las sesiones disponibles y los registros de asistencia.

---

## 2. Estructuras de Datos y Algoritmos en Java

Se implementó una aplicación Java que utiliza estructuras de datos básicas y algoritmos personalizados para el manejo de la información antes de su persistencia en la base de datos.

### Algoritmos implementados:

- **Búsqueda por Fecha (Sesiones)**  
  Se implementó un algoritmo que permite mostrar las sesiones ordenadas por fecha, ya sea en orden ascendente o descendente.

- **Ordenamiento por Nombre (Participantes)**  
  Se ordena alfabéticamente la lista de participantes tomando en cuenta la primera letra del nombre, utilizando un algoritmo de ordenamiento básico (por ejemplo, burbuja o selección).

---

## 3. Configuración de Servidores y Docker

Se preparó un entorno de desarrollo local utilizando **Spring Boot** como servidor de aplicaciones embebido y se configuró Docker para facilitar el despliegue de la aplicación.

### Elementos configurados:

- **Dockerfile** para empaquetar la aplicación Spring Boot.
- **docker-compose.yml** para levantar tanto la aplicación como la base de datos PostgreSQL en contenedores.

Esto permite una ejecución reproducible del entorno completo, facilitando su despliegue y portabilidad.

---

## 4. Comunicación entre Componentes

Se desarrolló una **API REST** utilizando **Spring Boot**, la cual permite realizar operaciones CRUD sobre las tres tablas principales: `Participantes`, `Sesiones` y `Registros`.

### Endpoints principales:

- `/api/participantes`
- `/api/sesiones`
- `/api/registros`

### Seguridad:

- Se implementó **autenticación con JWT (JSON Web Token)** utilizando **Spring Security**.
- Solo los usuarios autenticados pueden acceder a los recursos protegidos de la API.

---

## 5. Tecnologías Utilizadas

- **Lenguaje**: Java 23
- **Frameworks**: Spring Boot, Spring Security
- **Base de Datos**: PostgreSQL
- **Contenedores**: Docker, Docker Compose
- **Otros**: JWT para autenticación

---

## 6. Ejecución del Proyecto

### Requisitos Previos

- Tener **Docker** y **Docker Compose** instalados.

### Pasos para la Ejecución

1. Clonar el repositorio del proyecto.
2. Abrir una terminal y navegar hasta la ubicación del proyecto.
3. Ejecutar el siguiente comando para levantar los servicios de la aplicación y la base de datos:

   ```bash
   docker-compose up --build


# Informe de Diagnóstico

## Introducción

El presente informe tiene como objetivo reflejar la experiencia adquirida durante el desarrollo de la prueba técnica, la cual consistió en construir una aplicación básica que abordara aspectos fundamentales en bases de datos, estructuras de datos y algoritmos, configuración de servidores, y comunicación entre microservicios. Esta experiencia sirvió como punto de partida para identificar fortalezas y áreas de mejora de cara al curso.

---

## Experiencia de Desarrollo

### 1. Bases de Datos

**Experiencia:**  
Se implementó una base de datos relacional utilizando **PostgreSQL** para almacenar información relacionada con una conferencia, incluyendo participantes, sesiones y registros de asistencia. Se diseñaron tres tablas principales con sus respectivas relaciones foráneas.

**Desafíos:**  
El principal reto fue modelar las relaciones entre las entidades de manera coherente, especialmente asegurando la integridad referencial entre participantes, sesiones y registros.

**Solución:**  
Se emplearon claves foráneas y un diseño normalizado que permitió mantener la coherencia en los datos y facilitar las consultas posteriores.

**Autoevaluación:**  
Me considero competente en esta área, ya que pude aplicar correctamente principios de diseño de bases de datos relacionales.

---

### 2. Estructuras de Datos y Algoritmos

**Experiencia:**  
Desarrollé una aplicación Java que utiliza estructuras de datos como listas para el manejo temporal de información antes de su inserción en la base de datos. Implementé dos algoritmos: uno de búsqueda para ordenar sesiones por fecha (ascendente o descendente), y otro de ordenamiento para listar participantes en orden alfabético según la primera letra del nombre.

**Desafíos:**  
El reto principal fue integrar los algoritmos de manera funcional dentro del flujo de la aplicación y asegurar que los datos se mostraran correctamente ordenados.

**Solución:**  
Se realizaron pruebas unitarias para verificar el funcionamiento de los algoritmos y se utilizó la lógica de comparación de cadenas y fechas para la clasificación adecuada.

**Autoevaluación:**  
Tengo una base sólida en estructuras de datos básicas y algoritmos simples, aunque sería beneficioso reforzar el uso de otras estructuras como pilas y colas en escenarios más complejos.

---

### 3. Configuración de Servidores y Docker

**Experiencia:**  
Se configuró un entorno con **Docker** y **Docker Compose**, incluyendo tanto la aplicación Spring Boot como la base de datos PostgreSQL. Esto permitió levantar todo el sistema en un entorno aislado, replicable y portable.

**Desafíos:**  
El desafío fue entender cómo orquestar correctamente los servicios en contenedores y asegurar que la conexión entre la aplicación y la base de datos funcionara sin problemas.

**Solución:**  
Se configuraron correctamente las variables de entorno y puertos en los archivos `Dockerfile` y `docker-compose.yml`, asegurando la comunicación entre los servicios.

**Autoevaluación:**  
Cuento con un conocimiento intermedio en contenedores Docker, y considero esta habilidad en desarrollo, con margen para explorar configuraciones más avanzadas.

---

### 4. Comunicación entre Componentes

**Experiencia:**  
Desarrollé una API REST con **Spring Boot** que permite realizar operaciones CRUD sobre las entidades del sistema. Además, se implementó autenticación utilizando **JWT** y **Spring Security**, protegiendo el acceso a los recursos.

**Desafíos:**  
El principal reto fue integrar la autenticación JWT correctamente y configurar la seguridad de los endpoints sin afectar la funcionalidad general de la API.

**Solución:**  
Se configuró Spring Security con filtros personalizados para la validación de tokens y se probaron distintos flujos de autenticación para garantizar la protección de los recursos.

**Autoevaluación:**  
Demostré competencia en la creación de APIs REST y en la integración de mecanismos de seguridad básicos. Considero que tengo una buena base en este aspecto y estoy preparado para ampliar estos conocimientos hacia arquitecturas más complejas con microservicios.

---

## Conclusiones Generales

El desarrollo de esta prueba me permitió aplicar conocimientos previos y detectar áreas de mejora. Considero que mis fortalezas actuales están en el diseño de bases de datos, desarrollo backend con Java y Spring Boot, la construcción de APIs seguras. Como áreas a reforzar identifico:

- Profundización en estructuras de datos avanzadas.
- Manejo de servidores de aplicaciones externos (como Tomcat independiente o Jetty).
- Implementación de comunicación entre múltiples microservicios.

Este diagnóstico servirá como base para enfocar mi aprendizaje durante el curso y continuar fortaleciendo mis habilidades como desarrollador backend.

