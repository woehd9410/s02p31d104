# Helpromise
사용자에게 좀 더 편리한 일정 관리 서비스를 제공합니다. 개인, 그룹 일정을 한눈에 파악하고 일정을 관리할 수 있으며 사적인 일정과 공적인 일정을 따로 관리할 수 있는 서비스를 제공합니다. 


## Development Tools

- IntelliJ
- SpringBoot 2.1.7
- openjdk version 1.8.0_192

## Installation Process

### Properties 파일 DB 설정

- AWS RDS DB의 DB 설정들 같이 개인적인 정보가 담긴 설정들을 보호하기 위해서 properties파일들을 분리해서 관리함
- local DB나 자신의 DB 서버와 연결하고 싶다면 따로 Properties을 만들어서 관리해야함.
    1. resources - Properties 폴더 안에 application_auth.properties 파일 생성.
    2. application_auth.properties 파일 안에 사용할 DB 설정 작성

> spring.datasource.driver-class-name=  Database driver name    
spring.datasource.url= Database Url    
spring.datasource.username= Database 유저네임    
spring.datasource.password= Database 패스워드    


## Getting Started



### Local 환경에서 실행 

- com.help.back.backend package에 있는 [Application.java](http://application.java) Run
- [http://localhost:8080](http://localhost:8080) 에서 실행 확인



### Swagger

- Swagger를 사용해 API 문서를 자동화
- local환경에서 실행 후 [http://localhost:8080/swagger-ui.html에서](http://localhost:8080/swagger-ui.html에서) 확인 가능


## File Manifest

	├── README.md                 - 리드미 파일
	│
	├── src/                      - 어플리케이션 폴더
	│   │ 
	│   ├── main
	│   │   ├── java/com.help.back.backend/                        
	│   │   │                      ├── application.java        - 어플리케이션 실행 클래스 
	│   │   │                      ├── domain/                 - domain 클래스 패키지
	│   │   │                      ├── dao/                    - dao 패키지
	│   │   │                      ├── dto/                    - dto 클래스 패키지
	│   │   │                      ├── service/                - service 패키지
	│   │   │                      ├── web/                    - controller 패키지
	│   │   │                      └── config/                 - configuration 패키지
	│   │   │                          └── security/           - JWT 인증 관련 패키지	
	│   │   │
	│   │   └── resources/                                     - 프로젝트 설정 파일 폴더
	│   │       ├── mapper/                                    - mapper.xml 파일 폴더
	│   │       ├── properties/                                - 개인 properties 설정 파일 폴더
	│   │       └── application.properties                     - default properties 파일
	│   │ 
	│   └── test/                 - test 폴더
	│       ├── _java/            
	│       └── resources/        
	│ 
	├── build.gradle              - 모듈의 빌드 방법이 정의된 빌드스크립트
	├── .gitignore                - .gitignore 파일
	├── settings.gradle              
	├── gradlew          
	└── gradlew.bat                    
