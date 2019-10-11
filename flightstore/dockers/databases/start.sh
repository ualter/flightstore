rm -rf mysql-data
mkdir mysql-data
docker-compose up -d
docker-compose logs -f --tail=20

