# java-ladder
사다리 타기 미션 저장소

## 리팩터링 목록 (2단계)

- 기능 목록
  - [x] 기능목록의 용어를 사물 명칭으로 통일
- `연결점`
  - [x] 도입
- 다리
  - [x] 한 다리를 두 `연결점`으로 분리한다 (기능 추가)
- 위치
  - [x] 위치는 음수일 수 없다 (기능 추가)
  - [x] 위치의 `left()`, `right()` 메서드를 `moveTo(Direction direction)` 으로 통합
- 가로줄
  - [x] 이동 방향을 알아내지 않고, `연결점`이 위치를 이동시키도록 명령한다.
- 상품
  - [x] 상품을 일급 컬렉션으로 포장
- 테스트
  - [ ] 테스트에 `@DisplayName` 사용, `@ParameterizedTest`는 name 사용
  - [ ] 변수 정리
- 코드
  - [x] if 키워드 뒤의 `!` 를 대체한다

<details>
<summary>리팩터링 목록 (1단계)</summary>

- [x] 테스트 리팩터링
  - [x] `LineTest` - DisplayName 및 메서드명, ParameterizedTest
  - [x] `LineGeneratorTest` - BridgeGenerator를 변수로, 변수명 수정
- [x] 참가자들 일급 컬렉션
- [x] 참가자 이름이 같으면 같은 참가자로 취급
- [x] 참가자 클래스명을 Person 에서 Participant로 변경
- [x] `Bridge == EXIST` 대신 doesExist() 메서드 사용
- [x] 예외 핸들러에서 예외 출력을 OutputView로 위임
- [x] Bridge 표현 방식을 OutputView로 옮긴다.
- [x] LadderEngine::makeLines 인자 간략화(참가자 수만 전달)
- [x] `OutputView::printParticipantNamesOf(ladder)` 메서드명 및 인자
  - printParticipantNamesOf(ladder) -> printNamesOf(participants)
</details>

# 프로그램 설명
## 사다리 개념도
<img src="https://user-images.githubusercontent.com/39221443/222088045-1ab3d007-59d3-40dd-a7ef-361744a67594.png" width="400">

## `다리들`로 `연결점들` 만들기
### 개념
<img src="https://user-images.githubusercontent.com/39221443/222131951-85acbb81-5519-4ce7-9fd3-9115500e6986.png" width="400">

### 예시
| 예시 3개                                                                                                                         |
|-------------------------------------------------------------------------------------------------------------------------------|
| <img src="https://user-images.githubusercontent.com/39221443/222136870-1d1e8a45-9676-4822-8de9-f28dfd14e41e.png" width="400"> |
| <img src="https://user-images.githubusercontent.com/39221443/222136874-dfe40d1c-8d33-4bdf-91cc-edc4c888afe1.png" width="400"> |
| <img src="https://user-images.githubusercontent.com/39221443/222136886-ccd9a046-2232-464a-a792-1ceaeb62d461.png" width="400"> |

1. 한 다리를 두 연결점들로 분리한다.
2. 처음, 마지막 연결점을 제외한 나머지 연결점들을 한 쌍씩 병합한다.
   - 둘 중 하나는 `유지`를 가져야한다.
   - `왼쪽`, `오른쪽`을 우선으로 따른다.
   - 둘 다 `유지`라면, `유지`로 병합한다.
   - 이 이외에는 병합할 수 없다. (잘못 생성된 `다리들` 이다.)

## 기능 목록

### 사다리

- [x] 높이는 양수만 가능하다.
- [x] 너비는 일정해야 한다
- [x] 특정 위치부터 사다리를 탈 수 있다.

### 가로줄

- [x] 다리들로 연결점을 생성해 사용한다.
- [x] 두 연결점 사이가 대칭이 아니면, `IllegalStateException`을 던진다 
- [x] 연속되는 다리가 있으면 `SerialBridgeException`을 던진다
- [x] 현재 위치에서 다음 위치로 이동시킨다

### 연결점
`왼쪽`, `오른쪽`, `유지`의 세 가지 방향이 있다.  
`--|ㅤ`, `ㅤ|--`, `ㅤ|ㅤ`
- [x] 어떤 위치를 다음 위치로 이동 시킬 수 있다.
- [x] 두 연결점 사이가 대칭인지 알 수 있다.
- [x] 두 연결점을 합칠 수 있다.

### 다리

- [x] 주어지는 다리와 연속인지 알 수 있다.
- [x] 다리가 존재하는지 알 수있다.
- [x] 두 연결점으로 분리한다.

### 사다리 게임

- [x] 참가자들의 수, 경품 수가 같아야한다.
- [x] 특정 참가자의 결과를 알 수 있다.

### 다리 생성기

- [x] 브릿지 하나를 생성한다
  - [x] RandomBridgeGenerator는 랜덤으로 브릿지 하나를 생성한다.

### 가로줄 생성기

- [x] 가로줄 하나를 생성한다
- [x] 다리가 연속되지 않게 생성한다.

### 참가자

- [x] 이름은 최소 1글자 이상 최대 5글자 이하여야한다.
- [x] 참가자 이름이 같으면 같은 참가자로 취급한다

### 참가자들

- [x] 2명 이상이어야 한다
- [x] 중복될 수 없다
- [x] 특정 참가자의 시작점을 알 수 있다.
  - [x] 특정 참가자를 찾지 못한 경우 예외를 던진다.

### 상품
- [x] 특정 위치의 상품을 알 수 있다
- [x] 특정 크기를 가지는지 알 수 있다

### 위치
- [x] 특정 방향으로 이동시킬 수 있다
- [x] 위치는 음수일 수 없다

### 사다리 게임 엔진
- [x] 참가자를 모집한다.
- [x] 경품을 입력받는다.
- [x] 사다리 높이를 입력받는다.
- [x] 사다리를 만든다.
- [x] 만든 사다리를 출력한다.
- [x] 결과를 보고싶은 사람의 경품을 출력한다.
  - [x] "all"은 모든 이의 경품을 출력하고 게임을 종료한다.

### 입력

- [x] 사람 이름은 "," 로 구분한다.
- [x] 사다리 높이를 입력받는다.
- [x] 실행 결과(경품)들을 입력받는다.
- [x] 결과를 보고싶은 사람을 입력받는다.

### 출력

- [x] 사다리를 출력한다.
    - [x] 참가자 이름을 출력한다.
    - [x] 브릿지들을 출력한다.
    - [x] 경품들을 출력한다.
- [x] 결과를 출력한다.

### 예외 처리

- [x] 잘못된 입력부터 다시 받는다