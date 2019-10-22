fun bound(value: Int, max: Int): Int {
    return value.coerceAtLeast(0).coerceAtMost(max - 1)
}