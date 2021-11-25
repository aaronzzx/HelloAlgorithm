package com.aaron.helloalgorithm.structure.tree

/**
 * @author aaronzzxup@gmail.com
 * @since 2021/11/18
 */
abstract class IVisitor<E> {

    internal var stop = false

    abstract fun visit(element: E): Boolean
}