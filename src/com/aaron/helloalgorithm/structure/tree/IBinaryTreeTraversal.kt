package com.aaron.helloalgorithm.structure.tree

/**
 * @author aaronzzxup@gmail.com
 * @since 2021/11/18
 */
interface IBinaryTreeTraversal<E> {

    fun preorderTraversal(visitor: IVisitor<E>)

    fun inorderTraversal(visitor: IVisitor<E>)

    fun postorderTraversal(visitor: IVisitor<E>)

    fun levelOrderTraversal(visitor: IVisitor<E>)
}