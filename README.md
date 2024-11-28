# 간단 스프링 어플리케이션

## Core
### 8단계
- [x] 시간 테이블 설정
- [x] 시간 조회, 추가, 삭제 기능 추가
- [x] DB 초기 값 설정
### 9단계
- [x] 해당 시간이 존재하는 예약이 남아있으르때, 시간 삭제 못하도록 예외처리
- [x] 중복된 시간(시간의 time값이 같은) DB에 저장하지 못하도록 예외처리 
### 10단계

### 고민사항
- 프로젝트 구조:<br/>
[도메인 우선 vs 레이어 우선](https://codewithandrea.com/articles/flutter-project-structure/)
<br/>


[Repository 계층, 도메인과 영속성 엔티티 사이의 간극](https://kokodakadokok.tistory.com/entry/Repository-%EA%B3%84%EC%B8%B5-%EB%8F%84%EB%A9%94%EC%9D%B8%EA%B3%BC-%EC%97%94%ED%8B%B0%ED%8B%B0-%EC%82%AC%EC%9D%B4%EC%9D%98-%EA%B0%84%EA%B7%B9-%EB%A7%A4%EA%BE%B8%EA%B8%B0)
<br/>
스프링은 기술적으로 편의를 위해서? 
데이터베이스 테이블과 Java의 class를 매핑해준 Jpa 기술을 사용하는 것으로 알고 있습니다.
@Entity라는 annotation을 사용하여 정의하는 Class는 Domain과의 간극이 

- Entity 패키지를 따로 둔 이유? <br/>
Service는 


## JDBC
### 5단계
- [x] 데이터베이스 설정
- [x] 데이터베이스 연결

### 6단계
- [x] 데이터 조회하기

### 7단계
- [x] 데이터 추가하기
- [x] 데이터 삭제하기
- [x] 데이터 삭제 잘못된 요청시 예외처리

### 7 단계 고민
- Entity id에 setter 함수를 두면 위험성이 크다는 생각이 들어 새로운 객체를 생성해서 return 해야 되겠다 라는 생각을 가지게 되었습니다
   과연 spring은 새로 db에 생성된 entity에 관해 어떻게 id를 주입하나 궁금함군요 🤔

### 질문사항
- 어플리케이션 실행중 어디서든 exception이 발생하면 ControllerAdvice를 exception과 관련된 응답을 전달해줍니다. 
  대부분의 exception을 최종적으로 ControllerAdvice에서 처리를 하다 보니 Service, Repository, Entity 등등 아무데서나 exception을
  던질 수 있겠다라는 착각을 하게 되는것 같습니다. 혹시 exception은 보통 어느 layer에서 처리를 하는지 궁금하며 예외처리는 어떻게 관리
  되는지 궁금합니다!

