package uk.co.ruibot.robots.robot

import uk.co.ruibot.robots.World

interface Command {
    fun execute(
        robot: Robot,
        world: World
    )
    // TODO should return something... new position (x,y,dir)? but if new position then it's not "generic" -- this goes with immutability!
}

class MoveForward : Command { // FIXME smell: hashCode() & equals() implemented for testing purposes
    override fun execute(robot: Robot, world: World) {
        if (robot.state == State.LOST) {
            return
        }

        when (robot.position.direction) {
            Direction.NORTH -> {
                if (robot.position.y + 1 > world.height) {
                    robot.state = State.LOST
                } else {
                    robot.position.y += 1
                }
            }
            Direction.EAST -> {
                if (robot.position.x + 1 > world.width) {
                    robot.state = State.LOST
                } else {
                    robot.position.x += 1
                }
            }
            Direction.SOUTH -> {
                if (robot.position.y - 1 < 0) {
                    robot.state = State.LOST
                } else {
                    robot.position.y -= 1
                }
            }
            Direction.WEST -> {
                if (robot.position.x - 1 < 0) {
                    robot.state = State.LOST
                } else {
                    robot.position.x -= 1
                }
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

class TurnLeft : Command { // FIXME smell: hashCode() & equals() implemented for testing purposes
    override fun execute(robot: Robot, world: World) {
        if (robot.state == State.LOST) {
            return
        }

        when (robot.position.direction) { // FIXME fix this train (with kgetter?)
            Direction.NORTH -> robot.position.direction =
                Direction.WEST
            Direction.EAST -> robot.position.direction =
                Direction.NORTH
            Direction.SOUTH -> robot.position.direction =
                Direction.EAST
            Direction.WEST -> robot.position.direction =
                Direction.SOUTH
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

class TurnRight : Command { // FIXME smell: hashCode() & equals() implemented for testing purposes
    override fun execute(robot: Robot, world: World) {
        if (robot.state == State.LOST) {
            return
        }

        when (robot.position.direction) {
            Direction.NORTH -> robot.position.direction =
                Direction.EAST
            Direction.EAST -> robot.position.direction =
                Direction.SOUTH
            Direction.SOUTH -> robot.position.direction =
                Direction.WEST
            Direction.WEST -> robot.position.direction =
                Direction.NORTH
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

class TakePhoto : Command {
    override fun execute(robot: Robot, world: World) {
        println("Took a photo at ${robot.position}!")
    }
}
