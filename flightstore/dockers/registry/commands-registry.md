## Config Local Registry

#### 1. Generate a Self Signed Certificate with Subject Alternative Name (SAN) 
```bash
###### Check the file req.conf, must have all the correct configutarion
$ openssl req -x509 -nodes -days 730 -newkey rsa:2048 -keyout domain.key -out domain.crt -config req.conf -extensions 'v3_req'

###### Check Generate Certificate
$ openssl x509 -in domain.crt -noout -text
```
#### 2. Upload to the Docker (VM) the Certificate
```bash
###### Copy the domain.crt as ca.crt to the following directory at the Docker VM
$ docker-machine ssh
$ mkdir /etc/docker/certs.d/my-registry:5000/ca.crt
# OR (without DNS at Windows)
$ mkdir /etc/docker/certs.d/192.168.99.100:5000/ca.crt
```

#### 3. Edit Host "DNS"
```bash
###### MacOS
$ sudo vi /etc/hosts
localhost	my-registry

###### Windows
192.168.99.100	my-registry
```

#### 4. Registry the Image in the Local Registry
```bash
$ docker tag ualter/flightstore-airplane:latest my-registry:5000/ualter-flightstore-airplane
# OR 
$ docker tag ualter/flightstore-airplane:latest 192.168.99.100:5000/ualter-flightstore-airplane

$ docker login (if Basic Authentication active) (ualter/1234)
$ docker push my-registry:5000/ualter-flightstore-airplane
# OR
$ docker push 192.168.99.100:5000/ualter-flightstore-airplane
```

#### 5. URLs
http://my-registry:8080

http://my-registry:5000/v2/_catalog

http://192.168.99.100:5000/v2/_catalog









```