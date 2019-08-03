#!/usr/bin/env bash
sudo su deploy
cd ~/blog/blog
git pull
docker-compose down && mvn package && docker-compose up --build -d;
