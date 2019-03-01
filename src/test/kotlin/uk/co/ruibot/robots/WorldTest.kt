package uk.co.ruibot.robots

import com.google.common.truth.Truth.assertThat
import org.junit.Test

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

        world.run(robot, path)

        assertThat(robot.position).isEqualTo(Position(1, 1, Direction.EAST))
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

        world.run(robot, path)

        assertThat(robot).isEqualTo(Robot(Position(3, 3, Direction.NORTH), State.LOST))
    }

    @Test
    fun `robot detects scent - LLFFFLFLFL`() {
        val robot = Robot(Position(0, 3, Direction.WEST), State.ALIVE) // TODO extract these to Fixtures file
        val world = World(5, 3)
        val path = listOf(
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

        world.run(robot, path)

        assertThat(robot).isEqualTo(Robot(Position(3, 3, Direction.EAST), State.LOST))
    }
}
