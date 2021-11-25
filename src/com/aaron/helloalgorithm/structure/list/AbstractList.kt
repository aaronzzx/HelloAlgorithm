package com.aaron.helloalgorithm.structure.list

/**
 * @author aaronzzxup@gmail.com
 * @since 2021/11/25
 */
abstract class AbstractList<E> : List<E> {

    override fun add(item: E): Boolean {
        return add(size, item)
    }

    override fun isEmpty(): Boolean {
        return size == 0
    }

    override fun contains(item: E): Boolean {
        return indexOf(item) >= 0
    }
}