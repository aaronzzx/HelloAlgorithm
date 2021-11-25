package com.aaron.helloalgorithm

/**
 * @author aaronzzxup@gmail.com
 * @since 2021/11/24
 */

infix fun Byte.downUntil(to: Byte): IntProgression {
    return IntProgression.fromClosedRange(this.toInt(), to + 1, -1)
}

infix fun Short.downUntil(to: Short): IntProgression {
    return IntProgression.fromClosedRange(this.toInt(), to + 1, -1)
}

infix fun Char.downUntil(to: Char): CharProgression {
    return CharProgression.fromClosedRange(this, to + 1, -1)
}

infix fun Int.downUntil(to: Int): IntProgression {
    return IntProgression.fromClosedRange(this, to + 1, -1)
}

infix fun Long.downUntil(to: Long): LongProgression {
    return LongProgression.fromClosedRange(this, to + 1, -1)
}