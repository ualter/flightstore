Openshift / Minishift / OC Client

```bash

# List all resouces available to interact with...
$ oc api-resources

# List the Image Streams
$ oc describe is

# Templates Openshift
$ oc get templates -n openshift

# Image Stream Openshift
$ oc get is -n openshift

# New Application from Git Repository
$ oc new-app https://github.com/sclorg/cakephp-ex
$ oc new-app /home/user/code/myapp --strategy=docker

# New Application from Image
$ oc new-app mysql

# New Application from a Private Image Registry
$ oc new-app myregistry:5000/example/myimage    (--insecure-registry, parameter in case not https, insecure)

# New an Application from a Template
$ oc create -f examples/sample-app/application-template-stibuild.json
$ oc new-app ruby-helloworld-sample

# Specifying Environment Variables
$ oc new-app openshift/postgresql-92-centos7 \
    -e POSTGRESQL_USER=user \
    -e POSTGRESQL_DATABASE=db \
    -e POSTGRESQL_PASSWORD=password

```
