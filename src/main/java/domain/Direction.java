package domain;

enum Direction {

    LEFT, RIGHT, STAY;

    boolean isLeft() {
        return this.equals(LEFT);
    }

    boolean isRight() {
        return this.equals(RIGHT);
    }

    public boolean isStay() {
        return this.equals(STAY);
    }
}
