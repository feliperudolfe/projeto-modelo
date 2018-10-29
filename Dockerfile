FROM			jboss/wildfly:10.1.0.Final

MAINTAINER		Felipe Rudolfe <feliperudolfe@outlook.com>

USER			root

ADD				config/wildfly/standalone/configuration/standalone.xml /opt/jboss/wildfly/standalone/configuration/standalone.xml
ADD				config/wildfly/modules/system/layers/base/com/mysql/main/module.xml /opt/jboss/wildfly/modules/system/layers/base/com/mysql/main/module.xml
ADD				config/wildfly/modules/system/layers/base/com/mysql/main/mysql-connector-java-5.1.40-bin.jar /opt/jboss/wildfly/modules/system/layers/base/com/mysql/main/mysql-connector-java-5.1.40-bin.jar
ADD				target/modelo.war /opt/jboss/wildfly/standalone/deployments/ROOT.war

EXPOSE			80

RUN 			/opt/jboss/wildfly/bin/add-user.sh felipe.rudolfe @senha123 --silent

CMD				["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]