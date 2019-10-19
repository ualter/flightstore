```bash

#### 1. Generate the Certificate
# With Subject Alternative Nate (SAN) Check file req.conf
openssl req -x509 -nodes -days 730 -newkey rsa:2048 -keyout domain.key -out domain.crt -config req.conf -extensions 'v3_req'

# Check Generate Certificate
openssl x509 -in domain.crt -noout -text

#### 2. Upload to the Docker Client (VM) the Certificate

# Copy the domain.crt as ca.crt to the following directory at the Docker VM
mkdir /etc/docker/certs.d/my-registry:5000/ca.crt
mkdir /etc/docker/certs.d/192.168.99.100:5000/ca.crt


#### 3. Registry the Image in the Local Registry
docker tag ualter/flightstore-airplane:latest 192.168.99.100:5000/ualter-flightstore-airplane
docker tag ualter/flightstore-airplane:latest my-registry:5000/ualter-flightstore-airplane

docker push 192.168.99.100:5000/ualter-flightstore-airplane
docker push my-registry:5000/ualter-flightstore-airplane


http://192.168.99.100:5000/v2/_catalog
http://my-registry:5000/v2/_catalog
http://my-registry:8080





```