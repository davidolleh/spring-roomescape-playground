# 간단 스프링 어플리케이션

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

### 7 단계 질문사항
- 어플리케이션 실행중 어디서든 exception이 발생하면 ControllerAdvice를 exception과 관련된 응답을 전달해줍니다. 
  대부분의 exception을 최종적으로 ControllerAdvice에서 처리를 하다 보니 Service, Repository, Entity 등등 아무데서나 exception을
  던질 수 있겠다라는 착각을 하게 되는것 같습니다. 혹시 exception은 보통 어느 layer에서 처리를 하는지 궁금하며 예외처리같은 것은 어떻게 관리
  되는지 궁금합니다!

