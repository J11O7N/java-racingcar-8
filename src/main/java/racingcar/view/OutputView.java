package racingcar.view;

import racingcar.domain.race.RaceRecord;
import java.util.List;

public final class OutputView {

    private OutputView() {} // 유틸성 클래스 — 인스턴스화 방지

    public static void printStart() {
        System.out.println();
        System.out.println("실행 결과");
    }

    public static void printRaceResult(RaceRecord record) {
        for (String round : record.getRounds()) {
            System.out.println(round);
            System.out.println(); // 라운드 간 구분용 빈 줄
        }
    }

    public static void printWinners(List<String> winners) {
        System.out.println("최종 우승자 : " + String.join(", ", winners));
    }

}
