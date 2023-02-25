package domain;

public class Point {

    private final boolean left;
    private final boolean right;

    public Point() {
        this(false, false);
    }

    public Point(boolean left, boolean right) {
        this.left = left;
        this.right = right;
    }

    public Bridge joinWith(Point next) {
        if (this.right && next.left) {
            return Bridge.EXIST;
        }
        return Bridge.EMPTY;
    }

    public Point createNextWith(Bridge nextBridge) throws IllegalArgumentException {
        if (right && nextBridge.doesExist()) {
            throw new IllegalArgumentException("연속된 다리가 생성되었습니다");
        }
        return new Point(right, nextBridge.doesExist());
        // if (left) {
        //     return new Point(false, nextBridge.doesExist());
        // }
        // if (right) {
        //     return new Point(true, false);
        // }
        // return new Point(false, nextBridge.doesExist());
    }

    public Point last() {
        return new Point(right, false);
    }

    public boolean isSymmetricWith(Point next) {
        if (right) {
            return next.left;
        }
        if (left) {
            return !next.left;
        }
        return !next.left;
    }

    public Direction getDirection() {
        if (left) {
            return Direction.LEFT;
        }
        if (right) {
            return Direction.RIGHT;
        }
        return Direction.STAY;
    }

    @Override
    public String toString() {
        if (right) {
            return "  |--";
        }
        if (left) {
            return "--|  ";
        }
        return "  |  ";
    }
}
