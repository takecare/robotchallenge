package uk.co.ruibot.robots.robot

import uk.co.ruibot.robots.World

interface Command<T> {
    fun execute(robot: Robot, world: World): Result<T>
    // TODO should return something... new position (x,y,dir)? but if new position then it's not "generic" -- this goes with immutability!
}

sealed class Move : Command<Position>

// TODO convert to object @RUI
class MoveForward : Move() { // FIXME smell: hashCode() & equals() implemented for testing purposes
    override fun execute(robot: Robot, world: World): Result<Position> {
        val position = robot.position
        return if (robot.state == State.LOST) {
            Payload(position)
        } else {
            position.goForwardOrError(world)
        }
    }

    private fun Position.goForwardOrError(world: World): Result<Position> = when (direction) {
        Direction.NORTH -> moveForwardIf { y + 1 > world.height }
        Direction.EAST -> moveForwardIf { x + 1 > world.width }
        Direction.SOUTH -> moveForwardIf { y - 1 < 0 }
        Direction.WEST -> moveForwardIf { x - 1 < 0 }
    }

    private fun Position.moveForwardIf(cannotMove: () -> Boolean): Result<Position> {
        return when {
            cannotMove() -> Error(content = this)
            else -> Payload(moveForward())
        }
    }

    private fun Position.moveForward(): Position {
        return when (direction) {
            Direction.NORTH -> Position(x, y + 1, Direction.NORTH)
            Direction.EAST -> Position(x + 1, y, Direction.EAST)
            Direction.SOUTH -> Position(x, y - 1, Direction.SOUTH)
            Direction.WEST -> Position(x - 1, y, Direction.WEST)
        }
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

class TurnLeft : Move() { // FIXME smell: hashCode() & equals() implemented for testing purposes
    override fun execute(robot: Robot, world: World): Result<Position> {
        return if (robot.state == State.LOST) {
            Payload(robot.position) // rule in purple -> "ignore commands if lost" @RUI
        } else {
            when (robot.position.direction) { // FIXME fix this train (with kgetter?)
                Direction.NORTH -> Payload(robot.position.copy(direction = Direction.WEST))
                Direction.EAST -> Payload(robot.position.copy(direction = Direction.NORTH))
                Direction.SOUTH -> Payload(robot.position.copy(direction = Direction.EAST))
                Direction.WEST -> Payload(robot.position.copy(direction = Direction.SOUTH))
            }
        }

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

class TurnRight : Move() { // FIXME smell: hashCode() & equals() implemented for testing purposes
    override fun execute(robot: Robot, world: World): Result<Position> {
        return if (robot.state == State.LOST) {
            Payload(robot.position) // rule in purple -> "ignore commands if lost" @RUI
        } else {

            when (robot.position.direction) {
                Direction.NORTH -> Payload(robot.position.copy(direction = Direction.EAST))
                Direction.EAST -> Payload(robot.position.copy(direction = Direction.SOUTH))
                Direction.SOUTH -> Payload(robot.position.copy(direction = Direction.WEST))
                Direction.WEST -> Payload(robot.position.copy(direction = Direction.NORTH))
            }
        }
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

class TakePhoto : Command<String> {
    override fun execute(robot: Robot, world: World): Result<String> =
        Payload("Took a photo at ${robot.position}!")
}
