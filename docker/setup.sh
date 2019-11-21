#!/bin/bash
# author: ourfor
# date: 20191031
# enable systemd as pid 0
systemctl status mongod

cd /opt/app/findjob/db
bash ./import.sh

systemctl stop tomcat
systemctl start tomcat





