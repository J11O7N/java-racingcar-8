package racingcar.controller;

import racingcar.domain.race.RaceRecord;
import racingcar.service.RaceService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.List;

public class RaceController {
    private final RaceService raceService;

    public RaceController(RaceService raceService) {
        this.raceService = raceService;
    }

    public void run() {
        List<String> names = InputView.readCarNames();
        int attempts = InputView.readAttemptCount();

        raceService.registerCars(names);                        // Cars 준비
        RaceRecord record = raceService.play(attempts);    // 경기 진행

        OutputView.printStart();
        OutputView.printRaceResult(record);
        OutputView.printWinners(raceService.getFindWinners()); // 공동 우승 처리
    }
}
