package domain;

import domain.exception.SerialBridgeException;

public class Point {

    private final Direction direction;

    public Point() {
        this(Direction.STAY);
    }

    public Point(Direction direction) {
        this.direction = direction;
    }

    public Bridge joinWith(Point next) {
        if (this.direction.isRight() && next.direction.isLeft()) {
            return Bridge.EXIST;
        }
        return Bridge.EMPTY;
    }

    public Point createNextWith(Bridge nextBridge) throws SerialBridgeException {
        validateNotSerial(nextBridge);
        if (this.direction.isRight()) {
            return new Point(Direction.LEFT);
        }
        if (nextBridge.doesExist()) {
            return new Point(Direction.RIGHT);
        }
        return new Point(Direction.STAY);
    }

    private void validateNotSerial(Bridge nextBridge) throws SerialBridgeException {
        if (this.direction.isRight() && nextBridge.doesExist()) {
            throw new SerialBridgeException();
        }
    }

    public Point last() {
        if (this.direction.isRight()) {
            return new Point(Direction.LEFT);
        }
        return new Point(Direction.STAY);
    }

    public boolean isSymmetricWith(Point next) {
        if (this.direction.isRight()) {
            return next.direction.isLeft();
        }
        return next.direction.isRight() || next.direction.isStay();
    }

    public Position slide(Position position) {
        return position.moveTo(direction);
    }
}
