package racingcar.service;

import racingcar.domain.car.Cars;
import racingcar.domain.generator.NumberGenerator;
import racingcar.domain.race.RaceRecord;
import racingcar.domain.rule.MoveRule;

import java.util.List;

public class RaceService {
    private final NumberGenerator generator;
    private final MoveRule rule;
    private Cars cars;

    public RaceService(NumberGenerator generator, MoveRule rule) {
        this.generator = generator;
        this.rule = rule;
    }

    public void registerCars(List<String> names) {
        this.cars = new Cars(names);
    }

    public RaceRecord play(int attempts) {
        if (cars == null) throw new IllegalStateException("registerCars(names)를 먼저 호출하세요.");
        RaceRecord record = new RaceRecord();
        for (int i = 0; i < attempts; i++) {
            cars.moveAll(rule, generator);
            record.record(cars.getRaceState());
        }
        return record;
    }

    public List<String> getFindWinners() {
        if (cars == null) throw new IllegalStateException("게임이 준비되지 않았습니다.");
        return cars.findWinners();
    }
}
