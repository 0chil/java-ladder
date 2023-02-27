package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PositionTest {

    @Test
    void 현재_위치의_왼쪽으로_이동한다() {
        Position position = new Position(1);

        Position movedPosition = position.moveTo(Direction.LEFT);
        assertThat(movedPosition.getPosition()).isEqualTo(0);
    }

    @Test
    void 현재_위치의_오른쪽으로_이동한다() {
        Position position = new Position(1);

        Position movedPosition = position.moveTo(Direction.RIGHT);
        assertThat(movedPosition.getPosition()).isEqualTo(2);
    }

    @Test
    void 현재_위치를_유지한다() {
        Position position = new Position(1);

        Position movedPosition = position.moveTo(Direction.STAY);
        assertThat(movedPosition.getPosition()).isEqualTo(1);
    }

    @Test
    @DisplayName("위치는 음수일 수 없다")
    void 위치는_음수일_수_없다() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Position(-1));
    }

    @ParameterizedTest(name = "특정 방향으로 이동시킬 수 있다")
    @CsvSource({"LEFT,0", "RIGHT,2", "STAY,1"})
    void moveTo(Direction direction, int expectedPosition) {
        Position position = new Position(1);

        assertThat(position.moveTo(direction)).isEqualTo(new Position(expectedPosition));
    }
}
