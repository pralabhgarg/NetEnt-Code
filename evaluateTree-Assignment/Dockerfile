FROM openjdk:8-jre-alpine

LABEL originalAuthor="Pralabh Garg "

LABEL description="This is a docker image for the module"

RUN apk add --no-cache tini

WORKDIR /usr/local/server

COPY target/*.jar ./evaluateTree.jar

EXPOSE 8080

ENTRYPOINT ["/sbin/tini","-g","--"]

CMD ["sh","-c","java ${JAVA_OPTS} -jar evaluateTree.jar"]

