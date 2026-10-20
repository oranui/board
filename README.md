# 게시판 API

Spring Boot와 JPA로 만든 게시판 REST API


백엔드 개발 기본기를 익히기 위해 게시글 CRUD 구현, 계층 구조 분리와 HTTP 상태 코드 처리에 신경

## 기술 스택
- Java 21, Spring Boot, Spring Data JPA, MySQL

## API 목록
| 기능         | 메서드 | 주소        |
|--------------|--------|-------------|
| 글 작성      | POST   | /posts      |
| 글 목록 조회 | GET    | /posts      |
| 글 상세 조회 | GET    | /posts/{id} |
| 글 수정      | PUT    | /posts/{id} |
| 글 삭제      | DELETE | /posts/{id} |



## 실행 방법
### 1. 필요한 환경
- Java 21
- MySQL 8.4

### 2. 데이터베이스 생성
MySQL에 접속해서 아래 SQL을 실행합니다.

```sql
CREATE DATABASE board;
```

### 3. 환경 변수 설정
MySQL root 비밀번호를 `DB_PASSWORD` 환경 변수로 설정합니다.
(IntelliJ: 실행 구성 편집 → 환경 변수)

### 4. 실행
`BoardApplication`을 실행한 뒤 `http://localhost:8080/posts`로 접속합니다.

## 고민한 점
**findById는 왜 Optional을 반환할까?**

값이 없을 수 있다는 것을 타입으로 명시해 호출하는 쪽이 null 처리를 빠뜨리지 않게 강제하고 NullPointerException 예방하기 위함. 

**없는 글 조회에 404**

404는 클라이언트 요청 문제, 500은 서버 문제. 클라이언트가 상황에 맞게 대응하고 서버쪽도 장애 구분 가능.

**setter 대신 update()**

setter는 어디서든 값이 바뀌어 변경 추적 어려움. 의미 있는 메서드로만 변경가능하게 하면 캡슐화 지켜지고 검증 로직 한 곳에 모을 수 있음.

**응답 DTO 분리**

불필요하거나 민감한 정보 노출 방지, DB 구조가 바뀌어도 API 응답 형태가 유지됨.



