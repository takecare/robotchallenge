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

enum class Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST
}

class Robot(
    val x: Int,
    val y: Int
)
