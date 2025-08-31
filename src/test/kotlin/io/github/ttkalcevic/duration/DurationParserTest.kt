package io.github.ttkalcevic.duration

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class DurationParserTest {

    @Test
    fun `should parse colon-separated formats`() {
        assertEquals(Duration(150), DurationParser.parse("2:30")) // 2m 30s
        assertEquals(Duration(3665), DurationParser.parse("1:01:05")) // 1h 1m 5s
        assertEquals(Duration(90061), DurationParser.parse("1:01:01:01")) // 1d 1h 1m 1s
    }

    @Test
    fun `should parse compact formats`() {
        assertEquals(Duration(30), DurationParser.parse("30s"))
        assertEquals(Duration(150), DurationParser.parse("2m30s"))
        assertEquals(Duration(3665), DurationParser.parse("1h1m5s"))
        assertEquals(Duration(95445), DurationParser.parse("1d2h30m45s"))
    }

    @Test
    fun `should parse compact formats with spaces`() {
        assertEquals(Duration(150), DurationParser.parse("2m 30s"))
        assertEquals(Duration(3665), DurationParser.parse("1h 1m 5s"))
        assertEquals(Duration(7200), DurationParser.parse("2 h"))
    }

    @Test
    fun `should parse verbose formats`() {
        assertEquals(Duration(1), DurationParser.parse("1 second"))
        assertEquals(Duration(60), DurationParser.parse("1 minute"))
        assertEquals(Duration(3600), DurationParser.parse("1 hour"))
        assertEquals(Duration(86400), DurationParser.parse("1 day"))
        assertEquals(Duration(150), DurationParser.parse("2 minutes 30 seconds"))
        assertEquals(Duration(3665), DurationParser.parse("1 hour 1 minute 5 seconds"))
    }

    @Test
    fun `should handle case insensitive parsing`() {
        assertEquals(Duration(3665), DurationParser.parse("1H1M5S"))
        assertEquals(Duration(3600), DurationParser.parse("1 HOUR"))
        assertEquals(Duration(60), DurationParser.parse("1 Minute"))
    }

    @Test
    fun `should return null for invalid formats`() {
        assertNull(DurationParser.parse(""))
        assertNull(DurationParser.parse("invalid"))
        assertNull(DurationParser.parse("1:2:3:4:5"))
        assertNull(DurationParser.parse("abc:def"))
        assertNull(DurationParser.parse("1x2y3z"))
    }

    @Test
    fun `should handle edge cases in parsing`() {
        assertEquals(Duration(0), DurationParser.parse("0s"))
        assertEquals(Duration(0), DurationParser.parse("0:00"))
        assertEquals(Duration(3600), DurationParser.parse("60:00")) // 60 minutes = 1 hour
    }
}
