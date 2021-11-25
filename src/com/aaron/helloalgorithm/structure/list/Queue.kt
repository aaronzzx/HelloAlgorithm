package com.aaron.helloalgorithm.structure.list

/**
 * @author aaronzzxup@gmail.com
 * @since 2021/11/25
 */
interface Queue<E> {

    val size: Int

    fun offer(item: E): Boolean

    fun poll(): E

    fun peek(): E

    fun pollOrNull(): E?

    fun peekOrNull(): E?

    fun isEmpty(): Boolean
}