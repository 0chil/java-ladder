package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PositionTest {

    @Test
    void 현재_위치의_왼쪽을_알_수_있다() {
        Position position = new Position(1);

        assertThat(position.left().getPosition()).isEqualTo(0);
    }

    @Test
    void 현재_위치의_오른쪽을_알_수_있다() {
        Position position = new Position(1);

        assertThat(position.right().getPosition()).isEqualTo(2);
    }

    @Test
    @DisplayName("위치는 음수일 수 없다")
    void 위치는_음수일_수_없다() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Position(-1));
    }

    @ParameterizedTest
    @CsvSource({"LEFT,0", "RIGHT,2", "STAY,1"})
    void 어떤_방향으로_움직인다(Direction direction, int expectedPosition) {
        Position position = new Position(1);

        assertThat(position.moveTo(direction)).isEqualTo(new Position(expectedPosition));
    }
}
