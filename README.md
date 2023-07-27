# OnboardingRetoTalentZone
Reto Sofka. Se requiere una solución Backend para el manejo de inventario y la compra de dichos productos disponibles.

Crear Apis que permitan listar, agregar, editar y eliminar productos al inventario. El listado
de productos debe contar con paginación y las respectivas validaciones para contratos de
entrada de todo el CRUD.
Ejemplo.
POST → /products/
{
“name”: ”Product name”,
“inInventory”: 500,
“enabled”: true,
“min”: 8,
“max”: 200
}
Crear Apis de compras que permiten registrar la compra, la cual debe validar
respectivamente la disponibilidad, los mínimos, máximos por producto, asentar la compra
y descontar las cantidades compradas también debemos contar con un api para ver el
historial de compras realizadas.
Ejemplo.
POST → /buys/
{
“date”: “current date with time”
“idType”: “CC”,
“id”: “103748422”,
“clientName”: “Joy”,
“products”:[{“idProduct”: "1", “quantity”: "150"}]
}

# InstruccionesParaEjecutarElCódigo:
Para este proyecto se implementó una base de datos local de Mongo, por lo cual es necesario reemplazar la url de conexión en el archivo application.properties ubicado en la ruta \spring-mongo\src\main\resources
El nombre de la base de datos es Tienda y contará con dos colecciones: Producto y Buy

Utiliza la version 11 de Java y Apache Maven 3.9.1

Para ejecutar el proyecto, ir a la terminal y ubicarse en la raíz del proyecto y lanzar el comando .\mvnw.cmd spring-boot:run



