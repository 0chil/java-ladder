package domain;

import static java.util.List.copyOf;

import java.util.List;

public class Ladder {

    private static final int MIN_HEIGHT = 0;
    private static final int FIRST = 0;

    private final List<Line> lines;

    public Ladder(List<Line> lines) {
        validateHeightOf(lines);
        validateEvenWidth(lines);
        this.lines = copyOf(lines);
    }

    private void validateHeightOf(final List<Line> lines) {
        if (lines.size() <= MIN_HEIGHT) {
            throw new IllegalArgumentException("높이는 양수만 가능합니다");
        }
    }

    private void validateEvenWidth(final List<Line> lines) {
        Line firstLine = lines.get(FIRST);
        if (!isWidthEven(firstLine, lines)) {
            throw new IllegalArgumentException("사다리 너비는 균일해야 합니다");
        }
    }

    private boolean isWidthEven(Line firstLine, List<Line> lines) {
        return lines.stream()
                .allMatch(line -> line.hasSameWidthWith(firstLine));
    }

    public Position findDestinationFrom(final Position start) {
        Position position = start;
        for (Line line : lines) {
            position = line.slide(position);
        }
        return position;
    }

    public List<Line> getLines() {
        return lines;
    }
}
