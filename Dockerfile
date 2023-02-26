FROM openjdk:17-alpine
COPY . /usr/src/myapp
WORKDIR /usr/src/myapp
CMD ["java","-jar", "build/libs/api-books-grupal-0.0.1-SNAPSHOT-all.jar"]
