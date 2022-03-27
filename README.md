```shell
# to clean all cache before building (optional)
echo 'y' | docker system prune -a
# to build:
docker-compose build --no-cache
# to run:
docker-compose up -d
# to check the app logs
docker logs -f mealwrap-springboot
# to test with curl
curl -X GET "http://localhost:18080/user/all" -H "accept: */*"
# to quit and clean
docker-compose down --rmi all
```
