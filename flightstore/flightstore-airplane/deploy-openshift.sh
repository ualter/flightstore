#!/bin/bash

clear
serviceDeploy=false
configMapDeploy=false

if [ $# -gt 0 ]; then
	for var in "$@"
	do
		if [ $var == "--service" ]; then
			serviceDeploy=true
		fi
		if [ $var == "--configmap" ]; then
			configMapDeploy=true
		fi
	done
fi 

if [ "$configMapDeploy" = true ]; then
	# Create the ConfigMap
	echo "******************************************************************"
	echo "Deploy the flightstore-airplane ConfigMap to Openshift"
	echo "******************************************************************"
	oc delete configmap flightstore-airplane
	oc create configmap flightstore-airplane --from-file=src/main/resources/application.yml
fi	

echo "*********************************************************"
echo "Clean Service and Deployment-Configuration from Openshift"
echo "*********************************************************"
oc delete dc/flightstore-airplane
echo " "

echo "*********************************************************"
echo "Clean the flightstore-airplane Docker Images"
echo "*********************************************************"
for i in $(docker images --format '{{.ID}}' my-registry:5000/ualter-flightstore-airplane); do
	docker rmi -f "$i"
done
echo " "

echo "*********************************************************"
echo "Build a new flightstore-airplane Docker Image"
echo "*********************************************************"
mvn clean install -Dmaven.test.skip=true dockerfile:build
echo " "

echo "******************************************************************"
echo "Upload the flightstore-airplane Image to the Local Docker Registry"
echo "******************************************************************"
docker tag ualter/flightstore-airplane:latest my-registry:5000/ualter-flightstore-airplane
docker push my-registry:5000/ualter-flightstore-airplane
echo " "

echo "******************************************************************"
echo "Deploy the flightstore-airplane to Openshift"
echo "******************************************************************"
oc create -f openshift-deployment-configuration.yaml
echo "**********************************************************************"
echo "Checking the logs of the Deploy Configuration to Openshift"
echo "**********************************************************************"
oc logs -f dc/flightstore-airplane
echo " "


if [ "$serviceDeploy" = true ]; then
	# Create the service
	echo "******************************************************************"
	echo "Deploy the flightstore-airplane Service to Openshift"
	echo "******************************************************************"
	oc delete service flightstore-airplane
	oc create -f openshift-service.yaml
fi	

# Test
# curl -s http://$(oc get route flightstore-airplane -o json | jq -r .spec.host)/flightstore-airplane/actuator/health | jq .
# curl -s http://$(oc get route flightstore-airplane -o json | jq -r .spec.host)/flightstore-airplane/actuator/info | jq .
# curl -s http://$(oc get route flightstore-airplane -o json | jq -r .spec.host)/flightstore-airplane/api/v1/airplanes/ | jq .
# curl -s http://$(oc get route flightstore-airplane -o json | jq -r .spec.host)/flightstore-airplane/api/v1/airplanes/1 | jq .

# Copy command to Clipboard (MacOS)
echo "oc logs -f dc/flightstore-airplane" | pbcopy
echo " "
echo " <-----------> "
echo " <--- End ---> "
echo " <-----------> "
echo " "