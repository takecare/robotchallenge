package uk.co.ruibot.robots.robot

data class Robot(val position: Position, val state: State) {
//    fun run(command: Command<*>, world: World): Result<*> {
//        return command.execute(this, world)
//    }
}

enum class State {
    LOST,
    ALIVE
}
