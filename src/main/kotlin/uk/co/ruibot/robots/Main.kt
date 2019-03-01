package uk.co.ruibot.robots

import uk.co.ruibot.robots.parsing.parsePath
import uk.co.ruibot.robots.parsing.parsePosition
import uk.co.ruibot.robots.parsing.parseWorldSize
import uk.co.ruibot.robots.robot.Command
import uk.co.ruibot.robots.robot.Robot
import uk.co.ruibot.robots.robot.State

fun main() {

    print("world size: ")
    val sizeLine = readInput()
    val worldSize = parseWorldSize(sizeLine)
    val robots = mutableMapOf<Robot, List<Command<*>>>()

    while (true) {
        print("robot position: ")
        val lineInput = readInput()
        if (lineInput.isEmpty()) {
            break
        }
        val position = parsePosition(lineInput)

        print("commands: ")
        val commandsInput = readInput()
        if (commandsInput.isEmpty()) {
            break
        }
        val commands = parsePath(commandsInput)

        robots += Robot(position, State.ALIVE) to commands
    }

    val world = World(worldSize.first, worldSize.second)
    robots.forEach { robot, commands -> println(world.run(robot, commands)) }
}

private fun readInput() = readLine() ?: throw IllegalStateException()
