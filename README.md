
# A Dockerized REST-API Server implemented with Spring together with a MySQL Database

# Installation Guide

## How to install in a Docker environment

### Step 1
Make sure you have *docker* and *docker-compose* installed on your machine

### Step 2
Clone this project

### Step 3
Open a terminal in the project directory and run the following command:
`docker-compose up -d`

### To see the logs:
`docker-compose logs -f`



## How to deploy to a Kubernetes Cluster


### Step 1
Make sure you have *microk8s* installed and running on your local machine

### Step 2
* Cd to the project directory

* Execute k8s-apply.sh
This script is responsible for creating all required kubernetes entities like *PersistentVolumeClaims*, *Deployments*, *Cluster IPs*, *Configs* and *Secrets*



### Congratulations!! The server is up and running at: -> http://localhost:8080
