package io.github.ttkalcevic.duration

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class FormatTest {

    @Test
    fun `should format duration in AUTO format`() {
        assertEquals("30sec", 30.seconds().toHumanReadable(Format.AUTO))
        assertEquals("2min 30sec", 150.seconds().toHumanReadable(Format.AUTO))
        assertEquals("1h 30min", 5400.seconds().toHumanReadable(Format.AUTO))
        assertEquals("2h", 7200.seconds().toHumanReadable(Format.AUTO))
        assertEquals("1d 2h", 93600.seconds().toHumanReadable(Format.AUTO))
        assertEquals("2d", 172800.seconds().toHumanReadable(Format.AUTO))
    }

    @Test
    fun `should format duration in COMPACT format`() {
        assertEquals("30sec", 30.seconds().toHumanReadable(Format.COMPACT))
        assertEquals("2min30sec", 150.seconds().toHumanReadable(Format.COMPACT))
        assertEquals("1h30min", 5400.seconds().toHumanReadable(Format.COMPACT))
        assertEquals("1d2h30min45sec", 95445.seconds().toHumanReadable(Format.COMPACT))
        assertEquals("0sec", Duration.ZERO.toHumanReadable(Format.COMPACT))
    }

    @Test
    fun `should format duration in COLON_SEPARATED format`() {
        assertEquals("0:30", 30.seconds().toHumanReadable(Format.COLON_SEPARATED))
        assertEquals("2:30", 150.seconds().toHumanReadable(Format.COLON_SEPARATED))
        assertEquals("1:30:00", 5400.seconds().toHumanReadable(Format.COLON_SEPARATED))
        assertEquals("1:02:30:45", 95445.seconds().toHumanReadable(Format.COLON_SEPARATED))
    }

    @Test
    fun `should format duration with custom pattern`() {
        val duration = 3665.seconds() // 1h 1m 5s

        assertEquals("01:01:05", duration.toHumanReadable(Format.Custom("{hh}:{mm}:{ss}")))
        assertEquals("1h 1m 5s", duration.toHumanReadable(Format.Custom("{h}h {m}m {s}s")))
        assertEquals(
            "1 hour, 1 minute, 5 seconds",
            duration.toHumanReadable(Format.Custom("{h} hour, {m} minute, {s} seconds"))
        )
    }
}
