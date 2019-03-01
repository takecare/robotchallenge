package uk.co.ruibot.robots

import uk.co.ruibot.robots.robot.Command
import uk.co.ruibot.robots.robot.Position
import uk.co.ruibot.robots.robot.Robot
import uk.co.ruibot.robots.robot.State

class World(val width: Int, val height: Int) {

    private val scents: MutableSet<Position> = mutableSetOf()

    fun run(startingRobot: Robot, commands: List<Command<*>>): Robot = commands
        .fold(startingRobot) { robot, command -> execute(command, robot) }

    private fun execute(command: Command<*>, robot: Robot): Robot {
        val result = command.execute(robot, this)
        return when (result.contentOrNull) {
            is Position -> movedOrLost(result.contentOrNull as Position, robot)
            else -> robot // for now every command that's not a move does not change the robot
        }
    }

    private fun movedOrLost(position: Position, robot: Robot): Robot {
        val movedRobot = Robot(position, State.ALIVE)
        return when {
            scentFound(movedRobot, robot) -> Robot(
                position,
                State.ALIVE
            )
            robotGotLost(movedRobot, robot) -> {
                scents.add(movedRobot.position)
                robot.asLost()
            }
            else -> movedRobot
        }
    }

    private fun robotGotLost(newRobot: Robot, robot: Robot) = newRobot.position == robot.position

    private fun scentFound(newRobot: Robot, currentRobot: Robot) =
        currentRobot.state == State.ALIVE && newRobot.position in scents
}

private fun Robot.asLost() = copy(state = State.LOST)
