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
