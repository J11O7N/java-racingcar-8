package racingcar.domain.rule;

public class ThresholdMoveRule implements MoveRule {
    @Override
    public boolean isMovable(int position) {
        return position >= 4;
    }
}
