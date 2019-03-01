package uk.co.ruibot.robots.parsing

import com.google.common.truth.Truth.assertThat
import org.junit.Ignore
import org.junit.Test
import uk.co.ruibot.robots.robot.Direction
import uk.co.ruibot.robots.robot.MoveForward
import uk.co.ruibot.robots.robot.TurnLeft
import uk.co.ruibot.robots.robot.TurnRight

class ParserTest {

    @Test
    fun `correctly parses size string "5 3"`() {
        val result = parseWorldSize("5 3")

        assertThat(result.first).isEqualTo(5)
        assertThat(result.second).isEqualTo(3)
    }

    @Test
    fun `correctly parses size string "50 50"`() {
        val result = parseWorldSize("50 50")

        assertThat(result.first).isEqualTo(50)
        assertThat(result.second).isEqualTo(50)
    }

    @Test(expected = InvalidSize::class)
    fun `fails parsing size if there is a missing value`() {
        parseWorldSize("1988")
    }

    @Test
    @Ignore
    fun `fails parsing size if there are negative values`() {
        parseWorldSize("-50 50")
        // TODO implement negative values constraint
    }

    @Test
    @Ignore
    fun `fails if there are values greater than 50`() {
        parseWorldSize("19 88")
        // TODO implement 50,50 limit
    }

    @Test
    fun `correctly parses position coordinates`() {
        val result = parsePosition("19 88 N")

        assertThat(result.x).isEqualTo(19)
        assertThat(result.y).isEqualTo(88)
        assertThat(result.direction).isEqualTo(Direction.NORTH)
    }

    @Test(expected = InvalidSize::class)
    fun `fails parsing position if there is a missing value`() {
        parsePosition("88 N")
    }

    @Test
    @Ignore
    fun `fails if there  is an x position coordinate greater than 50`() {
        parsePosition("88 8 N")
        // TODO implement 50,50 limit
    }

    @Test
    @Ignore
    fun `fails if there  is a y position coordinate greater than 50`() {
        parsePosition("8 88 N")
        // TODO implement 50,50 limit
    }

    @Test
    fun `correctly parses position direction - north`() {
        val result = parsePosition("19 74 N")

        assertThat(result.direction).isEqualTo(Direction.NORTH)
    }

    @Test
    fun `correctly parses position direction - east`() {
        val result = parsePosition("19 74 E")

        assertThat(result.direction).isEqualTo(Direction.EAST)
    }

    @Test
    fun `correctly parses position direction - south`() {
        val result = parsePosition("19 74 S")

        assertThat(result.direction).isEqualTo(Direction.SOUTH)
    }

    @Test(expected = InvalidDirection::class)
    fun `fails if invalid direction found`() {
        parsePosition("19 74 X")
    }

    @Test
    fun `correctly parses position direction - west`() {
        val result = parsePosition("19 74 W")

        assertThat(result.direction).isEqualTo(Direction.WEST)
    }

    @Test
    fun `correctly parses path - RFL`() {
        val result = parsePath("RLF")
        assertThat(result).containsExactlyElementsIn(
            listOf(
                TurnRight,
                TurnLeft,
                MoveForward
            )
        ).inOrder()
    }

    @Test
    fun `correctly parses path - RFLRLF`() {
        val result = parsePath("RLFRLF")
        assertThat(result)
            .containsExactlyElementsIn(
                listOf(
                    TurnRight,
                    TurnLeft,
                    MoveForward,
                    TurnRight,
                    TurnLeft,
                    MoveForward
                )
            )
            .inOrder()
    }

    @Test(expected = InvalidCommand::class)
    fun `fails if invalid command found`() {
        parsePath("RLXF")
    }
}
