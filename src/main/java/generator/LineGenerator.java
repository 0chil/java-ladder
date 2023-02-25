package generator;

import java.util.ArrayList;
import java.util.List;

import domain.Bridge;
import domain.Line;
import domain.Point;

public class LineGenerator {

    private final BridgeGenerator bridgeGenerator;

    public LineGenerator(BridgeGenerator bridgeGenerator) {
        this.bridgeGenerator = bridgeGenerator;
    }

    public Line generate(final int personCount) {
        List<Point> points = generatePoints(personCount);
        return new Line(points);
    }

    private List<Point> generatePoints(int personCount) {
        Point point = new Point();
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < personCount - 1; i++) {
            point = generatePointAfter(point);
            points.add(point);
        }
        points.add(point.last());
        return points;
    }

    private Point generatePointAfter(Point point) {
        try {
            return point.createNextWith(bridgeGenerator.generate());
        } catch (IllegalArgumentException overlappedBridgeException) {
            return point.createNextWith(Bridge.EMPTY);
        }
    }
}
