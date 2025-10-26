package racingcar.domain.car;

import racingcar.domain.generator.NumberGenerator;
import racingcar.domain.rule.MoveRule;

import java.util.ArrayList;
import java.util.List;

public class Cars {
    private final List<Car> cars;

    public Cars(List<String> names) {
        this.cars = createCars(names);
    }

    private List<Car> createCars(List<String> names) {
        List<Car> list = new ArrayList<>();
        for (String name : names) {
            list.add(new Car(name.trim())); // Car 내부에서 검증
        }
        return list;
    }

    public void moveAll(MoveRule rule, NumberGenerator generator) {
        for (Car car : cars) {
            car.moveIf(rule, generator);
        }
    }

    public List<String> findWinners() {
        List<String> winners = new ArrayList<>();
        int maxPosition = findMaxPosition();

        for (Car car : cars) {
            if (car.getPosition() == maxPosition) {
                winners.add(car.getName());
            }
        }
        return List.copyOf(winners);
    }

    private int findMaxPosition() {
        int max = 0;
        for (Car car : cars) {
            max = Math.max(max, car.getPosition());
        }
        return max;
    }

    public String getRaceState() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cars.size(); i++) {
            sb.append(cars.get(i).toString());
            if (i < cars.size() - 1) sb.append("\n");
        }
        return sb.toString();
    }

    public List<Car> getCars() {
        return List.copyOf(cars);
    }
}
