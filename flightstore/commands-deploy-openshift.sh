#0
# Add DNS at Minishift to my local docker registry
# cat /etc/hosts | grep my-registry
# minishift ssh
# sudo vi /etc/hosts

## if needed: oc create configmap flightstore-airplane --from-file=src/main/resources/application.yml
## if needed: oc create configmap flightstore-airliner --from-file=src/main/resources/application.yml

#1
#mvn clean install -Dmaven.test.skip=true

#***********************************************************
#***********************************************************
#***********************************************************

#2
#cd flightstore-airplane
#oc delete service flightstore-airplane
#oc delete dc/flightstore-airplane
#for i in $(docker images --format '{{.ID}}' my-registry:5000/ualter-flightstore-airplane); do
#	docker rmi -f "$i"
#done
#mvn clean install -Dmaven.test.skip=true dockerfile:build
#docker tag ualter/flightstore-airplane:latest my-registry:5000/ualter-flightstore-airplane
#docker push my-registry:5000/ualter-flightstore-airplane
#oc create -f openshift-deployment-configuration.yaml
#oc logs -f dc/flightstore-airplane

# Get the random PORT
# Change the openshift-service.yaml
# sed -i -e 's/targetPort: .*/targetPort: 46085/g' openshift-service.yaml

# Create the service
#oc create -f openshift-service.yaml

# Test
#curl -s http://$(oc get route flightstore-airplane -o json | jq -r .spec.host)/flightstore-airplane/actuator/health | jq .
#curl -s http://$(oc get route flightstore-airplane -o json | jq -r .spec.host)/flightstore-airplane/actuator/info | jq .
#curl -s http://$(oc get route flightstore-airplane -o json | jq -r .spec.host)/flightstore-airplane/api/v1/airplanes | jq .
#cd ..

#***********************************************************
#***********************************************************
#***********************************************************

#3
#cd flightstore-airliner
#oc delete service flightstore-airliner
#oc delete dc/flightstore-airliner
#for i in $(docker images --format '{{.ID}}' my-registry:5000/ualter-flightstore-airliner); do
#	docker rmi -f "$i"
#done
#mvn clean install -Dmaven.test.skip=true dockerfile:build
#docker tag ualter/flightstore-airliner:latest my-registry:5000/ualter-flightstore-airliner
#docker push my-registry:5000/ualter-flightstore-airliner
#oc create -f openshift-deployment-configuration.yaml
#oc logs -f dc/flightstore-airliner

# Get the random PORT
# Change the openshift-service.yaml
# sed -i -e 's/targetPort: .*/targetPort: 46085/g' openshift-service.yaml

# Create the service
#oc create -f openshift-service.yaml

# Test
#curl -s http://$(oc get route flightstore-airliner -o json | jq -r .spec.host)/flightstore-airliner/actuator/health | jq .
#curl -s http://$(oc get route flightstore-airliner -o json | jq -r .spec.host)/flightstore-airliner/actuator/info | jq .
#curl -s http://$(oc get route flightstore-airliner -o json | jq -r .spec.host)/flightstore-airliner/api/v1/airliners/ | jq .
#curl -s http://$(oc get route flightstore-airliner -o json | jq -r .spec.host)/flightstore-airliner/api/v1/airliners/1 | jq .
#cd ..


