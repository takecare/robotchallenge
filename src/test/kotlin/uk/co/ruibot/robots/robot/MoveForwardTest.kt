package uk.co.ruibot.robots.robot

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import uk.co.ruibot.robots.World

class MoveForwardTest {

    @Test
    fun `robot moves forward maintaining north direction`() {
        val robot = Robot(
            Position(1, 1, Direction.NORTH), State.ALIVE
        )
        val world = World(3, 3)

        val result = robot.execute(MoveForward(), world)
        val position = result.contentOrNull as Position

        assertThat(position).isEqualTo(
            Position(1, 2, Direction.NORTH)
        )
    }

    @Test
    fun `robot moves forward maintaining east direction`() {
        val robot = Robot(
            Position(1, 1, Direction.EAST), State.ALIVE
        )
        val world = World(3, 3)

        val result = robot.execute(MoveForward(), world)
        val position = result.contentOrNull as Position

        assertThat(position).isEqualTo(
            Position(2, 1, Direction.EAST)
        )
    }

    @Test
    fun `robot moves forward maintaining south direction`() {
        val robot = Robot(
            Position(1, 1, Direction.SOUTH), State.ALIVE
        )
        val world = World(3, 3)

        val result = robot.execute(MoveForward(), world)
        val position = result.contentOrNull as Position

        assertThat(position).isEqualTo(
            Position(1, 0, Direction.SOUTH)
        )
    }

    @Test
    fun `robot moves forward maintaining west direction`() {
        val robot = Robot(
            Position(1, 1, Direction.WEST), State.ALIVE
        )
        val world = World(3, 3)

        val result = robot.execute(MoveForward(), world)
        val position = result.contentOrNull as Position

        assertThat(position).isEqualTo(
            Position(0, 1, Direction.WEST)
        )
    }

    @Test
    fun `robot gets lost in last valid position`() {
        val robot = Robot(
            Position(1, 1, Direction.NORTH), State.ALIVE
        )
        val world = World(1, 1)

        val result = robot.execute(MoveForward(), world)

        assertThat(result).isEqualTo(
            Error(content = Position(1, 1, Direction.NORTH))
        )
    }

    @Test
    fun `robot ignores command when lost`() {
        val robot = Robot(
            Position(0, 0, Direction.NORTH), State.LOST
        )
        val world = World(1, 1)

        val result = robot.execute(MoveForward(), world)
        val position = result.contentOrNull as Position

        assertThat(position).isEqualTo(robot.position)
    }
}
