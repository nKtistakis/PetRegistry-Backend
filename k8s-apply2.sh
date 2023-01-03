#!/bin/bash
sudo microk8s kubectl apply -f k8s/db/secret.yaml
sudo microk8s kubectl apply -f k8s/db/mysql-pv.yaml
sudo microk8s kubectl apply -f k8s/db/mysql-deployment.yaml
sudo microk8s kubectl apply -f k8s/db/mysql-clip.yaml
sudo microk8s kubectl apply -f k8s/spring/spring-config.yaml
sudo microk8s kubectl apply -f k8s/spring/spring-deployment.yaml
sudo microk8s kubectl apply -f k8s/spring/spring-clip.yaml
sudo microk8s kubectl apply -f k8s/react/react-deployment.yaml
sudo microk8s kubectl apply -f k8s/react/react-clip.yaml
sudo microk8s kubectl create secret generic react-tls-secret \
--from-file=tls.crt=certs/react/certificate.crt \
--from-file=tls.key=certs/react/private.key \
--from-file=ca.crt=certs/react/ca_bundle.crt
sudo microk8s kubectl apply -f k8s/react/react-ingress.yaml
sudo microk8s kubectl create secret generic spring-tls-secret \
--from-file=tls.crt=certs/spring/certificate.crt \
--from-file=tls.key=certs/spring/private.key \
--from-file=ca.crt=certs/spring/ca_bundle.crt
sudo microk8s kubectl apply -f k8s/spring/spring-ingress.yaml
