package uk.co.ruibot.robots

fun main() {
    // TODO 1. read world size

    /*do {
        val line = readLine()
        println("> $line")
    } while (line != null)*/

    // TODO 2. read (on loop) robots [define breaking condition]
    // TODO 3. evaluate all robots


    val robot = Robot(Position())
    val commands = mutableListOf(MoveForward())
    val world = World(5, 5)

    world.run(robot, commands)
}

// TODO define domain & changes to it

class World(
    private val width: Int,
    private val height: Int
) {

    private val scents: List<Robot> = mutableListOf()

    // TODO move robots?
    fun run(robot: Robot, commands: List<Command>) {
        commands.forEach { robot.run(it) }
    }
}

enum class Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST
}

interface Command {
    fun execute(robot: Robot) // TODO should return something... new position (x,y,dir)? but if new position then it's not "generic"
}

class MoveForward : Command {
    override fun execute(robot: Robot) {
        robot.position.x += 1 // TODO manage movement accordingly (i.e. take direction and bounds in account)
    }
}

class TakePhoto : Command {
    override fun execute(robot: Robot) {
        println("Took a photo at ${robot.position}")
    }
}

class Robot(val position: Position) {

    fun run(command: Command) {
        command.execute(this)
    }
}

data class Position(
    var x: Int = 0,
    val y: Int = 0,
    val direction: Direction = Direction.NORTH
)
