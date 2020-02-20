# Maybank-assignment-project

*Springboot application *Microservice architecture 

#run options
Can run as an ordinary spring-boot application or as docker swarm cluster

#To run as an ordinary spring-boot application
Install JDK 8 or upper
Install MySQL
Startup each app manually

#To run as docker swarm cluster
Install docker
Enable docker swarm
Execute ./run.sh shell script(This will build all spring-boot applications and start docker swam and deploy all applications.)

#Test
Import Postman file(assisment-requests.postman_collection.json) into Postman.
This file included all rest calls(Token generate, search by name, create a product, update product and delete product)

Class diagram: https://github.com/udaracamarasinghe/maybank-assinment-project/blob/master/diagrams/Class%20Diagram.jpg
