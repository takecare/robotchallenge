package uk.co.ruibot.robots.robot

data class Position(
    val coordinates: Coordinates = Coordinates(0, 0),
    val direction: Direction = Direction.NORTH
) {
    constructor(x: Int = 0, y: Int = 0, direction: Direction = Direction.NORTH) : this(Coordinates(x, y), direction)

    val x: Int
        get() = coordinates.x
    val y: Int
        get() = coordinates.y
}

data class Coordinates(
    val x: Int = 0,
    val y: Int = 0
)

enum class Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST
}
