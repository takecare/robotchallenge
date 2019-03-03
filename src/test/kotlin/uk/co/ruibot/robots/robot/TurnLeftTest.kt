package uk.co.ruibot.robots.robot

import com.google.common.truth.Truth
import org.junit.Test

class TurnLeftTest {

    @Test
    fun `robot turns left when facing north`() {

        val result = TurnLeft.execute(aRobot(), aWorld())
        val position = result.contentOrNull as Position

        Truth.assertThat(position.direction).isEqualTo(Direction.WEST)
    }

    @Test
    fun `robot turns left when facing east`() {

        val result = TurnLeft.execute(aRobot().facingEast(), aWorld())
        val position = result.contentOrNull as Position

        Truth.assertThat(position.direction).isEqualTo(Direction.NORTH)
    }

    @Test
    fun `robot turns left when facing south`() {

        val result = TurnLeft.execute(aRobot().facingSouth(), aWorld())
        val position = result.contentOrNull as Position

        Truth.assertThat(position.direction).isEqualTo(Direction.EAST)
    }

    @Test
    fun `robot turns left when facing west`() {

        val result = TurnLeft.execute(aRobot().facingWest(), aWorld())
        val position = result.contentOrNull as Position

        Truth.assertThat(position.direction).isEqualTo(Direction.SOUTH)
    }
}
