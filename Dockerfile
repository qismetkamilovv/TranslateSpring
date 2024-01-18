FROM openjdk:17 

WORKDIR /app

COPY build/libs/translate-0.0.1-SNAPSHOT.jar app.jar

ENV SPRING_DATASOURCE_URL=jdbc:mysql://host.docker.internal:3306/translator_app
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=qismet20
ENV SPRING_DATASOURCE_DRIVER-CLASS-NAME=com.mysql.cj.jdbc.Driver
ENV SPRING_JPA_SHOW-SQL=true
ENV SPRING_JPA_HIBERNATE_DDL-AUTO=update
ENV HIBERNATE_DIALECT=org.hibernate.dialect.MySQLDialect

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]