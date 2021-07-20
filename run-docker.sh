#!/bin/sh
mvn package
docker build --tag e-psych .
docker run -p 8080:8080 e-psych:latest
