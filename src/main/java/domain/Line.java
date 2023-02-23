package domain;

import static java.util.List.copyOf;

import java.util.List;

public class Line {

    private final List<Bridge> bridges;

    public Line(final List<Bridge> bridges) {
        validateBridges(bridges);
        this.bridges = copyOf(bridges);
    }

    private void validateBridges(final List<Bridge> bridges) {
        for (int i = 0; i < bridges.size() - 1; i++) {
            Bridge currentBridge = bridges.get(i);
            Bridge nextBridge = bridges.get(i + 1);
            validateNotSerial(currentBridge, nextBridge);
        }
    }

    private void validateNotSerial(final Bridge currentBridge, final Bridge nextBridge) {
        if (currentBridge.isSerialWith(nextBridge)) {
            throw new IllegalArgumentException("연속된 가로 라인이 존재합니다");
        }
    }

    public Direction getNextDirectionFrom(final Position position) {
        if (position.getLeftIn(bridges).doesExist()) {
            return Direction.LEFT;
        }
        if (position.getRightIn(bridges).doesExist()) {
            return Direction.RIGHT;
        }
        return Direction.STAY;
    }

    public List<Bridge> getBridges() {
        return bridges;
    }
}
