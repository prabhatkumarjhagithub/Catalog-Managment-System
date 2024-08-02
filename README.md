#   Catalog Management System


The goal of this project is to build a reliable Spring Boot application for managing product inventory. The application offers a complete set of RESTful APIs for creating, reading, updating, and deleting products, and connects to a MySQL database. It features logging to help with troubleshooting, auditing to track changes, and uses enums to define product categories


Steps
A] Create a Spring Boot Project Go to the Spring Initializr website. Configure the project with the necessary dependencies:

1.Spring Web

2.Spring Data JPA

3.MySQL Driver

4.Lombok

Use Spring Boot version 3.3.2. Click on the Generate button to download the project as a ZIP file. Extract the downloaded ZIP file.

B] Open the Project in IntelliJ IDEA:

Open IntelliJ IDEA. Click on File > New > Project from Existing Sources.

Select the extracted folder and open it in IntelliJ IDEA.

C] Configure Database Connectivity:

For example:

spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name

spring.datasource.username=your_username

spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update


D] Use Postman: A tool to test APIs.

1] POST API: Add the product in Database

Endpoint: http://localhost:8080/product/add

{
  "name": "Product9",
  "brand": "BrandA",
  "description": "This is a description",
  "price": 100.0,
  "quantity": 10,
  "category": " ELECTRONICS",
  "dateAdded": "2024-08-01"
}


Response: Product added successfully

2] GET API: Get the product details by name

Endpoint: http://localhost:8080/product/getProductByName?name=Product9

Request: key= name, value=product9

Then we get the Result 

{
    "id": 9,
    "name": "Product9",
    "brand": "BrandA",
    "description": "This is a description",
    "price": "100.0",
    "quantity": 10,
    "category": "ELECTRONICS",
    "createdOn": "2024-08-02T09:02:44.939+00:00",
    "updatedOn": "2024-08-02T09:02:44.939+00:00"
}


3] GET API: Get the product details by its brand

Endpoint:http://localhost:8080/product/getProductByBrand?brand=BrandC

Request : key=brand & value=BrandC
 
 Then We get the Result
 
 {
        "id": 11,
        "name": "Product10",
        "brand": "Brandc",
        "description": "This is a description",
        "price": "100.0",
        "quantity": 10,
        "category": "ELECTRONICS",
        "createdOn": "2024-08-02T09:09:53.964+00:00",
        "updatedOn": "2024-08-02T09:09:53.964+00:00"
    }
4] PUT API: Update the available product attributes

Endpoint:   http://localhost:8080/product/updateById?productId=2

Request: key=productId and value=2

Body In JSON 

  "name": "UpdatedProductName",
  "brand": "UpdatedBrandName",
  "description": "Updated product description",
  "price": "199.99", 
  "quantity": 50,
  "category": "ELECTRONICS",
  "createdOn": "2024-08-02T09:32:29.158Z", 
  "updatedOn": "2024-08-02T09:32:29.158Z"
}

Then we give the Result
{
    "id": 2,
    "name": "UpdatedProductName",
    "brand": "UpdatedBrandName",
    "description": "Updated product description",
    "price": "199.99",
    "quantity": 50,
    "category": "ELECTRONICS",
    "createdOn": "2024-08-01T18:57:37.458+00:00",
    "updatedOn": "2024-08-02T09:49:53.462+00:00"
}
5] DELETE API: Delete the existing product details from the database

Endpoint: http://localhost:8080/product/delete?productId=15

Request: key=productId & value=15

The product is deleted Successfully


Overview:
This Spring Boot application manages product inventory using a MySQL database. It provides RESTful APIs for CRUD operations on products, with features like logging, auditing, and category management using enums.

Key Features:

Create: Add new products.
Read: Retrieve products by name, brand.
Update: Modify product details by ID or name.
Delete: Remove products from the database.
Setup Instructions:

Create Project: Use Spring Initializr to generate a Spring Boot project with Web, JPA, MySQL, and Lombok dependencies.
Configure Database: Set up database connectivity in application.properties.
Test APIs: Use Postman to interact with the APIs.
API Endpoints:

POST /product/add: Add a new product.
GET /product/getProductByName: Retrieve a product by name.
GET /product/getProductByBrand: Retrieve products by brand.
PUT /product/updateById: Update product details by ID.
DELETE /product/delete: Delete a product by ID.
Example Requests:

Add Product: POST request with product details.
Get Product by Name: GET request with name parameter.
Update Product: PUT request with productId and updated details.
Delete Product: DELETE request with productId.
