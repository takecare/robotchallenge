package uk.co.ruibot.robots.robot

import com.google.common.truth.Truth
import org.junit.Test
import uk.co.ruibot.robots.World

// TODO parameterised test? @RUI
class TurnRightTest {

    @Test
    fun `robot turns right when facing north`() {
        val robot = Robot(
            Position(
                0,
                0,
                Direction.NORTH
            ), State.ALIVE
        ) // TODO extract these to Fixtures file
        val world = World(3, 3)

        robot.execute(TurnRight(), world)

        Truth.assertThat(robot.position.direction).isEqualTo(Direction.EAST)
    }

    @Test
    fun `robot turns right when facing east`() {
        val robot = Robot(
            Position(
                0,
                0,
                Direction.EAST
            ), State.ALIVE
        ) // TODO extract these to Fixtures file
        val world = World(3, 3)

        robot.execute(TurnRight(), world)

        Truth.assertThat(robot.position.direction).isEqualTo(Direction.SOUTH)
    }

    @Test
    fun `robot turns right when facing south`() {
        val robot = Robot(
            Position(
                0,
                0,
                Direction.SOUTH
            ), State.ALIVE
        ) // TODO extract these to Fixtures file
        val world = World(3, 3)

        robot.execute(TurnRight(), world)

        Truth.assertThat(robot.position.direction).isEqualTo(Direction.WEST)
    }

    @Test
    fun `robot turns right when facing west`() {
        val robot = Robot(
            Position(
                0,
                0,
                Direction.WEST
            ), State.ALIVE
        ) // TODO extract these to Fixtures file
        val world = World(3, 3)

        robot.execute(TurnRight(), world)

        Truth.assertThat(robot.position.direction).isEqualTo(Direction.NORTH)
    }
}
