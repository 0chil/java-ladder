package helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import domain.Bridge;
import domain.Ladder;
import domain.Line;
import domain.Participant;
import domain.Participants;
import domain.Point;
import domain.Prizes;

public abstract class AbstractTestFixture {

    public List<Point> convert(Boolean... flags) {
        List<Bridge> bridges = Arrays.stream(flags)
                .map((flag) -> {
                    if (flag) {
                        return Bridge.EXIST;
                    }
                    return Bridge.EMPTY;
                })
                .collect(Collectors.toList());
        return separateBridges(bridges);
    }

    private List<Point> separateBridges(List<Bridge> bridges) {
        List<Point> points = new ArrayList<>();
        Point point = new Point();
        for (Bridge bridge : bridges) {
            point = point.createNextWith(bridge);
            points.add(point);
        }
        points.add(point.last());
        return points;
    }

    public Participants createDefaultParticipants() {
        return new Participants(createParticipantsFrom("aa", "bb"));
    }

    public List<Participant> createParticipantsFrom(String... names) {
        return Arrays.stream(names)
                .map(Participant::new)
                .collect(Collectors.toList());
    }

    public Prizes createPrizesFrom(String... names) {
        return new Prizes(List.of(names));
    }

    public List<Line> createLines(final int height) {
        List<Line> lines = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            lines.add(new Line(convert(true, false, true)));
        }
        return lines;
    }

    public Ladder createLadderWith(Line... lines) {
        return new Ladder(List.of(lines));
    }
}
