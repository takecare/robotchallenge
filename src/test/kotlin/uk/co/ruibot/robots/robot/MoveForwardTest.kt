package uk.co.ruibot.robots.robot

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class MoveForwardTest {

    @Test
    fun `robot moves forward maintaining north direction`() {

        val result = MoveForward.execute(aRobot(), aWorld())
        val position = result.contentOrNull as Position

        assertThat(position).isEqualTo(
            Position(1, 2, Direction.NORTH)
        )
    }

    @Test
    fun `robot moves forward maintaining east direction`() {

        val result = MoveForward.execute(aRobot().facingEast(), aWorld())
        val position = result.contentOrNull as Position

        assertThat(position).isEqualTo(
            Position(2, 1, Direction.EAST)
        )
    }

    @Test
    fun `robot moves forward maintaining south direction`() {

        val result = MoveForward.execute(aRobot().facingSouth(), aWorld())
        val position = result.contentOrNull as Position

        assertThat(position).isEqualTo(
            Position(1, 0, Direction.SOUTH)
        )
    }

    @Test
    fun `robot moves forward maintaining west direction`() {

        val result = MoveForward.execute(aRobot().facingWest(), aWorld())
        val position = result.contentOrNull as Position

        assertThat(position).isEqualTo(
            Position(0, 1, Direction.WEST)
        )
    }

    @Test
    fun `robot gets lost in last valid position`() {

        val result = MoveForward.execute(aRobot().at(3, 3), aWorld())

        assertThat(result).isEqualTo(
            Error(content = Position(3, 3, Direction.NORTH))
        )
    }

    @Test
    fun `robot ignores command when lost`() {

        val result = MoveForward.execute(aLostRobot(), aWorld())
        val position = result.contentOrNull as Position

        assertThat(position).isEqualTo(aLostRobot().position)
    }
}
