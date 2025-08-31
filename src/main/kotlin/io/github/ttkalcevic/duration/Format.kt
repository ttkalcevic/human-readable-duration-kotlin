package io.github.ttkalcevic.duration

/**
 * Formatting options for duration display
 */
sealed class Format {
    /**
     * Automatically chooses the most appropriate format based on duration length
     */
    data object AUTO : Format()

    /**
     * Compact format without spaces: "1h30m45s"
     */
    data object COMPACT : Format()

    /**
     * Colon-separated format: "1:30:45"
     */
    @Suppress("ClassName")
    data object COLON_SEPARATED : Format()

    /**
     * Verbose format with units: "1d 2h 30min 45sec"
     */
    data object VERBOSE : Format()

    /**
     * Custom format using pattern placeholders
     *
     * Available placeholders:
     * - {d}, {h}, {m}, {s} - values without padding
     * - {dd}, {hh}, {mm}, {ss} - zero-padded values
     *
     * @param pattern The custom pattern string
     */
    data class Custom(val pattern: String) : Format()
}