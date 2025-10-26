package racingcar.domain.car;

import org.junit.jupiter.api.Test;
import racingcar.domain.generator.NumberGenerator;
import racingcar.domain.rule.MoveRule;
import racingcar.error.InvalidCarNameException;

import static org.assertj.core.api.Assertions.*;

public class CarTest {

    @Test
    void 이동조건을_만족하면_위치가_1_증가한다() {
        Car car = new Car("pobi");

        MoveRule rule = new MoveRule() {
            @Override
            public boolean isMovable(int number) {
                return number >= 4;
            }
        };

        NumberGenerator generator = new NumberGenerator() {
            @Override
            public int generate() {
                return 5;
            }
        };

        car.moveIf(rule, generator);
        assertThat(car.getPosition()).isEqualTo(1);
    }

    @Test
    void 이름이_5자_초과면_예외발생() {
        assertThatThrownBy(() -> new Car("pobicar"))
                .isInstanceOf(InvalidCarNameException.class);
    }
}
