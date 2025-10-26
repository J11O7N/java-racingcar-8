package racingcar.domain.car;

import racingcar.domain.generator.NumberGenerator;
import racingcar.domain.rule.MoveRule;
import racingcar.error.InvalidCarNameException;

public class Car {
    private final String name;
    private int position = 0;

    public Car(String name) {
        validateName(name);
        this.name = name;
    }

    public void moveIf(MoveRule rule, NumberGenerator generator) {
        int number = generator.generate();
        if (rule.isMovable(number)) {
            position++;
        }
    }

    private void validateName(String name) {
        if (name == null) {
            throw new InvalidCarNameException("자동차 이름이 null일 수 없습니다.");
        }
        if (name.isBlank()) {
            throw new InvalidCarNameException("자동차 이름은 공백일 수 없습니다.");
        }
        if (name.length() > 5) {
            throw new InvalidCarNameException("자동차 이름은 5자 이하여야 합니다. (입력값: " + name + ")");
        }
    }


    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public String toString() {
        // 기존: return name + ":" + "-".repeat(position);
        return name + " : " + "-".repeat(position); // 콜론 양옆에 공백 필수
    }

}
