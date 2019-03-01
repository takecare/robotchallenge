package uk.co.ruibot.robots.parsing

import uk.co.ruibot.robots.robot.*

fun parseWorldSize(line: String): Pair<Int, Int> { // TODO should be WorldSize @RUI
    val values = line.split(" ")
    if (values.size != 2) {
        throw InvalidSize("Invalid size value.")
    }
    return Pair(values[0].toInt(), values[1].toInt())
}

private class InvalidSize(message: String) : IllegalArgumentException(message)

fun parsePosition(line: String): Position {
    val values = line.split(" ")
    if (values.size != 3) {
        throw InvalidSize("Invalid position value.")
    }
    return Position(values[0].toInt(), values[1].toInt(), values[2].toDirection())
}

fun parsePath(line: String) = line.chunked(1).map { it.toCommand() }

private fun String.toCommand() = when (this) {
    "F" -> MoveForward
    "R" -> TurnRight
    "L" -> TurnLeft
    "P" -> TakePhoto
    else -> throw InvalidCommand("Unexpected command '$this'")
}

private class InvalidCommand(message: String) : IllegalArgumentException(message)

private fun String.toDirection() = when (this) {
    "N" -> Direction.NORTH
    "E" -> Direction.EAST
    "S" -> Direction.SOUTH
    "W" -> Direction.WEST
    else -> throw InvalidDirection("Unexpected direction '$this'")
}

class InvalidDirection(message: String) : IllegalArgumentException(message)
