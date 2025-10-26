package racingcar.domain.car;

import org.junit.jupiter.api.Test;
import racingcar.domain.generator.NumberGenerator;
import racingcar.domain.rule.MoveRule;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class CarsTest {

    @Test
    void 모든_자동차가_이동조건을_만족하면_모두_전진한다() {
        Cars cars = new Cars(List.of("pobi", "woni", "jun"));

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

        cars.moveAll(rule, generator);

        for (Car car : cars.getCars()) {
            assertThat(car.getPosition()).isEqualTo(1);
        }
    }

    @Test
    void 최대_위치의_자동차가_우승자이다() {
        Cars cars = new Cars(List.of("pobi", "woni"));

        MoveRule alwaysMove = new MoveRule() {
            @Override
            public boolean isMovable(int number) {
                return true;
            }
        };
        NumberGenerator fixed = new NumberGenerator() {
            @Override
            public int generate() {
                return 5;
            }
        };

        // pobi: 2번 이동, woni: 1번 이동
        cars.getCars().get(0).moveIf(alwaysMove, fixed);
        cars.getCars().get(0).moveIf(alwaysMove, fixed);
        cars.getCars().get(1).moveIf(alwaysMove, fixed);

        List<String> winners = cars.findWinners();
        assertThat(winners).containsExactly("pobi");
    }

    @Test
    void 공동_우승자가_존재할_수_있다() {
        Cars cars = new Cars(List.of("pobi", "woni"));

        MoveRule alwaysMove = new MoveRule() {
            @Override
            public boolean isMovable(int number) {
                return true;
            }
        };
        NumberGenerator fixed = new NumberGenerator() {
            @Override
            public int generate() {
                return 5;
            }
        };

        cars.getCars().get(0).moveIf(alwaysMove, fixed);
        cars.getCars().get(1).moveIf(alwaysMove, fixed);

        List<String> winners = cars.findWinners();
        assertThat(winners).containsExactlyInAnyOrder("pobi", "woni");
    }
}
