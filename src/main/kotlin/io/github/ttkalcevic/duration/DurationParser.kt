package io.github.ttkalcevic.duration

/**
 * Parser for converting string representations back to Duration objects
 */
object DurationParser {

    /**
     * Attempts to parse a duration string in various formats
     *
     * Supported formats:
     * - Colon separated: "1:30", "1:30:45", "2:1:30:45"
     * - Compact: "1h30m45s", "90m", "45s"
     * - Mixed: "1 hour 30 minutes"
     *
     * @param input The string to parse
     * @return Duration object or null if parsing failed
     */
    fun parse(input: String): Duration? {
        val trimmed = input.trim()
        if (trimmed.isEmpty()) return null

        return tryParseColon(trimmed)
            ?: tryParseCompact(trimmed)
            ?: tryParseVerbose(trimmed)
    }

    private fun tryParseColon(input: String): Duration? {
        if (!input.contains(":")) return null

        val parts = input.split(":").mapNotNull { it.toLongOrNull() }
        if (parts.isEmpty()) return null

        return when (parts.size) {
            2 -> Duration(parts[0] * 60 + parts[1]) // mm:ss
            3 -> Duration(parts[0] * 3600 + parts[1] * 60 + parts[2]) // hh:mm:ss
            4 -> Duration(parts[0] * 86400 + parts[1] * 3600 + parts[2] * 60 + parts[3]) // dd:hh:mm:ss
            else -> null
        }
    }

    private fun tryParseCompact(input: String): Duration? {
        val regex = Regex("""(\d+)\s*([dhms])""", RegexOption.IGNORE_CASE)
        val matches = regex.findAll(input.lowercase())
        var totalSeconds = 0L
        var hasMatches = false

        for (match in matches) {
            hasMatches = true
            val value = match.groupValues[1].toLongOrNull() ?: return null
            val unit = match.groupValues[2]

            totalSeconds += when (unit) {
                "d" -> value * 86400
                "h" -> value * 3600
                "m" -> value * 60
                "s" -> value
                else -> return null
            }
        }

        return if (hasMatches && totalSeconds >= 0) Duration(totalSeconds) else null
    }

    private fun tryParseVerbose(input: String): Duration? {
        val patterns = mapOf(
            Regex("""(\d+)\s*days?""", RegexOption.IGNORE_CASE) to 86400L,
            Regex("""(\d+)\s*hours?""", RegexOption.IGNORE_CASE) to 3600L,
            Regex("""(\d+)\s*minutes?""", RegexOption.IGNORE_CASE) to 60L,
            Regex("""(\d+)\s*seconds?""", RegexOption.IGNORE_CASE) to 1L
        )

        var totalSeconds = 0L
        var hasMatches = false

        for ((pattern, multiplier) in patterns) {
            val match = pattern.find(input)
            if (match != null) {
                hasMatches = true
                val value = match.groupValues[1].toLongOrNull() ?: return null
                totalSeconds += value * multiplier
            }
        }

        return if (hasMatches) Duration(totalSeconds) else null
    }
}
