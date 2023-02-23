package domain;

import java.util.List;

class Position {

    private final int position;

    Position(int position) {
        this.position = position;
    }

    Position moveTo(Direction direction) {
        if (direction.isLeft()) {
            return left();
        }
        if (direction.isRight()) {
            return right();
        }
        return new Position(position);
    }

    Position left() {
        return new Position(position - 1);
    }

    Position right() {
        return new Position(position + 1);
    }

    public Position follow(Line line) {
        Direction direction = line.getNextDirectionFrom(this);
        return moveTo(direction);
    }

    Bridge getLeftIn(List<Bridge> bridges) {
        return this.left().at(bridges);
    }

    Bridge getRightIn(List<Bridge> bridges) {
        return this.at(bridges);
    }

    private Bridge at(List<Bridge> bridges) {
        if (this.isIn(bridges)) {
            return bridges.get(this.position);
        }
        return Bridge.EMPTY;
    }

    private boolean isIn(List<Bridge> bridges) {
        return this.isInBetween(0, bridges.size());
    }

    boolean isInBetween(int fromInclusive, int toExclusive) {
        return fromInclusive <= position && position < toExclusive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Position position1 = (Position)o;

        return position == position1.position;
    }

    @Override
    public int hashCode() {
        return position;
    }

    int getPosition() {
        return position;
    }
}
