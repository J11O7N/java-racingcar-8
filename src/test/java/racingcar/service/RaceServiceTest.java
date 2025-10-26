package racingcar.service;

import org.junit.jupiter.api.Test;
import racingcar.domain.generator.NumberGenerator;
import racingcar.domain.race.RaceRecord;
import racingcar.domain.rule.MoveRule;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class RaceServiceTest {

    @Test
    void play가_시도횟수만큼_경기를_진행한다() {
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

        RaceService service = new RaceService(generator, rule);
        service.registerCars(List.of("pobi", "woni"));

        RaceRecord record = service.play(3);

        assertThat(record.getRounds()).hasSize(3);
    }

    @Test
    void 우승자를_반환한다() {
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

        RaceService service = new RaceService(generator, rule);
        service.registerCars(List.of("pobi", "woni"));
        service.play(1);

        assertThat(service.getFindWinners()).containsExactlyInAnyOrder("pobi", "woni");
    }
}
