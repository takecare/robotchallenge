package uk.co.ruibot.robots


fun main() {
    // TODO 1. read world size

    /*do {
        val line = readLine()
        println("> $line")
    } while (line != null)*/

    // TODO 2. read (on loop) robots [define breaking condition]
    // TODO 3. evaluate all robots


    val robot1 = Robot(Position(1, 1, Direction.EAST), State.ALIVE)
    val commands1 = mutableListOf(
        TurnRight(),
        MoveForward(),
        TurnRight(),
        MoveForward(),
        TurnRight(),
        MoveForward(),
        TurnRight(),
        MoveForward()
    )

    val robot2 = Robot(Position(3, 2, Direction.NORTH), State.ALIVE)
    val commands2 = mutableListOf(
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

    val robot3 = Robot(Position(0, 3, Direction.WEST), State.ALIVE)
    val commands3 = mutableListOf(
        TurnRight(),
        MoveForward(),
        TurnRight(),
        MoveForward(),
        TurnRight(),
        MoveForward(),
        TurnRight(),
        MoveForward()
    )


    val world = World(5, 3)
//    world.run(robot1, commands1)
    world.run(robot2, commands2)
//    world.run(robot3, commands3)
}

// TODO define domain & changes to it

class World(
    val width: Int,
    val height: Int
) {

    private val scents: List<Robot> = mutableListOf()

    // TODO move robots?
    fun run(robot: Robot, commands: List<Command>) {
        commands.forEach { robot.run(it, this) }
        println(robot)
    }
}

enum class Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST
}

interface Command {
    fun execute(
        robot: Robot,
        world: World
    ) // TODO should return something... new position (x,y,dir)? but if new position then it's not "generic" -- this goes with immutability!
}

class MoveForward : Command {
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

class TurnLeft : Command {
    override fun execute(robot: Robot, world: World) {
        if (robot.state == State.LOST) {
            return
        }

        when (robot.position.direction) { // FIXME fix this train (with kgetter?)
            Direction.NORTH -> robot.position.direction = Direction.WEST
            Direction.EAST -> robot.position.direction = Direction.NORTH
            Direction.SOUTH -> robot.position.direction = Direction.EAST
            Direction.WEST -> robot.position.direction = Direction.SOUTH
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

class TurnRight : Command {
    override fun execute(robot: Robot, world: World) {
        if (robot.state == State.LOST) {
            return
        }

        when (robot.position.direction) {
            Direction.NORTH -> robot.position.direction = Direction.EAST
            Direction.EAST -> robot.position.direction = Direction.SOUTH
            Direction.SOUTH -> robot.position.direction = Direction.WEST
            Direction.WEST -> robot.position.direction = Direction.NORTH
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

data class Robot(val position: Position, var state: State) {
    fun run(command: Command, world: World) {
        command.execute(this, world)
    }
}

data class Position( // FIXME i don't like this being mutable... maybe go fo immutability?
    var x: Int = 0,
    var y: Int = 0,
    var direction: Direction = Direction.NORTH
)

enum class State {
    LOST,
    ALIVE
}
