package com.aaron.helloalgorithm.structure.tree.ktx

import com.aaron.helloalgorithm.structure.tree.IBinaryTreeTraversal
import com.aaron.helloalgorithm.structure.tree.IVisitor

/**
 * @author aaronzzxup@gmail.com
 * @since 2021/11/23
 */

fun <E> IBinaryTreeTraversal<E>.toListByPreorder(predicate: ((E) -> Boolean)? = null): List<E> {
    val container = arrayListOf<E>()
    preorderTraversal(getVisitor(container, predicate))
    return container
}

fun <E> IBinaryTreeTraversal<E>.toListByInorder(predicate: ((E) -> Boolean)? = null): List<E> {
    val container = arrayListOf<E>()
    inorderTraversal(getVisitor(container, predicate))
    return container
}

fun <E> IBinaryTreeTraversal<E>.toListByPostorder(predicate: ((E) -> Boolean)? = null): List<E> {
    val container = arrayListOf<E>()
    postorderTraversal(getVisitor(container, predicate))
    return container
}

fun <E> IBinaryTreeTraversal<E>.toListByLevelOrder(predicate: ((E) -> Boolean)? = null): List<E> {
    val container = arrayListOf<E>()
    levelOrderTraversal(getVisitor(container, predicate))
    return container
}

private fun <E> getVisitor(container: MutableList<E>, predicate: ((E) -> Boolean)?): IVisitor<E> {
    return object : IVisitor<E>() {
        override fun visit(element: E): Boolean {
            container.add(element)
            return predicate?.invoke(element) ?: false
        }
    }
}