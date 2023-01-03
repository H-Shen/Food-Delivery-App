### The backend demo of a doordash-like food delivery app

```shell
# to clean all cache before building (optional)
docker system prune --all --force
# to build:
docker-compose build --no-cache
# to run:
docker-compose up -d
# to check the app logs
docker logs -f food-delivery-app-springboot
# to test with curl
curl -X GET "http://localhost:18080/api/v1/user/all" -H "accept: */*"
# to quit and clean
docker-compose down --rmi all
```
