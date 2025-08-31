package io.github.ttkalcevic.duration

/**
 * Represents a duration in seconds with human-readable formatting capabilities.
 *
 * @param totalSeconds Total duration in seconds
 */
data class Duration(val totalSeconds: Long) {

    init {
        require(totalSeconds >= 0) {
            "Duration cannot be negative: $totalSeconds"
        }
    }

    /**
     * Number of complete days in this duration
     */
    val days: Long get() = totalSeconds / SECONDS_IN_DAY

    /**o
     * Remaining hours after extracting complete days
     */
    val hours: Long get() = (totalSeconds % SECONDS_IN_DAY) / SECONDS_IN_HOUR

    /**
     * Remaining minutes after extracting complete hours
     */
    val minutes: Long get() = (totalSeconds % SECONDS_IN_HOUR) / SECONDS_IN_MINUTE

    /**
     * Remaining seconds after extracting complete minutes
     */
    val seconds: Long get() = totalSeconds % SECONDS_IN_MINUTE

    /**
     * Total duration expressed in minutes (fractional)
     */
    val totalMinutes: Long get() = totalSeconds / SECONDS_IN_MINUTE

    /**
     * Total duration expressed in hours (fractional)
     */
    val totalHours: Long get() = totalSeconds / SECONDS_IN_HOUR

    /**
     * Total duration expressed in days (fractional)
     */
    val totalDays: Long get() = totalSeconds / SECONDS_IN_DAY

    /**
     * Converts duration to human-readable format
     *
     * @param format The format to use for output
     * @return Human-readable duration string
     */
    fun toHumanReadable(format: Format = Format.AUTO): String {
        return when (format) {
            Format.AUTO -> formatAuto()
            Format.COMPACT -> formatCompact()
            Format.COLON_SEPARATED -> formatColonSeparated()
            Format.VERBOSE -> formatVerbose()
            is Format.Custom -> formatCustom(format.pattern)
        }
    }

    private fun formatAuto(): String {
        return when {
            totalSeconds < SECONDS_IN_MINUTE -> "${seconds}sec"
            totalSeconds < SECONDS_IN_HOUR -> {
                if (seconds > 0) "${totalMinutes}min ${seconds}sec"
                else "${totalMinutes}min"
            }

            totalSeconds < SECONDS_IN_DAY -> {
                if (minutes > 0) "${totalHours}h ${minutes}min"
                else "${totalHours}h"
            }

            else -> {
                if (hours > 0) "${days}d ${hours}h"
                else "${days}d"
            }
        }
    }

    private fun formatCompact(): String {
        val parts = mutableListOf<String>()
        if (days > 0) parts.add("${days}d")
        if (hours > 0) parts.add("${hours}h")
        if (minutes > 0) parts.add("${minutes}min")
        if (seconds > 0 || parts.isEmpty()) parts.add("${seconds}sec")
        return parts.joinToString("")
    }

    private fun formatColonSeparated(): String {
        return when {
            days > 0 -> String.format("%d:%02d:%02d:%02d", days, hours, minutes, seconds)
            hours > 0 -> String.format("%d:%02d:%02d", hours, minutes, seconds)
            else -> String.format("%d:%02d", minutes, seconds)
        }
    }

    private fun formatVerbose(): String {
        val parts = mutableListOf<String>()
        if (days > 0) parts.add("${days}d")
        if (hours > 0) parts.add("${hours}h")
        if (minutes > 0) parts.add("${minutes}min")
        if (seconds > 0) parts.add("${seconds}sec")

        return when (parts.size) {
            0 -> "0sec"
            1 -> parts[0]
            else -> parts.joinToString(" ")
        }
    }

    private fun formatCustom(pattern: String): String {
        return pattern
            .replace("{d}", days.toString())
            .replace("{h}", hours.toString())
            .replace("{m}", minutes.toString())
            .replace("{s}", seconds.toString())
            .replace("{dd}", String.format("%02d", days))
            .replace("{hh}", String.format("%02d", hours))
            .replace("{mm}", String.format("%02d", minutes))
            .replace("{ss}", String.format("%02d", seconds))
    }

    // Arithmetic operations
    operator fun plus(other: Duration): Duration = Duration(totalSeconds + other.totalSeconds)
    operator fun minus(other: Duration): Duration = Duration(maxOf(0, totalSeconds - other.totalSeconds))
    operator fun times(multiplier: Int): Duration = Duration(totalSeconds * multiplier)
    operator fun times(multiplier: Long): Duration = Duration(totalSeconds * multiplier)
    operator fun div(divisor: Int): Duration = Duration(totalSeconds / divisor)
    operator fun div(divisor: Long): Duration = Duration(totalSeconds / divisor)

    // Comparison operations
    operator fun compareTo(other: Duration): Int = totalSeconds.compareTo(other.totalSeconds)

    // Utility methods
    fun isZero(): Boolean = totalSeconds == 0L
    fun isPositive(): Boolean = totalSeconds > 0L

    companion object {
        val ZERO = Duration(0)

        fun ofDays(days: Long): Duration = Duration(days * SECONDS_IN_DAY)
        fun ofHours(hours: Long): Duration = Duration(hours * SECONDS_IN_HOUR)
        fun ofMinutes(minutes: Long): Duration = Duration(minutes * SECONDS_IN_MINUTE)
        fun ofSeconds(seconds: Long): Duration = Duration(seconds)

        private const val SECONDS_IN_MINUTE = 60
        private const val SECONDS_IN_HOUR = 3600
        private const val SECONDS_IN_DAY = 86400
    }
}
