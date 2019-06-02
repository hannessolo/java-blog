#!/usr/bin/env bash
sudo su deploy
cd ~/blog/blog
git pull
docker-compose down && docker-compose up -d;
