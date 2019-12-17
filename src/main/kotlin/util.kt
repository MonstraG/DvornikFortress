/**
 * Returns value bound by min (included) and max (not included)
 */
fun Int.bound(min: Int, max: Int): Int {
    return this.coerceAtLeast(min).coerceAtMost(max - 1)
}

/**
 * Returns next value in enum
 */
inline fun <reified T: Enum<T>> T.next(): T {
    val values = enumValues<T>()
    val nextOrdinal = (ordinal + 1) % values.size
    return values[nextOrdinal]
}