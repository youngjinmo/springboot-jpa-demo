# Requirement

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

# Initialize Project Setting

Spring Starter(https://start.spring.io)에서 프로젝트 생성.

Dependencies

- Spring Boot Starter Thymeleaf
- Spring Boot Starter Web
- Spring Boot Devtools
- Lombok

<br>

# Library

인텔리제이에서 프로젝트를 import하면 개발자가 추가한 의존성을 네트워크를 통해 가져오게 되는데, 이 때 개발자가 의도한 라이브러리 뿐만 하니라 의존하는 상위 라이브러리까지 가져오게된다. 

Spring Boot Starter 관련 라이브러리를 의존성으로 추가하면, Spring Boot Starter뿐만 아니라 Spring Boot Start가 의존하는 Spring Core까지 프로젝트 가져오게 된다.

![](https://user-images.githubusercontent.com/33862991/100562642-a1f26a00-32ff-11eb-85ad-6bf5d3cad571.JPG)

<br>

## Log

- `spring-boot-starter-logging`

실무에선 Slf4j와 Logback 라이브러리를 이용한 Logging을 사용한다. 

왜 Log를 사용하는지?

Slf4j와 Logback은 각각 어떤 라이브러리인지?

<br>

## Test

- `spring-boot-starter-test`

**JUnit** : 테스트 프레임워크

**mockito** : Mock 라이브러리

**AssertJ** : 테스트 코드를 더 편하게 작성하게 도와주는 라이브러리

**Spring-Test** : Spring 통합 테스트 지원 라이브러리

<br>

# Docs

Spring Boot : https://spring.io/projects/spring-boot

Thymeleaf : https://www.thymeleaf.org/

<br>

# Thymeleaf

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

# API 작동원리

## Spring에서 static/templates 구분하는 방법

요청이 들어오면, 일단 해당 URI를 처리하는 컨트롤러가 있는지 찾는다. 없으면 `\static` 디렉토리에서 해당 리소스를 찾는다. 만약 컨트롤러가 있다면, `\templates` 디렉토리에서 리소스를 찾는다.



## Spring Boot 컨트롤러에서 파라미터를 받는 방법

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

<br>

# 웹 애플리케이션 계층 구조 (MVC)

![](https://user-images.githubusercontent.com/33862991/100575074-b09c4980-331e-11eb-92f8-f8d47ec443ec.JPG)

- Controller : 웹 MVC의 컨트롤러 역할
- Service : 핵심 비즈니스 로직 구현
- Repository : DB에 접근, 도메인 객체를 DB에 저장하고 관리
- Domain : 비즈니스 도메인 객체
   - 회원, 주문, 쿠폰 등의 DB에 저장해서 관리되는 주체

## MVC 아키텍쳐를 사용하는 이유

URI로 요청이 들어왔을때 컨트롤러에서 직접 비즈니스 로직을 처리하는대신 서비스를 통해서 비즈니스 로직을 구현한다.

서비스는 도메인 리포지토리를 통해 DB를 제어하는데, 이런 관계를 **의존관계** 라고 한다.
이렇게 역할에 따라 분리함으로써 각각의 책임을 분명하게 분리할 수 있고 결합도를 느슨하게 낮출수 있다. 

웹 MVC에서 결합도는 느슨하게 하여 의존 관계를 만드는 이유는 향후 서비스 확장 등의 유지보수시에 여러 파일을 한꺼번에 수정하지 않아도 수정하고자 하는 기능(역할)의 책임소재가 있는 영역만 수정하면 되므로 훨씬 용이하다는 장점이 있기 때문이다.

<br>

## 클래스 의존관계

![](https://user-images.githubusercontent.com/33862991/100575078-b1cd7680-331e-11eb-9176-d8080f2ee7c0.JPG)

Repository는 DB를 관리, 제어하는 클래스인데 이렇게 Interface로 분리하는 이유는 **개방폐쇄원칙에서 확장성을 갖추기 위함** 이다. 이렇게 의존관계를 갖게되면, 실제로 다른 클래스(MemberService)에서 참조하는건 interface이므로 구현체(MemoryMemberRepository)를 변경하더라도 다른 클래스를 변경할 필요가 없어진다.

즉 아직 DBMS를 결정하지 않았을때 일단 위의 의존관계로 개발하고, 나중에 Repository만 교체하면 DB를 변경할 수 있다.

**SpringConfig.class**

~~~java
@Configuration
public class SpringConfig {
   @PersistenceContext
   private EntityManager em;
    
   @Autowired
   public SpringConfig(EneityManager em){
      this.em = em;
   }
   
   @Bean
   public MemberRepository memberRepository(){
   // return new MemoryMemberRepository();
   // return new JdbcMemberRepository(datasource);
   // return new JdbcTemplateMemberRepository(datasource);
      return new JpaMemberRepository(em);
   }
}
~~~

SpringConfig를 생성해서 이를 통해 DB를 관리하는 코드이다. `memberRepository()` 를 통해 무엇을 return 하느냐에 따라 DB 연결을 다르게 할 수 있다.

<br>

# Spring Bean을 등록하는 방법

## Component Scan

Spring Bean으로 등록해야하는 이유는 스프링은 스프링 컨테이너에 등록되어 있는 Bean을 가지고 앱을 실행하는데, 여기에 Bean으로 등록되어 있지 않으면 스프링 컨테이너에서 무엇을 실행해야할지 알 수 없기 때문에 반드시 Bean으로 등록해주어야한다.

Spring Bean을 등록하기 위해서는 `@Component` 가 필요하지만, `@Controller`, `@Service`, `@Repository` 에도 `@Component` 가 포함되어 있기 때문에 Spring Bean으로 등록할 수 있다.

<br>

### 제약조건

![](https://user-images.githubusercontent.com/33862991/100715874-35f52c00-33fb-11eb-878b-8e59cef18a6c.JPG)

스프링 컨테이너가 Spring Bean을 등록하기 위해서는 패키지 경로가 MainApplication의 패키지 경로와 같아야한다.

위 이미지에서 MainApplication 클래스가 `\com\inflearn\hellospringboot` 에 있는데, 이 하위 경로까지가 스프링 컨테이너가 Component Scan을 하므로 이 패키지 경로에서만 Controller, Service, Repository를 생성해야한다.

### Singleton

스프링이 스프링 컨테이너에 Bean이 등록할 때, 기본으로 싱글톤으로 등록한다. 컨테이너에 딱 하나의 객체를 등록하고 이를 공유한다는 의미이다. 이렇게하면 메모리 자원을 절약 할 수 있다는 장점이 있다.

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

Spring DI를 설명할 수 있는 컨트롤러 코드이다. `@Autowired` 가 적용된 생성자를 생성하면, 컨트롤러가 실행되면서 `@Autowired`가 적용된 생성자를 스프링 컨테이너에 Bean으로 등록하면서 생성자에서 사용하는 `MemberService` 의 bean을 가져와서 `MemberController`의 Bean과 연결해준다.

그래서 컨트롤러와 서비스를 연결할 때 사용하는 어노테이션을 `@Autowired` 라고 한다. 

스프링앱의 의존관계에 따라 각각의 Bean을 서로 연결해주는 역할을 하기때문에 매우 중요한 역할을 한다고 볼 수 있다.

Controller와 마찬가지로 Service에서도 `@Autowired` 생성자를 만드는 이유도 같다.

~~~java
@Service
public class MemberService {
   private final MemberRepository memberRepository;
    
   @Autowired
   public MemberService(MemoryMemberRepository repository){
      this.memberRepository = repository;
   }
}
~~~

첨언하면, 생성자의 파라미터로 사용되는 `MemoryMemberRepository`는 `MemberRepository` 인터페이스의 구현체이다. 

~~~java
@Repository
public class MemoryMemberRepository implements MemberRepository{ ... }
~~~

<br>

# Web MVC 작동원리 (값 입력받기)

화면에서 입력받은 값을 컨트롤러를 태우는 과정은 다음과 같다.

~~~html
<form action="/members/new" method="post">
   <div class="form-group">
      <label for="name">이름</label>
      <input type="text" id="name" name="name" placeholder="이름을 입력하세요.">
   </div>
   <a href="/"><button>돌아가기</button></a>
   <button type="submit">등록</button>
</form>
~~~

화면에서 값을 입력받는 태그는 `<input>` 태그이다. `<input>` 태그안에 `name` 이라는 속성을 추가하면, 이 `name` 의 속성값을 통해 스프링이 스프링 컨테이너로 입력값을 이동시킨다.  

~~~java
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberForm {
    private String name;
}
~~~

이렇게 이동한 값은 VO에서 관리되는데, 일반적으로 VO에서는 필드에 대한 접근제한자를 `private` 로 하기때문에 외부에서 접근이 안된다. 따라서 `setter()`에 의해 스프링 컨테이너에서 사용할 수 있는 객체로 변환된다. 

위의 코드는 lombok을 사용하여 `setter()`를 대신한 코드이다. 아래는 `setter()` 예시 코드이다.

~~~java
private String name;
public void setName(String name){
    this.name = name;
}
~~~

여기서 `setter()`가 받는 파라미터명은 화면에서 `<input>` 태그의 `name` 속성값과 같아야한다.

![](https://user-images.githubusercontent.com/33862991/100821057-f24e0100-3492-11eb-85ea-9e4f428cb497.JPG)

디버깅 모드로 확인하면 값이 잘 넘어왔음을 확인할 수 있다.

<br>

# Web MVC 작동원리 (값 조회하기)

위에서 회원가입한 회원목록을 조회하는 원리는 다음과 같다.

회원목록을 보여주는 원리는 서버에서 가져온 객체를 화면으로 뿌려주어야한다. 이 때 Thymeleaf, Model 객체가 사용된다.

~~~html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<body>
    <a href="/"><button>돌아가기</button></a>
    <div class="container">
        <table>
            <tbody>
                <tr th:each="member : ${members}">
                    <td th:text="${member.id}"></td>
                    <td th:text="${member.name}"></td>
                </tr>
            </tbody>
        </table>
    </div>
</body>
</html>
~~~

`<body>` 안에서 `th` 속성을 이용하면 `<html>` 안의 `xmlns` 태그와 매핑되어 Thymeleaf 문법이 적용된다.

`th:Each="member : ${members}"`는 서버에서 `members`라는 객체를 가져와서 `member`라는 이름으로 화면에서 사용하겠다는 코드이다. 보통 List나 Array같은 자료구조 데이터를 화면에서 사용할때 사용하는 코드이다. 

서버에서 `members` 라는 객체를 어떻게 관리하는지 확인해보자.

~~~java
@GetMapping("/members")
public String list(Model model){
   List<Member> members = memberService.findMembers();
   model.addAttribute("members",members);

   return "members/memberList";
}
~~~

컨트롤러에서 `memberService`를 통해 가져온 회원목록을 `members`라는 이름의 제네릭타입 List로 저장해두었다. 그리고 이를 `Model` 객체의 인스턴스에 저장하여 화면에서 쓸수있게 하였다. 이 Model 객체 덕분에 서버에 있는 데이터를 화면으로 가져가서 사용할 수 있게 된 것이다.

`MemberService`의 `findMembers()`는 다음과 같다.

~~~java
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    
    @Autowired
    public MemberService(MemoryMemberRepository repository){
        this.memberRepository = repository;
    }
    
    /**
     *  전체 회원 조회
     * @return
     */
    public List<Member> findMembers() {
       return memberRepository.findAll();
    }
}
~~~

`MemberRepository`의 `findAll()`이다.

~~~java
@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    
    @Override
    public List<Member> findAll() {
       ArrayList list = new ArrayList<>(store.values());
       return new ArrayList<>(store.values());  // map을 list로 변환해서 반환
    }
}
~~~

회원 id와 이름을 저장하는 객체로 `store` 라는 이름의 Map 데이터를 `static` 키워드로 생성해두고, 각 메서드에서 이 `store` 객체에서 값을 꺼내서 사용하는 방식이다.

`findAll()`에서는 `List` 타입으로 데이터타입을 변환하여 반환하는 기능을 한다.그럼 Service에서 받아서 컨트롤러로 다시 반환하고, 최종적으로 컨트롤러에서 이를 `Model` 객체에 담아서 화면에 보내는 원리이다.

<br>

# H2 Database

H2 Database [홈페이지](https://h2database.com/html/download.html)에서 H2 Database를 설치한다.

맥 사용자는 H2 Database를 설치하고 설치한 디렉토리로 이동하여 H2 Databse를 실행할 수 있는 권한을 부여한다.

~~~bash
$ cd h2/bin
$ chmod 755 h2.sh
~~~

![](https://user-images.githubusercontent.com/33862991/100830060-2aab0a80-34a6-11eb-8c45-5653c893d715.JPG)

실행하는 명령어는 다음과 같다.

~~~bash
$ ./h2.sh
~~~

![](https://user-images.githubusercontent.com/33862991/100832005-487a6e80-34aa-11eb-9ef8-d2809cb0784e.JPG)

이 상태에서 Connect를 클릭하면 콘솔을 통해 Database를 접속할 수 있다.

위의 코드를 인메모리에 저장할 간단한 테이블을 생성한다.

~~~sql
DROP TABLE IF EXISTS member CASCADE;

CREATE TABLE member(
   id bigint generated by default as identity,
   name varchar2(255),
   primary key (id)
);

SELECT * FROM member;
~~~

CREATE 문을 설명해보면, `bigint` 는 자바에서 `Long` 타입에 해당하는 데이터 타입이다. `generated by default as identity`는 id 값이 없이 insert되는 데이터는 자동으로 id를 삽입한다는 쿼리이다.

![](https://user-images.githubusercontent.com/33862991/100833283-fab33580-34ac-11eb-9ebf-3fd9c7170c83.JPG)

~~~sql
INSERT INTO member(name) VALUES('devandy');
SELECT * FROM member;
~~~

<br>

# 통합테스트

### @SpringBootTest

스프링 컨테이너와 테스트를 함께 실행하는 어노테이션.

### @Transactional

테스트 케이스에 애노테이션이 있으면, 테스트 시작 전에 트랜잭션을 시작하고, 테스트 완료 후에 항상 롤백한다. 이렇게 하면 DB에 데이터가 남지 않으므로 다음 테스트에 영향을 주지 않는다.

<br>

# JdbcTemplate

스프링 JdbcTemplate와 MyBatis같은 라이브러리는 JDBC API에서 본 반복 코드를 대부분 제거해준다. 하지만 SQL은 직접 작성해야한다.

~~~java
@Repository
public class JdbcTemplateMemberRepository implements MemberRepository {
   private final JdbcTemplate jdbcTemplate;
	
    ...
   
   @Override
   public Optional<Member> findById(Long id){
       List<Member> result = 
           jdbcTemplate.query("select * from member where id = ?",memberRowMapper());
       return result.stream().findAny();
    }
   
    ...
   
    private RowMapper<Member> memberRowMapper(){
       return (rs, rowNum) -> {
          Member member = new Member();
          member.setId(rs.getLong("id"));
          member.setName(rs.getString("name"));
          return member;
       }
    }
}
~~~

JDBC Template는 JDBC 코어 패키지를 갖는 객체로서 JDBC를 더 편하게 사용할 수 있도록 돕는 역할을 한다.
SQL 쿼리를 실행하여 결과를 반환받는다고 설명되어 있다.

<br>

# JPA

![출처 : victolee님 블로그](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=http%3A%2F%2Fcfile24.uf.tistory.com%2Fimage%2F9948AD435AE3CFAE2B0AB5)

- **JPA(Java Persistent API)** 는 ORM을 위한 인터페이스 집합체이다.
- **ORM(Object Relational Mapping)** 은 객체가 테이블이 되도록 매핑 시켜주는 것을 의미하며, ORM을 이용하면 SQL Query가 아닌 직관적인 코드(method)로서 데이터를 제어할 수 있다.
- ORM이란, 실제 JPA를 구현하는 ORM 프레임워크는 Hibernate, EclipseLink, DataNucleus 등이 있다.

![출처: Heee's Development Blog](https://gmlwjd9405.github.io/images/inflearn-jpa/jpa-basic-structure.png)

- 출처
   - [victolee - [Spring JPA] ORM과 JPA 그리고 Hibernate](https://victorydntmd.tistory.com/195)
   - [Heejeong Kwon - [JPA] JPA란](https://gmlwjd9405.github.io/2019/08/04/what-is-jpa.html)

<br>

## JPA로 변환하기

**build.gradle**

~~~gradle
dependencies {
   implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
}
~~~

**application.properties**

~~~properties
spring.datasource.url=jdbc:h2:tcp:localhost/~/test
spring.datasource.driverClassName=org.h2.Driver
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=none
~~~

`spring.jpa.hibernate.ddl-auto` 는 테이블을 자동으로 생성해주는 기능인데, 이 기능을 잠시 꺼두고 개발을 진행한다.

VO에서 데이터 필드를 JPA에서 인식하는 객체로 맵핑하려면 어노테이션을 추가해야한다.

<a name="jpa-domain"></a>**Member.class**

```java
import lombok.*;
import javax.persistence.*;

@Entity
@ToString
@Getter @Setter
public calss Member {
   @Id @GeneratedValue(strategy = GenetionType.IDENTITY)
   private Long id;
    
   @Column(name = "username")
   private String name;
}
```

어노테이션으로 `@Id` 를 부여하면 테이블에서 PK로 인식하도록 한다. 
`@GeneratedValue`는 사용자가 값을 넣을때 따로 부여하지 않아도 디폴트로 id값을 생성하도록 하는 어노테이션이다. 

<br>

## JpaMemberRepository 생성

~~~java
@Transactional
public class JpaMemberRepository implements MemberRepository {
    
    private final EntityManager em;
    
    public JpaMemberRepository(EntityManager em){
       this.em = em;
    }
    
    ...
}
~~~

`EntityManager` 는 `DataSource`를 갖고있어서 실제로 객체와 테이블을 매핑하는 역할을 수행한다. 

### JPA로 INSERT 구현

~~~java
@Transactional
public class JpaMemberRepository implements MemberRepository {
    private final EntityManger em;
    
    public JpaMemberRepository(EntityManger em){
       this.em = em;
    }
    
    ...
        
    @Override
    public Member save(Member member){
       em.persist(member);
       return member;
    }
}
~~~

`EntityManager`의 `.persis()`를 사용하면, `EntityManager`에서 `member` 객체를 매핑하여 이렇게 매핑한 데이터를 `INSERT` 쿼리로 만들어서 테이블에 삽입한다.

### JPA로 SELECT 구현

~~~java
@Override
public Optional<Member> findById(Long id){
    Member member = em.find(Member.class, id);
    return Optional.ofNullable(member);
}  
~~~

JPA를 이용하면 이처럼 직접 SQL을 입력하지 않아도 메서드만으로 쿼리를 생성할 수 있다.

`findById()` 에서 사용한 `EntityManager`의 `find()` 를 열어보면 다음과 같다.

![](https://user-images.githubusercontent.com/33862991/101302732-c3af9c00-387f-11eb-89d4-aebf21c1a6f2.JPG)

파라미터로 EntityClass와 Primary Key를 받는다고 한다.

[도메인](#jpa-domain)에서 `id`를 Member 테이블에 대한 PK로 지정했으므로 파라미터에 `Member` 클래스와 PK에 해당하는 id를 넣어주면, `.find(Member.class, id)` 라고만 해도 `id`를 통해서 Member 테이블 조회를 하는 쿼리를 생성하는 것이다.

### JPQL로 SELECT 구현 

~~~java
@Override
public List<Member> findAll(){
    return em.createQuery("SELECT m FROM member m", Member.class)
        .getResultList();
}
~~~

JPA는 JPA에서 제공하는 메서드만으로 섬세한 쿼리 작성이 힘들다는 단점이 있다. 이를 극복하기 위해 탄생한게 **JPQL(Java Persistence Query Language)** 이다.

- 읽어보면 좋을 글
   - [victolee - [Spring JPA] JPQL](https://victorydntmd.tistory.com/205)

위의 JPQL 쿼리에서 인상깊은 점은 SQL을 사용하더라도 **쿼리를 조회하는 대상이 테이블이 아니라 객체(Entity)** 라는 사실이다.

JPQL에서 객체(Entity)를 대상으로 조회할 수 있는건 **JPA에서 객체를 미리 매핑** 해두었기 때문에 가능한 일이다. 그래서

```sql
SELECT id, name FROM member
```

라고 작성하지 않고,

~~~sql
SELECT m FROM member m
~~~

처럼 객체를 대상으로 객체를 조회하더라도 해당 객체를 매핑해둔 필드를 SQL처럼 조회가 가능한 것이다. 즉 JPA이기 때문에 가능한 쿼리인 셈이다.

### JPQL로 파라미터 받아서 조회(SELECT)하기

이름(`name`)을 파라미터로 받아서 해당 이름의 Member 객체를 조회하는 쿼리를 생성하는 코드이다.

~~~java
@Override
public Optional<Member> findByName(String name) {
    List<Member> result = em.createQuery("SELECT m FROM member m WHERE m.name = :name", Member.class)
        .setParameter("name", name)
        .getResultList();
}
~~~

JPQL 쿼리에서 `:name` 이라는 쿼리가 실제 파라미터를 받는 영역이다.

~~~java
.setParameter("name", name);
~~~

 이 쿼리를 통해서 JPQL에 들어가는 파라미터 변수명을 매핑한다. 그럼 `findByName()` 메서드가 받는 파라미터를 JPQL 쿼리로 매핑해서 조회를 하게 된다.

그리고 `.getResultList()` 를 통해 JPQL 쿼리 결과를 `List` 타입으로 반환받게 된다.

<br>

# Spring Data JPA

스프링 데이터 JPA를 이용하면, 리포지토리에 구현 클래스 없이도 인터페이스만으로 개발을 완료할 수 있다.

기본적인 CRUD 기능을 스프링 데이터 JPA가 제공하기 때문에 개발자의 생산성이 증가한다.

스프링 데이터 JPA는 JPA를 편리하게 사용할 수 있는 기술이므로 JPA를 먼저 학습한 후 스프링 데이터 JPA를 학습해야 한다.

![](https://user-images.githubusercontent.com/33862991/101315030-295e5100-389d-11eb-8baf-95b15b2fc915.JPG)