package uk.co.ruibot.robots.robot

data class Robot(val position: Position, val state: State = State.ALIVE) {
    val isLost: Boolean
        get() = state == State.LOST
    val direction: Direction
        get() = position.direction
}

enum class State {
    LOST,
    ALIVE
}
