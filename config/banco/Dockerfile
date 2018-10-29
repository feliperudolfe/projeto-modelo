FROM			mysql/mysql-server:5.7

MAINTAINER		Felipe Rudolfe <feliperudolfe@outlook.com>

ENV				MYSQL_ROOT_HOST %
ENV				MYSQL_ROOT_PASSWORD root
ENV				MYSQL_DATABASE modelo
ENV				MYSQL_USER user
ENV				MYSQL_PASSWORD pass

ADD				sql-scripts/ /docker-entrypoint-initdb.d/