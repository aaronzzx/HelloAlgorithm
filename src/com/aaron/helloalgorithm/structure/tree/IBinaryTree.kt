package com.aaron.helloalgorithm.structure.tree

/**
 * @author aaronzzxup@gmail.com
 * @since 2021/11/18
 */
interface IBinaryTree<E> {

    fun add(element: E)

    fun remove(element: E)

    fun contains(element: E): Boolean

    fun isEmpty(): Boolean

    fun clear()

    fun size(): Int

    fun height(): Int

    fun isFull(): Boolean

    fun isPerfect(): Boolean

    fun isComplete(): Boolean
}