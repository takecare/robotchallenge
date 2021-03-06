package uk.co.ruibot.robots.robot

import uk.co.ruibot.robots.World

sealed class Move : Command<Position>

object MoveForward : Move() {

    override fun execute(robot: Robot, world: World): Result<Position> = if (robot.isLost) {
        Payload(robot.position)
    } else {
        robot.position.goForwardOrError(world)
    }
}

private fun Position.goForwardOrError(world: World): Result<Position> = when (direction) {
    Direction.NORTH -> moveForwardIf { y + 1 > world.height }
    Direction.EAST -> moveForwardIf { x + 1 > world.width }
    Direction.SOUTH -> moveForwardIf { y - 1 < 0 }
    Direction.WEST -> moveForwardIf { x - 1 < 0 }
}

private fun Position.moveForwardIf(cannotMove: () -> Boolean) = when {
    cannotMove() -> Error(content = this)
    else -> Payload(moveForward())
}

private fun Position.moveForward() = when (direction) {
    Direction.NORTH -> Position(x, y + 1, Direction.NORTH)
    Direction.EAST -> Position(x + 1, y, Direction.EAST)
    Direction.SOUTH -> Position(x, y - 1, Direction.SOUTH)
    Direction.WEST -> Position(x - 1, y, Direction.WEST)
}

object TurnLeft : Move() {
    override fun execute(robot: Robot, world: World): Result<Position> = if (robot.isLost) {
        Payload(robot.position)
    } else {
        Payload(robot.goLeft())
    }
}

private fun Robot.goLeft() = position.goLeft()

private fun Position.goLeft() = when (direction) {
    Direction.NORTH -> toWest()
    Direction.EAST -> toNorth()
    Direction.SOUTH -> toEast()
    Direction.WEST -> toSouth()
}

object TurnRight : Move() {
    override fun execute(robot: Robot, world: World): Result<Position> = if (robot.isLost) {
        Payload(robot.position)
    } else {
        Payload(robot.goRight())
    }
}

private fun Robot.goRight() = position.goRight()

private fun Position.goRight() = when (direction) {
    Direction.NORTH -> toEast()
    Direction.EAST -> toSouth()
    Direction.SOUTH -> toWest()
    Direction.WEST -> toNorth()
}

