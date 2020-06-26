FROM adoptopenjdk/openjdk14-openj9
LABEL maintainer="Ricardo"
EXPOSE 8080
VOLUME /tmp
ADD build/libs/*jar app.jar
RUN export PORT=8080 && \
    export JAVA_OPTS="-Xms128m -Xmx256m -XX:MaxMetaspaceSize=128m"
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar
