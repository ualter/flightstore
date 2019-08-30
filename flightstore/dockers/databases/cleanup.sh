docker-compose stop

docker-compose rm -f

docker volume rm -f databases_mysql-db-volume

rm -R mysql-data
