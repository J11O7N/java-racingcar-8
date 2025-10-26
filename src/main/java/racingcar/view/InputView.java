package racingcar.view;

import camp.nextstep.edu.missionutils.Console;
import racingcar.error.InvalidInputException;
import java.util.List;
import java.util.ArrayList;

public class InputView {
    public static List<String> readCarNames() {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String line = Console.readLine();
        List<String> names = new ArrayList<>();
        String[] tokens = line.split(",");
        for (int i = 0; i < tokens.length; i++) {
            names.add(tokens[i].trim());
        }
        return names;
    }

    // InputView.java
    public static int readAttemptCount() {
        System.out.println("시도할 횟수는 몇 회인가요?");
        String input = Console.readLine();

        if (input == null || input.isBlank()) {
            throw new InvalidInputException("시도 횟수를 입력해주세요.");
        }

        int count;
        try {
            count = Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new InvalidInputException("시도 횟수는 정수여야 합니다.");
        }

        if (count < 1) {
            throw new InvalidInputException("시도 횟수는 1 이상이어야 합니다.");
        }

        return count;
    }
}

