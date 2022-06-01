mvn package
docker build -t user-service .
docker run -dp 4444:8080 user-service