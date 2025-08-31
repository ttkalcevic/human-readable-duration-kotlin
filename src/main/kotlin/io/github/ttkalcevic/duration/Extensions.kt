package io.github.ttkalcevic.duration

/**
 * Extension functions for creating Duration instances
 */

// Long extensions
/** Returns a [Duration] representing this [Long] number of seconds. */
fun Long.seconds(): Duration = Duration(this)

/** Returns a [Duration] representing this [Long] number of minutes. */
fun Long.minutes(): Duration = Duration(this * 60)

/** Returns a [Duration] representing this [Long] number of hours. */
fun Long.hours(): Duration = Duration(this * 3600)

/** Returns a [Duration] representing this [Long] number of days. */
fun Long.days(): Duration = Duration(this * 86400)


// Int extensions
/** Returns a [Duration] representing this [Int] number of seconds. */
fun Int.seconds(): Duration = this.toLong().seconds()

/** Returns a [Duration] representing this [Int] number of minutes. */
fun Int.minutes(): Duration = this.toLong().minutes()

/** Returns a [Duration] representing this [Int] number of hours. */
fun Int.hours(): Duration = this.toLong().hours()

/** Returns a [Duration] representing this [Int] number of days. */
fun Int.days(): Duration = this.toLong().days()
