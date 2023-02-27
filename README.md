# java-ladder

사다리 타기 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

## 리팩터링 목록 (2단계)

### '연결점' 개념 도입하기
- 다리 개수와 위치 개수의 간극을 극복한다.  
  <img src="https://user-images.githubusercontent.com/39221443/221102919-4d5566f6-05cc-43e5-a468-418dfa6bdbc0.png" width="400">

- [x] '연결점'을 구현한다.
- [x] LineGenerator가 연결점들을 생성하도록 대체한다.
  - [x] 우선 연결점들을 생성하고, 다시 다리로 합쳐서 반환해본다.
    - 다른 프로덕션 코드에 변화가 전파되지 않는다.
    - 기존 테스트를 활용해 검증할 수 있다.
  - [x] Line 리팩터링 후 LineGenerator도 연결점들로 완전 대체한다.
- [x] Line의 다리들을 연결점들로 대체한다.
  - [x] 기존 생성자를 손대지 않고 내부 구현만 대체해본다.
  - [x] 완전 대체한다.
  - [x] 테스트에서 생성하는 다리들도 연결점들로 대체한다.

### 그 외
- [x] 연결점에서 방향 enum을 사용한다
- [x] 다리가 연속되면 IllegalArgumentException 대신, SerialBridgeException을 던진다


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

## 기능 목록

### 연결점(Point)
`왼쪽`, `오른쪽`, `유지`의 세 가지 방향이 있다.  
`ㅤ|--`, `--|ㅤ`, `ㅤ|ㅤ`
- [x] 다음 다리에 맞게 다음 연결점을 생성한다.
  - 다리가 있다면 현재 연결점에서 우측으로 가지를 뻗는다.
- [x] 다음 연결점을 생성할 때, 다리가 연속되면 SerialBridgeException을 던진다.
- [x] 연결점과 연결점 사이가 대칭인지 알 수 있다.

### 참가자

- [x] 이름은 최소 1글자 이상 최대 5글자 이하여야한다.
- [x] 참가자 이름이 같으면 같은 참가자로 취급한다

### 참가자들

- [x] 2명 이상이어야 한다
- [x] 중복될 수 없다
- [x] 특정 참가자의 시작점을 알 수 있다.
  - [x] 특정 참가자를 찾지 못한 경우 예외를 던진다.

### 사다리 게임

- [x] 참가자들의 수, 경품 수가 같아야한다.
- [x] 특정 참가자의 결과를 알 수 있다.

### 사다리

- [x] 높이는 양수만 가능하다.
- [x] 너비는 일정해야 한다
- [x] 특정 위치부터 사다리를 탈 수 있다.

### 다리 생성기

- [x] 브릿지 하나를 생성한다
    - [x] RandomBridgeGenerator는 랜덤으로 브릿지 하나를 생성한다.

### 가로줄 생성기

- [x] 가로줄 하나를 생성한다
- [x] 한 가로줄은 최대 (사람 수 - 1)개의 다리를 가진다
- [x] 다리가 연속되지 않게 생성한다.

### 가로줄
- [x] 연속되는 브릿지를 가진 Line을 생성할 수 없다
- [x] 현재 위치에서 이동할 다음 위치를 알 수 있다.
  - 좌측, 유지, 우측

### 위치
- [x] 현재 위치의 왼쪽, 오른쪽을 알 수 있다.
- [x] 컬렉션 범위에 있는지 알 수 있다.
- [x] 특정 방향으로 이동시킬 수 있다
- [x] 위치는 음수일 수 없다

### 다리

- [x] 주어지는 다리와 연속인지 알 수 있다.
- [x] 다리가 존재하는지 알 수있다.

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