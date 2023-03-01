package domain;

import java.util.ArrayList;
import java.util.List;

import domain.exception.SerialBridgeException;

public class Line {

    private final List<Point> points;

    public Line(final List<Bridge> bridges) {
        validateLengthOf(bridges);
        validateBridges(bridges);

        List<Point> points = generatePointsFrom(bridges);
        validatePoints(points);
        this.points = points;
    }

    private List<Point> generatePointsFrom(final List<Bridge> bridges) {
        List<Point> separatedPoints = separate(bridges);
        List<Point> pointsInBetween = separatedPoints.subList(1, separatedPoints.size() - 1);
        Point first = separatedPoints.get(0);
        Point last = separatedPoints.get(separatedPoints.size() - 1);
        return joinPoints(first, mergePoints(pointsInBetween), last);
    }

    private List<Point> separate(List<Bridge> bridges) {
        var points = new ArrayList<Point>();
        for (Bridge bridge : bridges) {
            points.addAll(bridge.separate());
        }
        return points;
    }

    private List<Point> mergePoints(List<Point> pointsInBetween) {
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < pointsInBetween.size(); i += 2) {
            var left = pointsInBetween.get(i);
            var right = pointsInBetween.get(i + 1);
            points.add(left.merge(right));
        }
        return points;
    }

    private List<Point> joinPoints(Point first, List<Point> inBetween, Point last) {
        List<Point> points = new ArrayList<>();
        points.add(first);
        points.addAll(inBetween);
        points.add(last);
        return points;
    }

    private void validatePoints(final List<Point> points) {
        for (int i = 0; i < points.size() - 1; i++) {
            var current = points.get(i);
            var next = points.get(i + 1);
            validateSymmetric(current, next);
        }
    }

    private void validateSymmetric(Point current, Point next) {
        if (current.isNotSymmetricWith(next)) {
            throw new IllegalStateException("연결점과 연결점 사이는 대칭이어야 합니다");
        }
    }

    private void validateLengthOf(final List<Bridge> bridges) {
        if (bridges.size() < 1) {
            throw new IllegalArgumentException("다리는 1개 이상이어야 합니다");
        }
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
            throw new SerialBridgeException();
        }
    }

    public Position slide(final Position position) {
        return getPointAt(position).slide(position);
    }

    public boolean hasDifferentWidthWith(Line line) {
        return line.points.size() != points.size();
    }

    private Point getPointAt(Position position) {
        return points.get(position.getPosition());
    }

    public List<Bridge> getBridges() {
        return join(points);
    }

    private List<Bridge> join(final List<Point> points) {
        List<Bridge> bridges = new ArrayList<>();
        Point left = points.get(0);
        for (int i = 1; i < points.size(); i++) {
            Point current = points.get(i);
            bridges.add(left.joinWith(current));
            left = current;
        }
        return bridges;
    }
}
