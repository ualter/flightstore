docker tag ualter/flightstore-airplane:latest 192.168.99.100:5000/ualter-flightstore-airplane
docker tag ualter/flightstore-airplane:latest my-registry:5000/ualter-flightstore-airplane

docker push 192.168.99.100:5000/ualter-flightstore-airplane
docker push my-registry:5000/ualter-flightstore-airplane