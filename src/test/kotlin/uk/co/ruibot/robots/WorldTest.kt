package uk.co.ruibot.robots

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import uk.co.ruibot.robots.robot.*

class WorldTest {

    @Test
    fun `robot goes back to starting position - RFRFRFRF`() {
        val path = listOf(
            TurnRight,
            MoveForward,
            TurnRight,
            MoveForward,
            TurnRight,
            MoveForward,
            TurnRight,
            MoveForward
        )

        val result = aWorld(5, 3).run(aRobot().at(1, 1).facingEast(), path)

        assertThat(result).isEqualTo(
            Robot(Position(1, 1, Direction.EAST), State.ALIVE)
        )
    }

    @Test
    fun `robot gets lost - FRRFLLFFRRFLL`() {
        val path = listOf(
            MoveForward,
            TurnRight,
            TurnRight,
            MoveForward,
            TurnLeft,
            TurnLeft,
            MoveForward,
            MoveForward,
            TurnRight,
            TurnRight,
            MoveForward,
            TurnLeft,
            TurnLeft
        )

        val result = aWorld(5, 3).run(aRobot().at(3, 2), path)

        assertThat(result).isEqualTo(
            Robot(
                Position(3, 3, Direction.NORTH), State.LOST
            )
        )
    }

    @Test
    fun `robot detects scent - FRRFLLFFRRFLL & LLFFFLFLFL`() {
        val world = aWorld(5, 3)

        val path1 = listOf(
            MoveForward,
            TurnRight,
            TurnRight,
            MoveForward,
            TurnLeft,
            TurnLeft,
            MoveForward,
            MoveForward,
            TurnRight,
            TurnRight,
            MoveForward,
            TurnLeft,
            TurnLeft
        )
        val path2 = listOf(
            TurnLeft,
            TurnLeft,
            MoveForward,
            MoveForward,
            MoveForward,
            TurnLeft,
            MoveForward,
            TurnLeft,
            MoveForward,
            TurnLeft
        )

        world.run(aRobot().at(3, 2).facingWest(), path1)
        val result = world.run(aRobot().at(0, 3).facingWest(), path2)

        assertThat(result).isEqualTo(
            Robot(
                Position(3, 3, Direction.NORTH), State.LOST
            )
        )
    }
}
