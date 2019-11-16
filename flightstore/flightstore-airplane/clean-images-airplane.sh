for i in $(docker images --format '{{.ID}}' my-registry:5000/ualter-flightstore-airplane); do
	docker rmi -f "$i"
done