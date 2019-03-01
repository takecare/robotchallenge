package uk.co.ruibot.robots.robot

import uk.co.ruibot.robots.World

sealed class Move : Command<Position>

object MoveForward : Move() { // FIXME smell: hashCode() & equals() implemented for testing purposes

    override fun execute(robot: Robot, world: World): Result<Position> = if (robot.isLost) {
        Payload(robot.position)
    } else {
        robot.position.goForwardOrError(world)
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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}

object TurnLeft : Move() { // FIXME smell: hashCode() & equals() implemented for testing purposes
    override fun execute(robot: Robot, world: World): Result<Position> = if (robot.isLost) {
        Payload(robot.position)
    } else {
        Payload(robot.position.goLeft())
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}

object TurnRight : Move() { // FIXME smell: hashCode() & equals() implemented for testing purposes
    override fun execute(robot: Robot, world: World): Result<Position> = if (robot.isLost) {
        Payload(robot.position)
    } else {
        Payload(robot.position.goRight())
    }

    private fun Position.goRight() = when (direction) {
        Direction.NORTH -> copy(direction = Direction.EAST)
        Direction.EAST -> copy(direction = Direction.SOUTH)
        Direction.SOUTH -> copy(direction = Direction.WEST)
        Direction.WEST -> copy(direction = Direction.NORTH)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}

private fun Position.moveForward() = when (direction) {
    Direction.NORTH -> Position(x, y + 1, Direction.NORTH)
    Direction.EAST -> Position(x + 1, y, Direction.EAST)
    Direction.SOUTH -> Position(x, y - 1, Direction.SOUTH)
    Direction.WEST -> Position(x - 1, y, Direction.WEST)
}

private fun Position.goLeft() = when (direction) {
    Direction.NORTH -> copy(direction = Direction.WEST)
    Direction.EAST -> copy(direction = Direction.NORTH)
    Direction.SOUTH -> copy(direction = Direction.EAST)
    Direction.WEST -> copy(direction = Direction.SOUTH)
}
