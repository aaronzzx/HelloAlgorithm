package com.aaron.helloalgorithm.structure.list

/**
 * @author aaronzzxup@gmail.com
 * @since 2021/11/25
 */
interface Stack<E> {

    val size: Int

    fun push(item: E): Boolean

    fun pop(): E

    fun top(): E

    fun popOrNull(): E?

    fun topOrNull(): E?

    fun isEmpty(): Boolean
}