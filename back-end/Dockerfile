# base image containing Java 8 runtime
FROM java:8

LABEL MAINTAINER SungUk Hong "hsw924@gmail.com"

# Add a volume to /tmp
VOLUME /tmp

# 해당 포트를 내컨테이너에서 외부로 노출
EXPOSE 8082

# jar file
ARG JAR_FILE=build/libs/back-end-1.0-SNAPSHOT.jar


# 내 jar file을 container로 옮기기
ADD ${JAR_FILE} springboot.jar

# RUN the jar file
# security : 코드가 예기치 않게 차단될 위험 회피
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/springboot.jar"]
