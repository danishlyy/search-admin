FROM harbor.jdk17

ADD *.jar /root/

ENTRYPOINT java  -javaagent:/home/java/skywalking-agent/skywalking-agent.jar -Dskywalking.agent.service_name=${SPRING_APPLICATION_NAME} -Dskywalking.collector.backend_service=${BACKEND_SERVICE} ${JVM:=-Xms2048m -Xmx2048m} -jar -Duser.timezone=GMT+8 /root/search-admin.jar