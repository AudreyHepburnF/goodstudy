FROM hub.c.163.com/library/java:latest
VOLUME /tmp
ADD target/elasticsearch-spring-0.0.1-SNAPSHOT.jar elasticsearch-spring.jar
ENTRYPOINT ["java","-jar","/elasticsearch-spring.jar"]