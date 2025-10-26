package racingcar;

import racingcar.controller.RaceController;
import racingcar.domain.generator.NumberGenerator;
import racingcar.domain.generator.RandomNumberGenerator;
import racingcar.domain.rule.MoveRule;
import racingcar.domain.rule.ThresholdMoveRule;
import racingcar.service.RaceService;

public class Application {
    public static void main(String[] args) {
        MoveRule rule = new ThresholdMoveRule();
        NumberGenerator generator = new RandomNumberGenerator();

        RaceService service = new RaceService(generator, rule);
        RaceController controller = new RaceController(service);
        controller.run();
    }
}
