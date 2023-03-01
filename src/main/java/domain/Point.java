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

    public Point join(Point next) {
        if (!this.direction.isStay() && !next.direction.isStay()) {
            throw new IllegalStateException("양방향 연결점은 생성될 수 없습니다");
        }
        if (this.direction.isRight() || next.direction.isRight()) {
            return new Point(Direction.RIGHT);
        }
        if (this.direction.isLeft() || next.direction.isLeft()) {
            return new Point(Direction.LEFT);
        }
        return new Point(Direction.STAY);
    }

    public Point createNextWith(Bridge bridgeAfterNewPoint) throws SerialBridgeException {
        validateNotSerial(bridgeAfterNewPoint);
        if (this.direction.isRight()) {
            return new Point(Direction.LEFT);
        }
        if (bridgeAfterNewPoint.doesExist()) {
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

    public boolean isNotSymmetricWith(Point next) {
        return !this.isSymmetricWith(next);
    }

    private boolean isSymmetricWith(Point next) {
        if (this.direction.isRight()) {
            return next.direction.isLeft();
        }
        return next.direction.isRight() || next.direction.isStay();
    }

    public Position slide(Position position) {
        return position.moveTo(direction);
    }
}
