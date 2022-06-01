mvn package
docker build -t rent-service .
docker run -dp 5555:8080 rent-service