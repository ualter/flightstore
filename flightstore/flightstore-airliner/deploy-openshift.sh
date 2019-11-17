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
	echo "Deploy the flightstore-airliner ConfigMap to Openshift"
	echo "******************************************************************"
	oc delete configmap flightstore-airliner
	oc create configmap flightstore-airliner --from-file=src/main/resources/application.yml
fi	

echo "*********************************************************"
echo "Clean Service and Deployment-Configuration from Openshift"
echo "*********************************************************"
oc delete dc/flightstore-airliner
echo " "

echo "*********************************************************"
echo "Clean the flightstore-airliner Docker Images"
echo "*********************************************************"
for i in $(docker images --format '{{.ID}}' my-registry:5000/ualter-flightstore-airliner); do
	docker rmi -f "$i"
done
echo " "

echo "*********************************************************"
echo "Build a new flightstore-airliner Docker Image"
echo "*********************************************************"
mvn clean install -Dmaven.test.skip=true dockerfile:build -Pminishift
echo " "

echo "******************************************************************"
echo "Upload the flightstore-airliner Image to the Local Docker Registry"
echo "******************************************************************"
docker tag ualter/flightstore-airliner:latest my-registry:5000/ualter-flightstore-airliner
docker push my-registry:5000/ualter-flightstore-airliner
echo " "

echo "******************************************************************"
echo "Deploy the flightstore-airliner to Openshift"
echo "******************************************************************"
oc create -f openshift-deployment-configuration.yaml
echo "**********************************************************************"
echo "Checking the logs of the Deploy Configuration to Openshift"
echo "**********************************************************************"
oc logs -f dc/flightstore-airliner
echo " "


if [ "$serviceDeploy" = true ]; then
	# Create the service
	echo "******************************************************************"
	echo "Deploy the flightstore-airliner Service to Openshift"
	echo "******************************************************************"
	oc delete service flightstore-airliner
	oc create -f openshift-service.yaml
fi	

# Test
# curl -s http://$(oc get route flightstore-airliner -o json | jq -r .spec.host)/flightstore-airliner/actuator/health | jq .
# curl -s http://$(oc get route flightstore-airliner -o json | jq -r .spec.host)/flightstore-airliner/actuator/info | jq .
# curl -s http://$(oc get route flightstore-airliner -o json | jq -r .spec.host)/flightstore-airliner/api/v1/airliners/ | jq .
# curl -s http://$(oc get route flightstore-airliner -o json | jq -r .spec.host)/flightstore-airliner/api/v1/airliners/1 | jq .

# Copy command to Clipboard (MacOS)
echo "oc logs -f dc/flightstore-airliner" | pbcopy
echo " "
echo " <-----------> "
echo " <--- End ---> "
echo " <-----------> "
echo " "