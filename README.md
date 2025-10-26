# java-racingcar-precourse
# 2주차 미션 - 자동차 경주

---

## 기능 목록

### 입력값 검증
- 사용자에게 자동차 이름들을 쉼표(,) 기준으로 입력받는다.  
  예) `pobi,woni,jun`
- 이름은 1자 이상, 5자 이하만 허용한다.
    - [예외] 이름이 비었거나 5자를 초과하면 `InvalidCarNameException` 발생.
- 시도 횟수를 입력받는다.
    - [예외] 숫자가 아니거나 1 미만일 경우 `InvalidInputException` 발생.

---

### 자동차 이동 규칙
- 각 자동차는 0부터 9 사이의 랜덤값을 생성한다.
- 랜덤값이 4 이상일 경우 전진한다.
- 이동 조건은 `MoveRule` 인터페이스로 정의하고,  
  `ThresholdMoveRule` 클래스에서 기본 구현(`number >= 4`)을 제공한다.

---

### 자동차(Car) 기능
- 자동차는 이름(`name`)과 현재 위치(`position`)를 가진다.
- `moveIf(rule, generator)` 메서드를 통해 조건에 따라 전진한다.
- 출력 시 `"pobi : ---"` 형식으로 반환한다.

---

### 자동차 집합(Cars) 기능
- 여러 대의 자동차를 관리한다.
- `moveAll(rule, generator)` : 모든 자동차를 한 번씩 이동시킨다.
- `findWinners()` : 가장 멀리 간 자동차(들)를 찾아 반환한다.  
  (공동 우승자 허용)
- `getRaceState()` : 현재 라운드의 자동차 상태를 문자열로 반환한다.

---

### 난수 생성
- `NumberGenerator` 인터페이스에서 `generate()` 메서드를 정의한다.
- `RandomNumberGenerator` 클래스는 0~9 범위의 난수를 반환한다.
- 테스트 시 고정값을 반환하도록 한다.

---

### 경기 진행
- 사용자가 입력한 시도 횟수만큼 다음 과정을 반복한다:
    1. 모든 자동차 이동 (`Cars.moveAll()`)
    2. 현재 상태를 기록 (`RaceRecord`)
- 모든 시도 종료 후 우승자 목록을 반환한다.
- 각 라운드별 결과를 `RaceRecord`에 저장해 `OutputView`로 전달한다.

---

### 출력 결과
- 각 라운드별 자동차 상태를 출력한다.