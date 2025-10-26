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

---

## 파일 구조

```text
racingcar/
├── Application.java                # 프로그램 시작점 (main)
│
├── controller/                     # 흐름 제어 계층
│   └── RaceController.java         # 입력 → 경기 진행 → 출력 담당
│
├── service/                        
│   └── RaceService.java            # 시도 횟수만큼 Cars.moveAll() 반복 + 결과 수집
│
├── domain/                         # 핵심 규칙과 모델
│   ├── car/
│   │   ├── Car.java                # 자동차 (name, position, moveIf)
│   │   └── Cars.java               # 자동차 집합 (moveAll, findWinners)
│   │
│   ├── rule/
│   │   └── MoveRule.java         # 이동 규칙 인터페이스 (e.g. 4 이상 전진)
│   │   └── ThresholdMoveRule.java  # 기본 구현체 (number >= 4)
│   │
│   ├── generator/
│   │   ├── NumberGenerator.java    # 난수 생성 인터페이스
│   │   └── RandomNumberGenerator.java # 0~9 랜덤 반환
│   │
│   └── race/
│       └── RaceRecord.java       # 라운드별 자동차 상태 저장
│
├── view/                           # 입출력 계층
│   ├── InputView.java              # 이름/시도 횟수 입력
│   └── OutputView.java             # 경기 결과/우승자 출력
│
└── error/                          # 공통 예외
    ├── InvalidCarNameException.java  # 도메인 예외
    └── InvalidInputException.java    # 입력 예외

```

---

## ⚙️ 구현 이슈 및 설계

🧩 설계

```text
1️⃣ 인터페이스 중심으로 구조 설계

기존에는 클래스를 바로 구현하고 나중에 커밋했지만,
이번 미션에서는 인터페이스를 먼저 정의하고 이후에 구현체를 작성하는 방식으로 바꿨다.

덕분에 각 클래스의 역할이 명확해지고, “무엇을 해야 하는가(선언)”와 “어떻게 할 것인가(구현)”를 분리할 수 있었다.
예:
MoveRule ↔ ThresholdMoveRule
NumberGenerator ↔ RandomNumberGenerator

2️⃣ 파일 구조 세분화 및 책임 분리

프로젝트를 단순히 controller, domain, view로 나누는 데 그치지 않고,
세부 책임 단위로 패키지를 나눠 명확한 역할 경계를 설정했다.

error → 예외 클래스 (입력 검증, 도메인 규칙 위반 등)
service → 비즈니스 로직 (경기 반복, 우승자 관리 등)
utils/strings → 문자열 유틸 (초기에 설계했지만 오버엔지니어링 판단 후 제외)

이렇게 나누면서, 각 계층이 하나의 명확한 역할만 수행하도록 리팩토링했다.

3️⃣ 의존성 주입(DI)

1주차 문자열 계산기 미션에서는 Controller 내부에서 직접 객체를 생성하고 조립했다.

이 방식은 간단하지만, 클래스 간 결합도가 높아지고 재사용성이 떨어지는 문제가 있었다.

이번 자동차 경주 미션에서는 의존성 주입(DI) 을 적용하여 Controller가 직접 객체를 만들지 않고,
Application이 조립자 역할을 하도록 설계했다.
이를 통해 각 클래스는 오직 자신의 역할에만 집중할 수 있었고, 객체 생성 책임이 한 곳(Application)으로 모여 코드의 흐름이 명확해졌다.
또한, 특정 구현체를 바꾸거나 로직을 수정하더라도 다른 계층에 영향을 주지 않게 되어 유지보수성과 확장성이 크게 향상되었다.

---

🚧 구현 이슈

1️⃣ 객체 생성 및 주입 위치에 대한 고민

의존성 주입 원칙을 지키다 보니 “어디서 객체를 생성하고 전달해야 할지”에 대한 고민이 많았다.
특히 Cars, RaceService, RaceController, Application 간의 관계를 정의하면서
의존성 순환 없이 객체를 전달하는 구조를 설계하는 데 시간이 걸렸다.

이 과정에서
Controller 내부에서 Service를 직접 생성하지 않도록 수정했고,
Application에서 모든 객체를 조립 후 Controller에 주입하도록 개선했다.

👉 이 부분에서 객체 생성을 넘기는 과정에서 여러 에러가 발생했지만,
결국 “Controller는 실행만 담당하고, 생성은 Application에서 한다”는 원칙을 지키며 해결했다.

2️⃣ Utils/Strings 오버엔지니어링 이슈

초기 설계에서는 utils/Strings를 만들어 입력값을 splitAndTrim() 등의 메서드로 전처리하려 했으나,
입력이 한 번만 발생하는 구조임을 고려해 과설계(Over-engineering) 로 판단하고 제외했다.

입력 검증은 Car 도메인에서 책임지도록 단일화했고,
InputView에서는 단순히 입력을 전달하는 역할만 하도록 유지했다.

```