package io.github.ttkalcevic.duration

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ExtensionsTest {

    @Test
    fun `should create durations using extension functions for Long`() {
        assertEquals(Duration(30), 30L.seconds())
        assertEquals(Duration(120), 2L.minutes())
        assertEquals(Duration(7200), 2L.hours())
        assertEquals(Duration(172800), 2L.days())
    }

    @Test
    fun `should create durations using extension functions for Int`() {
        assertEquals(Duration(30), 30.seconds())
        assertEquals(Duration(120), 2.minutes())
        assertEquals(Duration(7200), 2.hours())
        assertEquals(Duration(172800), 2.days())
    }

    @Test
    fun `should chain extension functions`() {
        val duration = 1.hours() + 30.minutes() + 45.seconds()
        assertEquals(Duration(5445), duration)
    }
}
