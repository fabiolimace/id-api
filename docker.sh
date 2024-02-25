./mvnw clean package && docker image build --tag example/id-api .

docker container rm -f id-api || true

docker run -d --name id-api -p 8080:8080 example/id-api

