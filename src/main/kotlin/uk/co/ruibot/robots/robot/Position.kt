package uk.co.ruibot.robots.robot

data class Position( // FIXME i don't like this being mutable... maybe go fo immutability?
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
    var x: Int = 0,
    var y: Int = 0
)

enum class Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST
}
