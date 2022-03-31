#!env bash

DIR=$(dirname "$0")

docker build $DIR -t superfish1998/cs7cs3:latest
docker push superfish1998/cs7cs3:latest