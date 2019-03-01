package uk.co.ruibot.robots

fun main() {
    // TODO 1. read world size

    do {
        val line = readLine()
        println("> $line")
    } while (line != null)

    // TODO 2. read (on loop) robots [define breaking condition]
    // TODO 3. evaluate all robots
}

// TODO define domain & changes to it

class World(
    private val width: Int,
    private val height: Int
) {
    private val robots: Pair<Robot, List<Command>> =
        Pair(Robot(0, 0, Direction.NORTH), mutableListOf()) // FIXME will have to be <Robot?, List> ??
    private val scents: List<Robot> = mutableListOf()

    // TODO move robots?
}

enum class Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST
}

interface Command {
    fun execute() // TODO should return something... new position (x,y,dir)? but if new position then it's not "generic"
}

class Robot(val position: Position) {

    fun run(command: Command) {
        // TODO run on this robot
    }
}

data class Position(
    val x: Int,
    val y: Int,
    val direction: Direction
)
