package domain;

import static java.util.List.copyOf;

import java.util.ArrayList;
import java.util.List;

public class Line {

    private final List<Point> points;
    // private final List<Bridge> bridges;

    // public Line(final List<Bridge> bridges) {
    //     validateBridges(bridges);
    //     validatePoints(seperateBridges(bridges));
    //     this.points = seperateBridges(bridges);
    //     this.bridges = join(points);
    // }

    public Line(final List<Point> points) {
        // List<Bridge> bridges = join(copyOf(points));
        // validateBridges(bridges);
        validatePoints(copyOf(points));
        this.points = copyOf(points);
        // this.bridges = bridges;
    }

    // private List<Point> seperateBridges(List<Bridge> bridges) {
    //     List<Point> points = new ArrayList<>();
    //     Point point = new Point();
    //     for (Bridge bridge : bridges) {
    //         point = point.createNextWith(bridge);
    //         points.add(point);
    //     }
    //     points.add(point.last());
    //     return points;
    // }

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
            throw new IllegalArgumentException("잘못된 다리가 있습니다");
        }
    }

    // private void validateBridges(final List<Bridge> bridges) {
    //     for (int i = 0; i < bridges.size() - 1; i++) {
    //         Bridge currentBridge = bridges.get(i);
    //         Bridge nextBridge = bridges.get(i + 1);
    //         validateNotSerial(currentBridge, nextBridge);
    //     }
    // }
    //
    // private void validateNotSerial(final Bridge currentBridge, final Bridge nextBridge) {
    //     if (currentBridge.isSerialWith(nextBridge)) {
    //         throw new IllegalArgumentException("연속된 가로 라인이 존재합니다");
    //     }
    // }

    public Direction findDirectionFrom(final Position position) {
        return getPointAt(position).getDirection();
    }

    private Point getPointAt(Position position) {
        return points.get(position.getPosition());
    }

    public List<Bridge> getBridges() {
        return join(points);
    }

    public boolean hasSameWidthWith(Line line) {
        // return line.bridges.size() == bridges.size();
        // return line.join(line.points).size() == join(points).size();
        return line.points.size() == points.size();
    }
}
