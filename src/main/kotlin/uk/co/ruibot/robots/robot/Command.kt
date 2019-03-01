package uk.co.ruibot.robots.robot

import uk.co.ruibot.robots.World

interface Command<T> {
    fun execute(robot: Robot, world: World): Result<T>
    // TODO should return something... new position (x,y,dir)? but if new position then it's not "generic" -- this goes with immutability!
}

class TakePhoto : Command<String> {
    override fun execute(robot: Robot, world: World): Result<String> =
        Payload("Took a photo at ${robot.position}!")
}
