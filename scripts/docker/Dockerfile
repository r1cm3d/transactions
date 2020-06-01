FROM adoptopenjdk/openjdk14-openj9
MAINTAINER Ricardo
EXPOSE 8080
VOLUME /tmp
ADD ../../../build/libs/*.jar app.jar
ENV PORT 8080
ENV JAVA_OPTS="-Xms128m -Xmx256m -XX:MaxMetaspaceSize=128m"
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar