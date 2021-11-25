package com.aaron.helloalgorithm.structure.list

/**
 * @author aaronzzxup@gmail.com
 * @since 2021/11/25
 */
interface Deque<E> : Queue<E> {

    fun offerFirst(item: E): Boolean

    fun offerLast(item: E): Boolean

    fun pollFirst(): E

    fun pollLast(): E

    fun peekFirst(): E

    fun peekLast(): E

    fun pollFirstOrNull(): E?

    fun pollLastOrNull(): E?

    fun peekFirstOrNull(): E?

    fun peekLastOrNull(): E?
}