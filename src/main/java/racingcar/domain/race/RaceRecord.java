package racingcar.domain.race;

import java.util.List;
import java.util.ArrayList;

public class RaceRecord {
    private List<String> rounds = new ArrayList<>();

    public void record(String state) {
        rounds.add(state);
    }

    public List<String> getRounds() {
        return List.copyOf(rounds);
    }
}
