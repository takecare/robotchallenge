package uk.co.ruibot.robots.robot

import com.google.common.truth.Truth
import org.junit.Test

// TODO parameterised test? @RUI
class TurnRightTest {

    @Test
    fun `robot turns right when facing north`() {

        val result = TurnRight.execute(aRobot(), aWorld())
        val position = result.contentOrNull as Position

        Truth.assertThat(position.direction).isEqualTo(Direction.EAST)
    }

    @Test
    fun `robot turns right when facing east`() {

        val result = TurnRight.execute(aRobot().facingEast(), aWorld())
        val position = result.contentOrNull as Position

        Truth.assertThat(position.direction).isEqualTo(Direction.SOUTH)
    }

    @Test
    fun `robot turns right when facing south`() {

        val result = TurnRight.execute(aRobot().facingSouth(), aWorld())
        val position = result.contentOrNull as Position

        Truth.assertThat(position.direction).isEqualTo(Direction.WEST)
    }

    @Test
    fun `robot turns right when facing west`() {

        val result = TurnRight.execute(aRobot().facingWest(), aWorld())
        val position = result.contentOrNull as Position

        Truth.assertThat(position.direction).isEqualTo(Direction.NORTH)
    }
}
