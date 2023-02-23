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

    public Direction findDirectionFrom(final Position position) {
        if (getLeftBridgeFrom(position).doesExist()) {
            return Direction.LEFT;
        }
        if (getRightBridgeFrom(position).doesExist()) {
            return Direction.RIGHT;
        }
        return Direction.STAY;
    }

    private Bridge getLeftBridgeFrom(Position position) {
        return getBridgeAt(position.left());
    }

    private Bridge getRightBridgeFrom(Position position) {
        return getBridgeAt(position);
    }

    private Bridge getBridgeAt(Position position) {
        if (position.isInRangeOf(bridges)) {
            return bridges.get(position.getPosition());
        }
        return Bridge.EMPTY;
    }

    public List<Bridge> getBridges() {
        return bridges;
    }

    public boolean hasSameWidthWith(Line line) {
        return line.bridges.size() == bridges.size();
    }
}
