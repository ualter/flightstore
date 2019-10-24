Openshift / Minishift / OC Client

```bash

# List all resouces available to interact with...
$ oc api-resources

# List all Openshift Templates
$ oc get templates -n openshift

# List the Image Streams
$ oc describe is

# Templates Openshift
$ oc get templates -n openshift

# Describe Secrets in YAML/JSON format
$ oc get secret/my-registry-secret -o yaml
$ oc get secret/my-registry-secret -o json

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
    
# Specifying Enviroment Variable via .env file
$ cat postgresql.env
POSTGRESQL_USER=user
POSTGRESQL_DATABASE=db
POSTGRESQL_PASSWORD=password
$ oc new-app openshift/postgresql-92-centos7 --env-file=postgresql.env

# Check Permissions
$ oc auth can-i delete projects
$ oc auth can-i list projects
$ oc whoami

```


More: https://docs.okd.io/latest/dev_guide/application_lifecycle/new_app.html
