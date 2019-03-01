package uk.co.ruibot.robots

fun main() {
    // TODO 1. read world size

    /*do {
        val line = readLine()
        println("> $line")
    } while (line != null)*/

    // TODO 2. read (on loop) robots [define breaking condition]
    // TODO 3. evaluate all robots


    val robot = Robot(Position(), State.ALIVE)
    val commands = mutableListOf(MoveForward(), MoveForward(), MoveForward())
    val world = World(5, 5)

    world.run(robot, commands)
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
    ) // TODO should return something... new position (x,y,dir)? but if new position then it's not "generic"
}


class MoveForward : Command {
    override fun execute(robot: Robot, world: World) {
        // TODO manage movement accordingly (i.e. take direction and bounds in account)

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
}

class TurnLeft : Command {
    override fun execute(robot: Robot, world: World) {
        when (robot.position.direction) {
            Direction.NORTH -> robot.position.direction = Direction.WEST
            Direction.EAST -> robot.position.direction = Direction.NORTH
            Direction.SOUTH -> robot.position.direction = Direction.EAST
            Direction.WEST -> robot.position.direction = Direction.SOUTH
        }
    }
}

class TurnRight : Command {
    override fun execute(robot: Robot, world: World) {
        when (robot.position.direction) {
            Direction.NORTH -> robot.position.direction = Direction.EAST
            Direction.EAST -> robot.position.direction = Direction.SOUTH
            Direction.SOUTH -> robot.position.direction = Direction.WEST
            Direction.WEST -> robot.position.direction = Direction.NORTH
        }
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

data class Position(
    var x: Int = 0,
    var y: Int = 0,
    var direction: Direction = Direction.NORTH
)

enum class State {
    LOST,
    ALIVE
}
