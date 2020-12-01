## Requirement

<details>
	<summary>버전 수정 이력</summary>
	<h2>v1.7 - 2020-11-23</h2>
	<ul>
		<li>스프링부트 2.4 에서 데이터베이스 커넥션 오류 해결방안 추가</li>
		<li>스프링부트 2.4부터는 `spring.datasource.username=sa`를 꼭 추가해주어야 한다. 그렇지 않으면 `Wrong user name or password` 오류가 발생한다.</li>
		<li><a href="https://github.com/spring-projects/spring-boot/pull/23693" target="_blank">Databases that support embedded and non-embedded modes are always detected as embedded  by somayaj · Pull Request #23693 · spring-projects/spring-boot · GitHub</a></li>
	</ul>
	<h2>v1.6 - 2020-10-14</h2>
	<ul>
		<li>helloController -> memberController 이미지 오류 수정 (도움주신분: 최성규님)</li>
	</ul>
	<h2>v1.5 - 2020-10-10</h2>
	<ul>
		<li>IntelliJ JDK 설치 확인 추가</li>
	</ul>
	<h2>v1.4 - 2020-09-18</h2>
	<ul>
		<li>인텔리J 커뮤니티(무료) 버전에서 `application.properties` 파일에서 키가 회색으로 인식 설명</li>
	</ul>
	<h2>v1.3 - 2020-09-07</h2>
	<ul>
		<li>윈도우 gradlew.bat -> gradlew로 변경</li>
	</ul>
	<h2>v1.2 - 2020-08-28</h2>
	<ul>
		<li>윈도우 사용자를 위한 IntelliJ 단축키 조회 방법 추가</li>
	</ul>
	<h2>v1.2 - 2020-08-28</h2>
	<ul>
		<li>윈도우 사용자를 위한 IntelliJ 단축키 조회 방법 추가</li>
	</ul>
	<h2>v1.1 - 2020-07-20</h2>
	<ul>
		<li>강의 오픈</li>
	</ul>
</details>
<br>

## Initialize Project Setting

Spring Starter(https://start.spring.io)에서 프로젝트 생성.

Dependencies

- Spring Boot Starter Thymeleaf
- Spring Boot Starter Web
- Spring Boot Devtools
- Lombok

<br>

## Library

인텔리제이에서 프로젝트를 import하면 개발자가 추가한 의존성을 네트워크를 통해 가져오게 되는데, 이 때 개발자가 의도한 라이브러리 뿐만 하니라 의존하는 상위 라이브러리까지 가져오게된다. 

Spring Boot Starter 관련 라이브러리를 의존성으로 추가하면, Spring Boot Starter뿐만 아니라 Spring Boot Start가 의존하는 Spring Core까지 프로젝트 가져오게 된다.

![](https://user-images.githubusercontent.com/33862991/100562642-a1f26a00-32ff-11eb-85ad-6bf5d3cad571.JPG)

<br>

### Log

- `spring-boot-starter-logging`

실무에선 Slf4j와 Logback 라이브러리를 이용한 Logging을 사용한다. 

왜 Log를 사용하는지?

Slf4j와 Logback은 각각 어떤 라이브러리인지?

<br>

### Test

- `spring-boot-starter-test`

**JUnit** : 테스트 프레임워크

**mockito** : Mock 라이브러리

**AssertJ** : 테스트 코드를 더 편하게 작성하게 도와주는 라이브러리

**Spring-Test** : Spring 통합 테스트 지원 라이브러리

<br>

## Docs

Spring Boot : https://spring.io/projects/spring-boot

Thymeleaf : https://www.thymeleaf.org/

<br>

## Thymeleaf

Thymeleaf는 HTML 페이지에서 작동하는 템플릿 엔진이다. 페이지 상단에 아래와 같은 타임리프 옵션을 추가하면, HTML 페이지에서 타임리프 문법을 사용할 수 있다.

~~~html
<html lang="en" xmlns:th="https://www.thymeleaf.org">
<head>
	<meta http-euiv="Content-Type" charset="text/html; charset=UTF-8">
    <title>Hello</title>
</head>
<body>
	<h1>Hello World</h1>
    안녕하세요, <span th:text="${guest}">guest</span>
</body>
</html>
~~~

위에서 `guest`는 API를 통해 가져오는 key 역할을 한다.

**HelloController.java**

~~~java
@Controller
public class HelloController {
    @GetMapping("hello/{guest}")
    public String hello(@PathVariable String guest, Model model){
        model.addAttribute("guest", guest);
        return "hello";
    }
}
~~~

GET 요청으로 URI가 `/hello/guest` 가 들어오면, `/resources/templates` 에 있는 hello.html을 반환하는 컨트롤러 메서드이다. 이 때 URI에서 hello 다음으로 들어오는 변수를 `guest` 라는 변수로 인식하도록 메서드 인자로 `@PathVariable String guest` 라고 처리해두었다. 이렇게 하면, URI에서 들어온 hello 다음의 URI를 `hello()` 인자로 던져준다.

이렇게 해서 받은 값은 Model에 넣어서 화면에 반환하여 화면에서 출력할 수 있게된다.

![](https://user-images.githubusercontent.com/33862991/100566140-90fa2680-3308-11eb-802f-fc26c1992c2e.JPG)

<br>

## Build and Run

Gradle 프로젝트를 빌드하는 명령어이다.

~~~bash
./gradlew build
~~~

![](https://user-images.githubusercontent.com/33862991/100569298-f05c3480-3310-11eb-949e-338c756b069d.JPG)

Build 하면 jar 파일이 생성되는데, 이 jar를 실행하면 웹 서버를 실행하게된다.

~~~bash
java -jar build/libs/{project-nam}.jar
~~~

그냥  IDE에서 `main()` 를 실행하면 build와 run이 동시에 실행된다.

<br>

## API 작동원리

### Spring에서 static/templates 구분하는 방법

요청이 들어오면, 일단 해당 URI를 처리하는 컨트롤러가 있는지 찾는다. 없으면 `\static` 디렉토리에서 해당 리소스를 찾는다. 만약 컨트롤러가 있다면, `\templates` 디렉토리에서 리소스를 찾는다.



### Spring Boot 컨트롤러에서 파라미터를 받는 방법

- `@PathVariable`

   - REST API를 만들 수 있다.

   - ```java
      @GetMapping("hello/{guest}")
      public String hello(@PathVariable String guest, Model model){
         model.addAttribute("guest", guest);
         return "hello";
      }
      ```

- `@RequestParam`

   - URI에 `?name=devandy`  처럼 파라미터명을 주소창에 입력해야 한다.

   - ```java
      @GetMapping("hello-mvc")
      public String helloMvc(@RequestParam("name") String name, Model model) {
         model.addAttribute("name", name); 
         return "hello-mvc";
      }
      ```

- `@ResponseBody`

   - HTML의 BODY 에 문자를 그대로 전달하는 어노테이션

   - ```java
      @GetMapping("hello-mvc2")
      @ResponseBody
      public String helloMvc2(@RequestParam("name") String name, Model model) {
         return "hello-mvc"+name;
      }
      ```

   - 일반적으로 객체를 주고받을 때 사용하며,가장 많이 사용하는 스프링부트 컨트롤러 어노테이션이다.

   - `viewResolver` 대신 `HttpMessageConverter` 가 동작한다.

      - 클라이언트의 HTTP Accept 헤더와 서버의 컨트롤러 반환 타입 정보 둘을 조합해서 `HttpMessageConverter` 선택

   - 기본 문자처리 : `StringHttpMessageConverter`

   - 기본 객체처리 : `MappingJackson2HttpMessageConverter`

      - 객체를 JSON으로 바꿔주는 대중적인 라이브러리

![](https://user-images.githubusercontent.com/33862991/100574086-93ff1200-331c-11eb-979c-776a7bf45f8f.JPG)

- `@Autowired`
   - 컨트롤러에서 이 어노테이션을 사용하면, 스프링부트 앱이 실행되면서 Spring Container에서 bean으로 등록이 된다.

<br>

## 웹 애플리케이션 계층 구조

![](https://user-images.githubusercontent.com/33862991/100575074-b09c4980-331e-11eb-92f8-f8d47ec443ec.JPG)

- Controller : 웹 MVC의 컨트롤러 역할
- Service : 핵심 비즈니스 로직 구현
- Repository : DB에 접근, 도메인 객체를 DB에 저장하고 관리
- Domain : 비즈니스 도메인 객체
   - 회원, 주문, 쿠폰 등의 DB에 저장해서 관리되는 주체



![](https://user-images.githubusercontent.com/33862991/100575078-b1cd7680-331e-11eb-9176-d8080f2ee7c0.JPG)

<br>

## Spring DI (Dependency Injection)

~~~java
@Controller
public class MemberController {

   private final MemberService memberService;

   @Autowired
   public MemberController(MemberService memberService) {
      this.memberService = memberService;
   }
}
~~~

Spring DI를 설명할 수 있는 컨트롤러 코드이다. `@Autowired` 를 생성자에 적용하면, 컨트롤러가 실행되면서 `@Autowired`가 적용된 메서드에서 `MemberService` 의 bean을 가져와서 Spring Container에 등록한다. 

그래서 컨트롤러와 서비스를 연결할 때 사용하는 어노테이션이 `@Autowired` 라고 한다. 

<br>

## Spring Bean을 등록하는 방법

### 컴포넌트 스캔과 자동 의존관계 설정

Spring Bean을 등록하기 위해서는 `@Component` 가 필요하지만, `@Controller`, `@Service`, `@Repository` 에도 `@Component` 가 포함되어 있기 때문에 Spring Bean으로 등록할 수 있다.

그럼 `@Autowired` 는 무엇을 할까?  
컨트롤러와 서비스, VO 등을 연결하는 역할을 한다.



### 제약조건

![](https://user-images.githubusercontent.com/33862991/100715874-35f52c00-33fb-11eb-878b-8e59cef18a6c.JPG)

Spring Container가 Spring Bean을 등록하기 위해서는 패키지 경로가 MainApplication의 패키지 경로와 같아야한다.

위 이미지에서 MainApplication 클래스가 `\com\inflearn\hellospringboot` 에 있는데, 이 하위 경로까지가 Spring Container가 Component Scan을 하므로 이 패키지 경로에서만 Controller, Service, Repository를 생성해야한다.



### Singleton

스프링이 Spring Container에 Bean이 등록할 때, 기본으로 싱글톤으로 등록한다. 컨테이너에 딱 하나의 객체를 등록하고 이를 공유한다는 의미이다.
