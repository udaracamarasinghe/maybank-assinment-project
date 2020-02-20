# Brief

 Framework: Springboot
 
 DB: MySQL
 
 Server: Embedded Tomcat 
 
 Architecture: Microservice architecture
 
 Modules: authorization-server, category-management-service, merchant-management-service and product-management-service
 
 Functionalities: Create a product, Search product, Update product and Delete product
 
 Divided application into three microservices, base on domain objects(Product, Merchant, and Category) due to demonstrate the microservice behavior

# Possible options to run the application

It can run as an ordinary spring-boot application or as docker swarm cluster.

If you select to run as ordinary spring-boot applications it will have to configure database connections manually. 

# To run as an ordinary spring-boot application

Install JDK 8 or upper

Install MySQL

Configure Datasource connection properties accordingly

	spring.datasource.url=jdbc:mysql://localhost:3306/product_db?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true

	spring.datasource.username=username
	spring.datasource.password=password
	
Configure endpoint host URLs for auth-server, category service and merchant service accordingly
	
	token.endpoint.url=http://localhost:8080

	categoryservice.host=http://localhost:8080
	merchantservice.host=http://localhost:8080

Startup each spring-boot application individually.

# To run as docker swarm cluster

Install docker

Enable docker swarm

Execute ./run.sh shell script(This will build all spring-boot applications and start docker swam and deploy all applications.)

# How to test

Import postman test script JSON file which located in the root of the application directory into Postman.
   
	assisment-requests.postman_collection.json

This file included all rest API calls for Generate auth token, to search by name or category id, to create a product, to update a product and to delete a product.

# Diagrams

High-level Class diagram: https://github.com/udaracamarasinghe/maybank-assinment-project/blob/master/diagrams/Class%20Diagram.jpg

High-level search product activity diagram: https://github.com/udaracamarasinghe/maybank-assinment-project/blob/master/diagrams/Activity%20Diagram.jpg
