# Untitled

Created By: 응재 이
Last Edited: May 21, 2020 5:19 PM

## Development Tools

- IntelliJ
- SpringBoot 2.1.7
- openjdk version 1.8.0_192

## Properties 파일 DB 설정

- AWS RDS DB의 DB 설정들 같이 개인적인 정보가 담긴 설정들을 보호하기 위해서 properties파일들을 분리해서 관리함
- local DB나 자신의 DB 서버와 연결하고 싶다면 따로 Properties을 만들어서 관리해야함.
    1. resources - Properties 폴더 안에 application_auth.properties 파일 생성.
    2. application_auth.properties 파일 안에 사용할 DB 설정 작성

> spring.datasource.driver-class-name=  Database driver name
spring.datasource.url= Database Url
spring.datasource.username= Database 유저네임
spring.datasource.password= Database 패스워드

## Local환경에서 실행

- com.help.back.backend package에 있는 [Application.java](http://application.java) Run
- [http://localhost:8080](http://localhost:8080) 에서 실행 확인

## Swagger

- Swagger를 사용해 API 문서를 자동화
- local환경에서 실행 후 [http://localhost:8080/swagger-ui.html에서](http://localhost:8080/swagger-ui.html에서) 확인 가능