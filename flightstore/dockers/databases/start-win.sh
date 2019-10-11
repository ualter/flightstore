rm -rf c:/Users/mysql-data
rm -rf c:/Users/mysql-init
cp -r mysql-data c:/Users 
cp -r mysql-init c:/Users
docker-compose up -d
docker-compose logs -f --tail=20

