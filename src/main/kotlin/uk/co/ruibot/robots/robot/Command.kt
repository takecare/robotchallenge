package uk.co.ruibot.robots.robot

import uk.co.ruibot.robots.World

interface Command<T> {
    fun execute(robot: Robot, world: World): Result<T>
}

object TakePhoto : Command<String> {
    override fun execute(robot: Robot, world: World): Result<String> =
        Payload("Took a photo at ${robot.position}!")
}
