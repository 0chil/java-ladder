package domain;

import java.util.List;

public enum Bridge {

    EXIST {
        @Override
        public List<Point> toPoints() {
            return List.of(new Point(Direction.RIGHT), new Point(Direction.LEFT));
        }
    }, EMPTY {
        @Override
        public List<Point> toPoints() {
            return List.of(new Point(Direction.STAY), new Point(Direction.STAY));
        }
    };

    public boolean isSerialWith(Bridge next) {
        return this.doesExist() && next.doesExist();
    }

    public boolean doesExist() {
        return this == EXIST;
    }

    public abstract List<Point> toPoints();
}
