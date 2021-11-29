package com.aaron.helloalgorithm.structure.tree

/**
 * @author aaronzzxup@gmail.com
 * @since 2021/11/29
 */
interface IBinarySearchTree<E> : IBinaryTree<E> {

    fun add(element: E)

    fun remove(element: E)

    fun predecessorOf(element: E): E?

    fun successorOf(element: E): E?
}