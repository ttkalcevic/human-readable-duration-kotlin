package io.github.ttkalcevic.duration

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class DurationTest {

    @Test
    fun `should create duration with correct properties`() {
        val duration = Duration(3665) // 1 hour, 1 minute, 5 seconds

        assertEquals(1, duration.hours)
        assertEquals(1, duration.minutes)
        assertEquals(5, duration.seconds)
        assertEquals(0, duration.days)
        assertEquals(3665, duration.totalSeconds)
        assertEquals(61, duration.totalMinutes)
        assertEquals(1, duration.totalHours)
        assertEquals(0, duration.totalDays)
    }

    @Test
    fun `should handle zero duration`() {
        val duration = Duration(0)

        assertEquals(0, duration.days)
        assertEquals(0, duration.hours)
        assertEquals(0, duration.minutes)
        assertEquals(0, duration.seconds)
        assertTrue(duration.isZero())
        assertFalse(duration.isPositive())
    }

    @Test
    fun `should handle large durations`() {
        val duration = Duration(90061) // 1 day, 1 hour, 1 minute, 1 second

        assertEquals(1, duration.days)
        assertEquals(1, duration.hours)
        assertEquals(1, duration.minutes)
        assertEquals(1, duration.seconds)
        assertEquals(1, duration.totalDays)
    }

    @Test
    fun `should throw exception for negative duration`() {
        assertThrows<IllegalArgumentException> {
            Duration(-1)
        }
    }

    @Test
    fun `should support arithmetic operations`() {
        val duration1 = Duration(100)
        val duration2 = Duration(50)

        assertEquals(Duration(150), duration1 + duration2)
        assertEquals(Duration(50), duration1 - duration2)
        assertEquals(Duration(0), duration2 - duration1) // Should not go negative
        assertEquals(Duration(300), duration1 * 3)
        assertEquals(Duration(50), duration1 / 2)
    }

    @Test
    fun `should support comparison operations`() {
        val duration1 = Duration(100)
        val duration2 = Duration(50)
        val duration3 = Duration(100)

        assertTrue(duration1 > duration2)
        assertTrue(duration2 < duration1)
        assertEquals(duration1, duration3)
        assertEquals(0, duration1.compareTo(duration3))
    }

    @Test
    fun `should create durations using companion object methods`() {
        assertEquals(Duration(86400), Duration.ofDays(1))
        assertEquals(Duration(3600), Duration.ofHours(1))
        assertEquals(Duration(60), Duration.ofMinutes(1))
        assertEquals(Duration(30), Duration.ofSeconds(30))
    }
}