FROM mysql:latest

ENV MYSQL_ROOT_PASSWORD=password
ENV MYSQL_DATABASE=reactlibrarydatabase

COPY sqldump.sql /docker-entrypoint-initdb.d/

EXPOSE 3306

CMD ["mysqld"]