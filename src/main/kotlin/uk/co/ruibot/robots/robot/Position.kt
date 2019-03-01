package uk.co.ruibot.robots.robot

data class Position( // FIXME i don't like this being mutable... maybe go fo immutability?
    var x: Int = 0,
    var y: Int = 0,
    var direction: Direction = Direction.NORTH
)

enum class Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST
}
