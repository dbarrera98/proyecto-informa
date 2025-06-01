# Librería API

API REST para la gestión integral de autores y libros en una librería.  
Desarrollada con **Spring Boot**, **PostgreSQL** y **Docker**, permite operaciones CRUD robustas y despliegue en entornos locales o de integración.

---

## Características

- CRUD completo para autores y libros.
- Consulta de autores junto a sus libros asociados.
- Inicialización automática de la base de datos con scripts SQL.
- Despliegue reproducible mediante Docker y Docker Compose.

---

## Requisitos

- Java 21
- Docker y Docker Compose

---

## Instalación y Ejecución

### 1. Clona el repositorio

```bash
git clone https://github.com/dbarrera98/proyecto-informa.git
cd proyecto-informa
```

### 2. Construye y levanta los servicios

```bash
docker-compose up --build
```

Esto iniciará:
- PostgreSQL (con el script de inicialización)
- La aplicación Spring Boot

### 3. Acceso a la API

La API estará disponible en:  
`http://localhost:8081/api/`

---

## Documentación de la API

### Endpoints de Autor

| Método | Endpoint          | Descripción                         |
|--------|-------------------|-------------------------------------|
| POST   | `/api/autor`      | Crear un nuevo autor                |
| GET    | `/api/autor`      | Listar todos los autores            |
| GET    | `/api/autor/{id}` | Obtener un autor y sus libros       |
| PUT    | `/api/autor/{id}` | Actualizar información de un autor  |
| DELETE | `/api/autor/{id}` | Eliminar un autor                   |

#### Ejemplos

**Crear un autor**
```http
POST /api/autor
Content-Type: application/json

{
  "nombre": "Gabriel García Márquez"
}
```

**Obtener todos los autores**
```http
GET /api/autor
```

**Obtener autor por ID (con libros)**
```http
GET /api/autor/14
```

**Actualizar un autor**
```http
PUT /api/autor/16
Content-Type: application/json

{
  "nombre": "Nombre actualizado"
}
```

**Eliminar un autor**
```http
DELETE /api/autor/17
```

---

### Endpoints de Libro

| Método | Endpoint           | Descripción                        |
|--------|--------------------|------------------------------------|
| POST   | `/api/libro`       | Crear un nuevo libro               |
| GET    | `/api/libro`       | Listar todos los libros            |
| GET    | `/api/libro/{id}`  | Obtener un libro por su ID         |
| PUT    | `/api/libro/{id}`  | Actualizar información de un libro |
| DELETE | `/api/libro/{id}`  | Eliminar un libro                  |

#### Ejemplos

**Crear un libro**
```http
POST /api/libro
Content-Type: application/json

{
  "titulo": "El amor en los tiempos del cólera",
  "autorId": 14
}
```

**Obtener todos los libros**
```http
GET /api/libro
```

**Obtener libro por ID**
```http
GET /api/libro/12
```

**Actualizar un libro**
```http
PUT /api/libro/11
Content-Type: application/json

{
  "titulo": "Título actualizado",
  "autorId": 14
}
```

**Eliminar un libro**
```http
DELETE /api/libro/8
```

---

> Puedes probar la API con herramientas como **Postman** o **cURL**.

---

## Configuración

- La configuración principal está en `src/main/resources/application.yml`.
- Las variables de conexión y credenciales pueden personalizarse en `docker-compose.yml`.

---

## Inicialización de la Base de Datos

El archivo `initdb/init.sql` crea las tablas, funciones y datos iniciales de la base automáticamente al levantar el contenedor de PostgreSQL.

---

## Estructura del Proyecto

```
├── src/main/java/com/biblio/libreria/   # Código fuente Java
├── src/main/resources/                  # Configuración de la aplicación
├── initdb/init.sql                      # Script de inicialización de la base de datos
├── Dockerfile                           # Imagen Docker de la app
├── docker-compose.yml                   # Orquestación de servicios
└── README.md                            # Documentación principal
```

---

## Autor

**Duvan Andres Barrera Figueroa**  
Contacto: andresing2021@gmail.com

---

¿Dudas, sugerencias o problemas?  
Abre un Issue o Pull Request en el repositorio.
