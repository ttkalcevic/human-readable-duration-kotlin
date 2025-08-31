# Human Readable Duration

A Kotlin library for converting durations to human-readable formats.

## Features

- 🚀 **Simple API** - Easy to use extension functions
- ⚡ **Multiple formats** - AUTO, COMPACT, COLON_SEPARATED, VERBOSE, and Custom
- 🔧 **Builder pattern** - Advanced formatting options
- 📝 **Parsing support** - Convert strings back to Duration objects
- 🎯 **Type safe** - Full Kotlin type safety
- 🧪 **Well tested** - Comprehensive test suite

## Installation

### Gradle (Kotlin DSL)
```kotlin
dependencies {
    implementation("io.github.ttkalcevic:human-readable-duration:1.0.0")
}
```

### Gradle (Groovy)
```groovy
dependencies {
    implementation 'io.github.ttkalcevic:human-readable-duration:1.0.0'
}
```

### Maven
```xml
<dependency>
    <groupId>io.github.ttkalcevic</groupId>
    <artifactId>human-readable-duration</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Quick Start

```kotlin
import io.github.ttkalcevic.duration.*

// Create durations using extension functions
val duration = 3665.seconds() // 1 hour, 1 minute, 5 seconds

// Format in different ways
println(duration.toHumanReadable())                    // "1h 1min" (AUTO format)
println(duration.toHumanReadable(Format.COMPACT))      // "1h1min5sec"
println(duration.toHumanReadable(Format.COLON_SEPARATED)) // "1:01:05"
println(duration.toHumanReadable(Format.VERBOSE))      // "1h 1min 5sec"

// Custom formatting
val formatted = duration.format()
    .separator(" : ")
    .showSeconds(false)
    .build()
println(formatted) // "1 : 01"

// Parse strings back to Duration
val parsed = DurationParser.parse("1h 30min 45sec")
println(parsed?.totalSeconds) // 5445
```

## Usage Examples

### Creating Durations

```kotlin
// Extension functions
val d1 = 30.seconds()
val d2 = 5.minutes()
val d3 = 2.hours()
val d4 = 1.days()

// Companion object methods
val d5 = Duration.ofSeconds(30)
val d6 = Duration.ofMinutes(5)
val d7 = Duration.ofHours(2)
val d8 = Duration.ofDays(1)

// Direct constructor
val d9 = Duration(3665) // seconds

// Arithmetic operations
val total = 1.hours() + 30.minutes() + 45.seconds()
```

### Formatting Options

#### AUTO Format (Default)
Automatically chooses the most appropriate format:
```kotlin
30.seconds().toHumanReadable()     // "30sec"
150.seconds().toHumanReadable()    // "2min 30sec"
5400.seconds().toHumanReadable()   // "1h 30min"
93600.seconds().toHumanReadable()  // "1d 2h"
```

#### COMPACT Format
No spaces between units:
```kotlin
3665.seconds().toHumanReadable(Format.COMPACT)  // "1h1min5sec"
```

#### COLON_SEPARATED Format
Traditional time format:
```kotlin
3665.seconds().toHumanReadable(Format.COLON_SEPARATED)  // "1:01:05"
95445.seconds().toHumanReadable(Format.COLON_SEPARATED) // "1:02:30:45"
```

#### VERBOSE Format
All units with spaces:
```kotlin
3665.seconds().toHumanReadable(Format.VERBOSE)  // "1h 1min 5sec"
```

#### Custom Format
Use pattern placeholders:
```kotlin
val duration = 3665.seconds()

// Basic placeholders
duration.toHumanReadable(Format.Custom("{h}h {m}m {s}s"))  // "1h 1m 5s"

// Zero-padded placeholders
duration.toHumanReadable(Format.Custom("{hh}:{mm}:{ss}"))  // "01:01:05"

// Free text
duration.toHumanReadable(Format.Custom("Duration: {h} hours and {m} minutes"))
// "Duration: 1 hours and 1 minutes"
```

Available placeholders:
- `{d}`, `{h}`, `{m}`, `{s}` - values without padding
- `{dd}`, `{hh}`, `{mm}`, `{ss}` - zero-padded values

### Advanced Formatting with Builder

```kotlin
val duration = 3665.seconds()

val formatted = duration.format()
    .showDays(false)           // Hide days
    .showSeconds(false)        // Hide seconds
    .separator(" : ")          // Custom separator
    .padWithZeros(true)        // Zero padding
    .minDisplayUnit(DurationFormatter.TimeUnit.MINUTES) // Minimum unit to show
    .build()

println(formatted) // "1 : 01"
```

### Parsing Strings

The parser supports multiple formats:

```kotlin
// Colon-separated
DurationParser.parse("1:30")        // 1min 30sec
DurationParser.parse("1:30:45")     // 1h 30min 45sec
DurationParser.parse("2:01:30:45")  // 2d 1h 30min 45sec

// Compact format
DurationParser.parse("30sec")       // 30 seconds
DurationParser.parse("2min30sec")   // 2min 30sec
DurationParser.parse("1h30min")     // 1h 30min
DurationParser.parse("1d2h30min45sec") // 1d 2h 30min 45sec

// With spaces
DurationParser.parse("1h 30min 45sec")  // 1h 30min 45sec
DurationParser.parse("2 h")             // 2 hours

// Case insensitive
DurationParser.parse("1H30MIN")     // 1h 30min
```

### Arithmetic Operations

```kotlin
val d1 = 1.hours()
val d2 = 30.minutes()

val sum = d1 + d2           // 1h 30min
val difference = d1 - d2    // 30min
val multiplied = d1 * 2     // 2h
val divided = d1 / 2        // 30min

// Comparison
println(d1 > d2)            // true
println(d1 == 60.minutes()) // true
```

### Utility Methods

```kotlin
val duration = 3665.seconds()

// Properties
println(duration.days)         // 0
println(duration.hours)        // 1
println(duration.minutes)      // 1
println(duration.seconds)      // 5

// Total values
println(duration.totalMinutes) // 61
println(duration.totalHours)   // 1
println(duration.totalDays)    // 0

// Checks
println(duration.isZero())     // false
println(duration.isPositive()) // true
```

## Requirements

- Kotlin 1.9.24+
- JVM 11+

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## Running Tests

```bash
./gradlew test
```

## Building

```bash
./gradlew build
```

## Publishing

```bash
./gradlew publishToSonatype
```

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---