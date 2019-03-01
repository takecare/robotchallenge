package uk.co.ruibot.robots.robot

data class Robot(val position: Position, val state: State) {
    val x: Int
        get() = position.x
    val y: Int
        get() = position.y
    val isLost: Boolean
        get() = state == State.LOST
    val direction: Direction
        get() = position.direction
}

enum class State {
    LOST,
    ALIVE
}
