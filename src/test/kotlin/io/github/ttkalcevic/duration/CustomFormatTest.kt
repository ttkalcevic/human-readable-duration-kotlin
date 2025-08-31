package io.github.ttkalcevic.duration

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CustomFormatTest {

    @Test
    fun `should format with custom patterns`() {
        val duration = 93784.seconds() // 1d 2h 3m 4s

        assertEquals(
            "1d 2h 3m 4s",
            duration.toHumanReadable(Format.Custom("{d}d {h}h {m}m {s}s"))
        )

        assertEquals(
            "1d 2h 3m 4s",
            duration.toHumanReadable(Format.Custom("{d}d {h}h {m}m {s}s"))
        )

        // skip days!
        assertEquals(
            "2h 3m 4s",
            duration.toHumanReadable(Format.Custom("{h}h {m}m {s}s"))
        )

        // skip hours!
        assertEquals(
            "1d 3m 4s",
            duration.toHumanReadable(Format.Custom("{d}d {m}m {s}s"))
        )

        // skip minutes!
        assertEquals(
            "1d 2h 4s",
            duration.toHumanReadable(Format.Custom("{d}d {h}h {s}s"))
        )

        // skip seconds!
        assertEquals(
            "1d 2h 3m",
            duration.toHumanReadable(Format.Custom("{d}d {h}h {m}m"))
        )

        assertEquals(
            "Duration: 1 day, 2 hours, 3 minutes, 4 seconds",
            duration.toHumanReadable(Format.Custom("Duration: {d} day, {h} hours, {m} minutes, {s} seconds"))
        )
    }

    @Test
    fun `should handle zero values in custom format`() {
        val duration = 61.seconds() // 1m 1s

        assertEquals(
            "0d 0h 1m 1s",
            duration.toHumanReadable(Format.Custom("{d}d {h}h {m}m {s}s"))
        )
    }
}
