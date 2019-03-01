package uk.co.ruibot.robots

import uk.co.ruibot.robots.robot.*


fun main() {
    // TODO 1. read world size

    /*do {
        val line = readLine()
        println("> $line")
    } while (line != null)*/

    // TODO 2. read (on loop) robots [define breaking condition]
    // TODO 3. evaluate all robots


    val robot1 = Robot(
        Position(
            1,
            1,
            Direction.EAST
        ), State.ALIVE
    )
    val commands1 = mutableListOf(
        TurnRight(),
        MoveForward(),
        TurnRight(),
        MoveForward(),
        TurnRight(),
        MoveForward(),
        TurnRight(),
        MoveForward()
    )

    val robot2 = Robot(
        Position(
            3,
            2,
            Direction.NORTH
        ), State.ALIVE
    )
    val commands2 = mutableListOf(
        MoveForward(),
        TurnRight(),
        TurnRight(),
        MoveForward(),
        TurnLeft(),
        TurnLeft(),
        MoveForward(),
        MoveForward(),
        TurnRight(),
        TurnRight(),
        MoveForward(),
        TurnLeft(),
        TurnLeft()
    )

    val robot3 = Robot(
        Position(
            0,
            3,
            Direction.WEST
        ), State.ALIVE
    )
    val commands3 = mutableListOf(
        TurnRight(),
        MoveForward(),
        TurnRight(),
        MoveForward(),
        TurnRight(),
        MoveForward(),
        TurnRight(),
        MoveForward()
    )


    val world = World(5, 3)
    world.run(robot1, commands1)
    world.run(robot2, commands2)
    world.run(robot3, commands3)
}

class World(
    val width: Int,
    val height: Int
) {

    private var scents: List<Robot> = listOf()

    fun run(robot: Robot, commands: List<Command>) {
        commands.forEach {
            val result = robot.run(it, this)
            if (result == Result.FAIL) {
                scents = scents + robot
            }
        }
        println(robot)
    }
}
