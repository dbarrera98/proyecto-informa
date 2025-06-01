# Librería API

API REST para la gestión integral de autores y libros en una librería.  
Desarrollada con **Spring Boot**, **PostgreSQL** y **Docker**. Permite operaciones CRUD robustas y 
despliegue en entornos locales o de integración.

---

## Características

- CRUD completo para autores y libros.
- Consulta de autores junto a sus libros asociados.
- Inicialización automática de la base de datos con scripts PL/SQL (PL/pgSQL).
- Despliegue reproducible mediante Docker y Docker Compose.
- Código fuente y demo funcional disponibles.

---

## Requisitos

- Java 21
- Docker y Docker Compose
- Maven

---

## Instalación y Ejecución

### 1. Clona el repositorio

```bash
git clone https://github.com/dbarrera98/proyecto-informa.git
cd proyecto-informa
```

### 2. Compila el proyecto (opcional, solo si modificas el código)

```bash
mvn clean package
```

Esto generará el archivo `target/libreria-0.0.1-SNAPSHOT.jar` necesario para construir la imagen Docker de la aplicación.

### 3. Construcción y despliegue con Docker Compose

La forma más fácil de levantar todo —base de datos y API— es con Docker Compose:

```bash
docker-compose up --build
```

Esto iniciará:
- **PostgreSQL** (base de datos, con el script de inicialización)
- **La aplicación Spring Boot** (backend en Java)

Al finalizar, tendrás la API corriendo en:  
[http://localhost:8081/api/](http://localhost:8081/api/)

#### Variables de entorno principales (puedes personalizarlas en `docker-compose.yml`):

- `SPRING_DATASOURCE_URL`
- `SPRING_DATASOURCE_USERNAME`
- `SPRING_DATASOURCE_PASSWORD`

---

### 4. Construir y correr la imagen Docker manualmente (opcional)

Si solo quieres la imagen de la aplicación, puedes hacerlo así:

#### a) Construir la imagen

```bash
docker build -t app-biblio:1.0 .
```

#### b) Ejecutar la imagen (conexión a una base de datos existente)

```bash
docker run -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=jdbc:postgresql://<host>:<puerto>/<dbname> \
  -e SPRING_DATASOURCE_USERNAME=<usuario> \
  -e SPRING_DATASOURCE_PASSWORD=<contraseña> \
  app-biblio:1.0
```

> **Nota:** Si usas Docker Compose, estos pasos se ejecutan automáticamente.

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
- Las variables de conexión y credenciales pueden personalizarse en `docker-compose.yml` o al crear el contenedor.

---

## Inicialización de la Base de Datos

El archivo [`initdb/init.sql`](initdb/init.sql) crea las tablas, funciones y datos iniciales de la base automáticamente al levantar el contenedor de PostgreSQL la primera vez.

Si necesitas restaurar tu base de datos local en el contenedor:
1. Haz un backup con `pg_dump` en tu máquina local:
   ```bash
   pg_dump -U postgres -d libreria > initdb/init.sql
   ```
2. Borra el volumen y reinicia los contenedores para aplicar el nuevo script:
   ```bash
   docker-compose down -v
   docker-compose up --build
   ```

---

## Estructura del Proyecto

```
├── src/main/java/com/biblio/libreria/   # Código fuente Java
├── src/main/resources/                  # Configuración de la aplicación
├── initdb/init.sql                      # Script de inicialización de la base de datos
├── Dockerfile                           # Imagen Docker de la app
├── docker-compose.yml                   # Orquestación de servicios
├── docs/                                # Documentación e imágenes de pruebas
│   └── img/                             # Evidencias de instalación y pruebas
├── API_CATALOGACION.md                  # Evidencias detalladas de pruebas y uso
└── README.md                            # Documentación principal
```

---

## Demo Funcional

La API está desplegada en Railway y puede consultarse en:  
[https://app-biblio-production-ed53.up.railway.app/api/](https://app-biblio-production-ed53.up.railway.app/api/)

Ejemplos rápidos:
- [GET /api/autor](https://app-biblio-production-ed53.up.railway.app/api/autor)
- [GET /api/libro](https://app-biblio-production-ed53.up.railway.app/api/libro)

> Puedes probar la API con Postman, cURL o tu navegador.

---

## Instalación y ejecución de pruebas

La guía detallada de instalación, ejecución y pruebas (incluyendo imágenes ilustrativas y ejemplos en producción) está disponible en:

👉 [API_CATALOGACION.md](API_CATALOGACION.md)

---

## Autor

**Duvan Andres Barrera Figueroa**  
Contacto: andresing2021@gmail.com

---

¿Dudas, sugerencias o problemas?  
Abre un Issue o Pull Request en el repositorio.