package uk.co.ruibot.robots.robot

import uk.co.ruibot.robots.World

data class Robot(val position: Position, val state: State) {
    fun execute(command: Command<*>, world: World): Result<*> {
        return command.execute(this, world)
    }
}

enum class State {
    LOST,
    ALIVE
}
