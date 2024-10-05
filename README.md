# API REST de Gestión de Categorías y Productos

Esta API permite gestionar categorías y productos en una base de datos. Proporciona funcionalidades para listar, crear, obtener y eliminar categorías y productos.

## Requisitos

- **Java 11+**
- **Spring Boot**
- **Gradle**
- **Base de datos PostgreSQL** (configurable en `application.properties`)

## Endpoints

### Categorías

#### Obtener una categoría por ID
- **URL**: `/categories/{id}`
- **Método HTTP**: `GET`
- **Descripción**: Obtiene una categoría por su ID.
- **Respuesta exitosa**: `200 OK`
  ```json
  {
    "id": 1,
    "name": "Category Name",
    "description": "Category Description"
  }


Obtener una categoría por ID
curl -X GET "http://localhost:8080/categories/{id}"

Crear una categoría
curl -X POST "http://localhost:8080/categories" \
-H "Content-Type: application/json" \
-d '{
"name": "Category Name",
"description": "Category Description"
}'

Obtener todas las categorías
curl -X GET "http://localhost:8080/categories"

Eliminar una categoría por ID
curl -X DELETE "http://localhost:8080/categories/{id}"


ProductController
Obtener todos los productos (paginados
curl -X GET "http://localhost:8080/products?page=0&size=10"


Obtener un producto por ID
curl -X GET "http://localhost:8080/products/{id}"


Crear un producto
curl -X POST "http://localhost:8080/products" \
-H "Content-Type: application/json" \
-d '{
"name": "Product Name",
"price": 123.45,
"description": "Product Description",
"categoryId": 1
}'

Obtener un producto con foto de categoría por ID
curl -X GET "http://localhost:8080/products/search/{id}"


