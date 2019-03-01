package uk.co.ruibot.robots.robot

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import uk.co.ruibot.robots.World

class MoveForwardTest {

    @Test
    fun `robot moves forward maintaining direction`() {
        val robot = Robot(
            Position(
                0,
                0,
                Direction.NORTH
            ), State.ALIVE
        )
        val world = World(3, 3)

        robot.run(MoveForward(), world)

        assertThat(robot.position).isEqualTo(
            Position(
                0,
                1,
                Direction.NORTH
            )
        )
    }

    @Test
    fun `robot moves forward several times`() {
        val robot = Robot(
            Position(
                0,
                0,
                Direction.NORTH
            ), State.ALIVE
        )
        val world = World(5, 5)

        listOf(
            MoveForward(),
            MoveForward(),
            MoveForward()
        )
            .forEach { robot.run(it, world) }

        assertThat(robot.position).isEqualTo(
            Position(
                0,
                3,
                Direction.NORTH
            )
        )
    }

    @Test
    fun `robot gets lost in last valid position`() {
        val robot = Robot(
            Position(
                0,
                0,
                Direction.NORTH
            ), State.ALIVE
        )
        val world = World(5, 5)

        listOf(
            MoveForward(),
            MoveForward(),
            MoveForward(),
            MoveForward(),
            MoveForward(),
            MoveForward()
        )
            .forEach { robot.run(it, world) }

        assertThat(robot).isEqualTo(
            Robot(
                Position(
                    0,
                    5,
                    Direction.NORTH
                ), State.LOST
            )
        )
    }

    @Test
    fun `robot ignores command when lost`() {
        val robot = Robot(
            Position(
                0,
                0,
                Direction.NORTH
            ), State.ALIVE
        )
        val world = World(5, 5)

        listOf(
            MoveForward(),
            MoveForward(),
            MoveForward(),
            MoveForward(),
            MoveForward(),
            MoveForward(),
            MoveForward()
        )
            .forEach { robot.run(it, world) }

        assertThat(robot).isEqualTo(
            Robot(
                Position(
                    0,
                    5,
                    Direction.NORTH
                ), State.LOST
            )
        )
    }
}
