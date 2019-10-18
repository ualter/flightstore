#!bin/bash
mkdir -p /Users/ualter/Temp/docker-registry-htpasswd
docker run --rm --entrypoint htpasswd registry:2 -Bbn ualter 1234 > /Users/ualter/Temp/docker-registry-htpasswd
echo Start Docker Compose
echo  ********************
docker-compose up -d
echo
echo Lets follow the Logs (Ctrl+C)
echo ****************************** 
docker-compose logs -f --tail=30
echo
echo Ok!
echo

.env
#MY_REGISTRY_CERTS=certs
#REGISTRY_AUTH_PATH=/Users/ualter/Temp/docker-registry-htpasswd