```bash

# Install Archetype
$ mvn install

# AND updates archetype catalog
$ mvn install archetype:update-local-catalog

# Calls plugin archetype goal crawl 
$ mvn archetype:crawl

# Usage:
$ mvn archetype:generate -DarchetypeGroupId=ujr.flightstore -DarchetypeArtifactId=flightstore-archetype -DarchetypeVersion=1.0 -DgroupId=ujr.flightstore -DartifactId=flightstore-airliner -DinteractiveMode=false -DmicroService=airliner


```
