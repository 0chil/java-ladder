package domain;

class Position {

    private final int position;

    Position(int position) {
        validateNotNegative(position);
        this.position = position;
    }

    private void validateNotNegative(int position) {
        if (position < 0) {
            throw new IllegalArgumentException("위치는 음수일 수 없습니다");
        }
    }

    Position moveTo(Direction direction) {
        if (direction.isLeft()) {
            return new Position(position - 1);
        }
        if (direction.isRight()) {
            return new Position(position + 1);
        }
        return this;
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
