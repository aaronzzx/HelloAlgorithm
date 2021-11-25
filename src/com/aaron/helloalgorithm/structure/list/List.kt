package com.aaron.helloalgorithm.structure.list

/**
 * @author aaronzzxup@gmail.com
 * @since 2021/11/25
 */
interface List<E> {

    val size: Int

    fun add(item: E): Boolean

    fun add(index: Int, item: E): Boolean

    fun remove(item: E): Boolean

    fun removeAt(index: Int): E

    fun set(index: Int, item: E): E

    fun get(index: Int): E

    fun getFirst(): E

    fun getLast(): E

    fun isEmpty(): Boolean

    fun indexOf(item: E): Int

    fun contains(item: E): Boolean

    fun clear()
}