#!/bin/bash -x

LOGFILE=/tmp/acme_air_install.log
date | tee ${LOGFILE}

echo "Installing Tomcat package" | tee -a ${LOGFILE}
yum -y install tomcat tomcat-webapps tomcat-admin-webapps unzip

echo "Configuring Tomcat" | tee -a ${LOGFILE}
TOMCATPASS=`dd if=/dev/urandom bs=1 count=12 2>/dev/null | base64 -w 0 | rev | cut -b 2- | rev`
sed -i -e "s/%PASS%/$TOMCATPASS/" /root/tomcat-users.xml
echo "Tomcat Username  -  admin" > /root/tomcat
echo "Tomcat Password  -  $TOMCATPASS" >> /root/tomcat
mv -f /root/tomcat-users.xml /usr/share/tomcat/conf/tomcat-users.xml

echo "Downloading Acme Air application" | tee -a ${LOGFILE}
wget -q https://github.com/invhariharan77/DemoClusterApp/raw/master/releases/v3.0.0.0/DemoClusterApp-v3.0.0.0.war

echo "Extracting Acme Air application" | tee -a ${LOGFILE}
unzip -q DemoClusterApp-v3.0.0.0.war -d /var/lib/tomcat/webapps/DemoClusterApp

sleep 5

echo "Starting Tomcat" | tee -a ${LOGFILE}
systemctl start tomcat
systemctl enable tomcat

echo "Done" | tee -a ${LOGFILE}

