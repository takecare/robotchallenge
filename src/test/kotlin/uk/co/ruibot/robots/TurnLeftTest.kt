package uk.co.ruibot.robots

import com.google.common.truth.Truth
import org.junit.Test

class TurnLeftTest {

    @Test
    fun `robot turns left when facing north`() {
        val robot = Robot(Position(0, 0, Direction.NORTH), State.ALIVE) // TODO extract these to Fixtures file
        val world = World(3, 3)

        robot.run(TurnLeft(), world)

        Truth.assertThat(robot.position.direction).isEqualTo(Direction.WEST)
    }

    @Test
    fun `robot turns left when facing east`() {
        val robot = Robot(Position(0, 0, Direction.EAST), State.ALIVE) // TODO extract these to Fixtures file
        val world = World(3, 3)

        robot.run(TurnLeft(), world)

        Truth.assertThat(robot.position.direction).isEqualTo(Direction.NORTH)
    }

    @Test
    fun `robot turns left when facing south`() {
        val robot = Robot(Position(0, 0, Direction.SOUTH), State.ALIVE) // TODO extract these to Fixtures file
        val world = World(3, 3)

        robot.run(TurnLeft(), world)

        Truth.assertThat(robot.position.direction).isEqualTo(Direction.EAST)
    }

    @Test
    fun `robot turns left when facing west`() {
        val robot = Robot(Position(0, 0, Direction.WEST), State.ALIVE) // TODO extract these to Fixtures file
        val world = World(3, 3)

        robot.run(TurnLeft(), world)

        Truth.assertThat(robot.position.direction).isEqualTo(Direction.SOUTH)
    }
}
