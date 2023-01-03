#!/bin/bash
sudo microk8s kubectl delete deploy mysql react spring
sudo microk8s kubectl delete svc react-clip spring-clip
sudo microk8s kubectl delete ing react-ingress spring-ingress
sudo microk8s kubectl delete secret react-tls-secret spring-tls-secret
