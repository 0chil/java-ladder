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
        // List<Bridge> bridges = join(points);
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
        System.out.println("points = " + points);
        return points;
    }

    // private List<Bridge> join(List<Point> points) {
    //     List<Bridge> bridges = new ArrayList<>();
    //     Point prev = points.get(0);
    //     for (int i = 1; i < points.size(); i++) {
    //         Point cur = points.get(i);
    //         bridges.add(prev.joinWith(cur));
    //         prev = cur;
    //     }
    //     return bridges;
    // }

    private Point generatePointAfter(Point point) {
        try {
            return point.createNextWith(bridgeGenerator.generate());
        } catch (IllegalArgumentException overlappedBridgeException) {
            return point.createNextWith(Bridge.EMPTY);
        }
    }

    // private Bridge getNextBridgeAfter(final Bridge lastBridge) {
    //     if (lastBridge.doesExist()) {
    //         return Bridge.EMPTY;
    //     }
    //     return bridgeGenerator.generate();
    // }
}
