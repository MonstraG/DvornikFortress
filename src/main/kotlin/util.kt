/**
 * Returns value bound by 0 and max value
 */
fun bound(value: Int, max: Int): Int {
    return value.coerceAtLeast(0).coerceAtMost(max - 1)
}

/**
 * Returns next value in enum
 */
inline fun <reified T: Enum<T>> T.next(): T {
    val values = enumValues<T>()
    val nextOrdinal = (ordinal + 1) % values.size
    return values[nextOrdinal]
}