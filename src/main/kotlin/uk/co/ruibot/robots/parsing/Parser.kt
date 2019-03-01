package uk.co.ruibot.robots.parsing

import uk.co.ruibot.robots.robot.*

fun parseWorldSize(line: String): Pair<Int, Int> { // TODO should be WorldSize @RUI
    val values = line.split(" ")
    // TODO throw if values.size != 2
    // TODO throw if values[0] || values[1] are not valid ints
    return Pair(values[0].toInt(), values[1].toInt())
}

fun parsePosition(line: String): Position {
    val values = line.split(" ")
    // TODO throw if values.size != 2
    // TODO throw if values[0] || values[1] are not valid ints
    return Position(values[0].toInt(), values[1].toInt(), values[2].toDirection())
}

fun parsePath(line: String) = line.chunked(1).map { it.toCommand() }

private fun String.toCommand() = when (this) {
    "F" -> MoveForward()
    "R" -> TurnRight()
    "L" -> TurnLeft()
    "P" -> TakePhoto()
    else -> throw IllegalArgumentException("Unexpected command '$this'")
}

private fun String.toDirection() = when (this) {
    "N" -> Direction.NORTH
    "E" -> Direction.EAST
    "S" -> Direction.SOUTH
    "W" -> Direction.WEST
    else -> throw IllegalArgumentException("Unexpected direction '$this'")
}

private enum class State {
    READING_WORLD_SIZE,
    READING_ROBOT_POSITION,
    READING_ROBOT_PATH
}
