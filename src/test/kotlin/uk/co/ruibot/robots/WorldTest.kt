package uk.co.ruibot.robots

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import uk.co.ruibot.robots.robot.*

class WorldTest {

    @Test
    fun `robot goes back to starting position - RFRFRFRF`() {
        val robot = Robot(Position(1, 1, Direction.EAST), State.ALIVE) // TODO extract these to Fixtures file
        val world = World(5, 3)
        val path = listOf(
            TurnRight(),
            MoveForward(),
            TurnRight(),
            MoveForward(),
            TurnRight(),
            MoveForward(),
            TurnRight(),
            MoveForward()
        )

        val result = world.run(robot, path)

        assertThat(result).isEqualTo(
            Robot(Position(1, 1, Direction.EAST), State.ALIVE)
        )
    }

    @Test
    fun `robot gets lost - FRRFLLFFRRFLL`() {
        val robot = Robot(Position(3, 2, Direction.NORTH), State.ALIVE) // TODO extract these to Fixtures file
        val world = World(5, 3)
        val path = listOf(
            MoveForward(),
            TurnRight(),
            TurnRight(),
            MoveForward(),
            TurnLeft(),
            TurnLeft(),
            MoveForward(),
            MoveForward(),
            TurnRight(),
            TurnRight(),
            MoveForward(),
            TurnLeft(),
            TurnLeft()
        )

        val result = world.run(robot, path)

        assertThat(result).isEqualTo(
            Robot(
                Position(3, 3, Direction.NORTH), State.LOST
            )
        )
    }

    @Test
    fun `robot detects scent - FRRFLLFFRRFLL & LLFFFLFLFL`() {
        val robot1 = Robot(Position(3, 2, Direction.WEST), State.ALIVE) // TODO extract these to Fixtures file
        val robot2 = Robot(Position(0, 3, Direction.WEST), State.ALIVE) // TODO extract these to Fixtures file
        val world = World(5, 3)

        val path1 = listOf(
            MoveForward(),
            TurnRight(),
            TurnRight(),
            MoveForward(),
            TurnLeft(),
            TurnLeft(),
            MoveForward(),
            MoveForward(),
            TurnRight(),
            TurnRight(),
            MoveForward(),
            TurnLeft(),
            TurnLeft()
        )
        val path2 = listOf(
            TurnLeft(),
            TurnLeft(),
            MoveForward(),
            MoveForward(),
            MoveForward(),
            TurnLeft(),
            MoveForward(),
            TurnLeft(),
            MoveForward(),
            TurnLeft()
        )

        world.run(robot1, path1)
        val result = world.run(robot2, path2)

        assertThat(result).isEqualTo(
            Robot(
                Position(3, 3, Direction.NORTH), State.LOST
            )
        )
    }
}
