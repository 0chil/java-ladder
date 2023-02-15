package domain;

import java.util.List;

public class Ladder {

    private static final int MIN_HEIGHT = 0;

    private final List<Person> people;
    private final List<Line> lines;

    public Ladder(List<Person> people, List<Line> lines) {
        validateNotNegative(lines.size());
        this.lines = lines;
        this.people = people;
    }

    private void validateNotNegative(int height) {
        if (height <= MIN_HEIGHT) {
            throw new IllegalArgumentException("높이는 양수만 가능합니다");
        }
    }
}
