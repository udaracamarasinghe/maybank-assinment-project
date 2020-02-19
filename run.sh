cd ./authorization-server
mvn clean package

cd ..

cd ./category-management-service
mvn clean package

cd ..

cd ./merchant-management-service
mvn clean package

cd ..

cd ./product-management-service
mvn clean package

cd ..

docker-compose up --build --force-recreate
