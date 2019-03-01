package uk.co.ruibot.robots

import uk.co.ruibot.robots.robot.*


fun main() {
    // TODO 1. read world size

    /*do {
        val line = readLine()
        println("> $line")
    } while (line != null)*/

    // TODO 2. read (on loop) robots [define breaking condition]
    // TODO 3. evaluate all robots


    val robot1 = Robot(
        Position(
            1,
            1,
            Direction.EAST
        ), State.ALIVE
    )
    val commands1 = mutableListOf(
        TurnRight,
        MoveForward,
        TurnRight,
        MoveForward,
        TurnRight,
        MoveForward,
        TurnRight,
        MoveForward
    )

    val robot2 = Robot(
        Position(
            3,
            2,
            Direction.NORTH
        ), State.ALIVE
    )
    val commands2 = mutableListOf(
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

    val robot3 = Robot(
        Position(
            0,
            3,
            Direction.WEST
        ), State.ALIVE
    )
    val commands3 = mutableListOf(
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


    val world = World(5, 3)
    println(world.run(robot1, commands1))
    println(world.run(robot2, commands2))
    println(world.run(robot3, commands3))
}

class World(
    val width: Int,
    val height: Int
) {

    private val scents: MutableSet<Position> = mutableSetOf()

    fun run(startingRobot: Robot, commands: List<Command<*>>): Robot = commands
        .fold(startingRobot) { robot, command -> execute(command, robot) }

    private fun execute(command: Command<*>, robot: Robot): Robot {
        val result = command.execute(robot, this)
        return when (result.contentOrNull) {
            is Position -> moveOrDisappear(result.contentOrNull as Position, robot)
            else -> robot
        }
    }

    private fun moveOrDisappear(position: Position, robot: Robot): Robot {
        val newRobot = Robot(position, State.ALIVE)
        return when {
            scentFound(newRobot, robot) -> Robot(position, State.ALIVE)
            robotGotLost(newRobot, robot) -> {
                scents.add(newRobot.position)
                robot.asLost()
            }
            else -> newRobot
        }
    }

    private fun robotGotLost(newRobot: Robot, robot: Robot) = newRobot.position == robot.position

    private fun scentFound(newRobot: Robot, currentRobot: Robot) =
        currentRobot.state == State.ALIVE && newRobot.position in scents

    private fun Robot.asLost() = copy(state = State.LOST)
}
