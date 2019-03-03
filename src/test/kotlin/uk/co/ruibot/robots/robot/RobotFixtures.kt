package uk.co.ruibot.robots.robot

import uk.co.ruibot.robots.World

fun aWorld(width: Int = 3, height: Int = 3) = World(width, height)
fun aRobot(x: Int = 0, y: Int = 0, direction: Direction = Direction.NORTH, state: State = State.ALIVE) =
    Robot(Position(x, y, direction), state)

private

fun Robot.facingNorth() = Robot(Position(position.x, position.y, Direction.NORTH), state)
fun Robot.facingEast() = Robot(Position(position.x, position.y, Direction.EAST), state)
fun Robot.facingSouth() = Robot(Position(position.x, position.y, Direction.SOUTH), state)
fun Robot.facingWest() = Robot(Position(position.x, position.y, Direction.WEST), state)

fun Robot.at(x: Int, y: Int) = Robot(Position(x, y,  direction), state)
