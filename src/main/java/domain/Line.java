package domain;

import static java.util.List.copyOf;

import java.util.ArrayList;
import java.util.List;

public class Line {

    private final List<Point> points;

    public Line(final List<Point> points) {
        validatePoints(points);
        this.points = copyOf(points);
    }

    private List<Bridge> join(List<Point> points) {
        List<Bridge> bridges = new ArrayList<>();
        Point prev = points.get(0);
        for (int i = 1; i < points.size(); i++) {
            Point cur = points.get(i);
            bridges.add(prev.joinWith(cur));
            prev = cur;
        }
        return bridges;
    }

    private void validatePoints(final List<Point> points) {
        for (int i = 0; i < points.size() - 1; i++) {
            var current = points.get(i);
            var next = points.get(i + 1);
            validateSymmetric(current, next);
        }
    }

    private void validateSymmetric(Point current, Point next) {
        if (!current.isSymmetricWith(next)) {
            throw new IllegalArgumentException("연결점과 연결점 사이는 대칭이어야 합니다");
        }
    }

    public Position slide(final Position position) {
        return getPointAt(position).slide(position);
    }

    private Point getPointAt(Position position) {
        return points.get(position.getPosition());
    }

    public List<Bridge> getBridges() {
        return join(points);
    }
}
