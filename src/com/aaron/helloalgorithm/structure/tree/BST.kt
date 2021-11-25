package com.aaron.helloalgorithm.structure.tree

import com.aaron.helloalgorithm.structure.tree.printer.BinaryTreeInfo
import java.util.*
import kotlin.math.max

/**
 * @author aaronzzxup@gmail.com
 * @since 2021/11/23
 */
class BST<E> : IBinaryTree<E>, IBinaryTreeTraversal<E>, BinaryTreeInfo {

    private var root: TreeNode<E>? = null

    private var size = 0

    private var height = 0

    private var isHeightDirty = false

    private var comparator: Comparator<E>? = null

    constructor() : this(null)

    constructor(comparator: Comparator<E>?) {
        this.comparator = comparator
    }

    override fun add(element: E) {
        if (root == null) {
            root = TreeNode(element, null)
            size++
            return
        }
        val root = root ?: return
        var node: TreeNode<E>? = root
        var parent = root
        var compare = 0
        while (node != null) {
            parent = node
            compare = compare(element, node.element)
            when (compare) {
                -1 -> {
                    node = node.left
                }
                1 -> {
                    node = node.right
                }
                else -> {
                    node.element = element
                    return
                }
            }
        }
        val newNode = TreeNode(element, parent)
        when (compare) {
            -1 -> {
                parent.left = newNode
            }
            else -> {
                parent.right = newNode
            }
        }
        size++
        isHeightDirty = true
    }

    private fun compare(e1: E, e2: E): Int {
        if (comparator != null) {
            return comparator!!.compare(e1, e2)
        }
        return (e1 as Comparable<E>).compareTo(e2)
    }

    override fun remove(element: E) {
        remove(node(element))
    }

    private fun remove(node: TreeNode<E>?) {
        var _node = node ?: return
        if (_node.hasTwoChildren) {
            val succ = successor(_node)!!
            _node.element = succ.element
            _node = succ
        }
        val replacement = when (_node.left) {
            null -> _node.right
            else -> _node.left
        }
        if (replacement != null) {
            replacement.parent = _node.parent
            if (_node.parent == null) {
                root = replacement
            } else {
                if (_node == _node.parent?.left) {
                    _node.parent?.left = replacement
                } else {
                    _node.parent?.right = replacement
                }
            }
        } else if (_node.parent == null) {
            root = null
        } else {
            if (_node == _node.parent?.left) {
                _node.parent?.left = null
            } else {
                _node.parent?.right = null
            }
        }
        size--
        isHeightDirty = true
    }

    private fun predecessor(node: TreeNode<E>?): TreeNode<E>? {
        node ?: return null
        var node1 = node
        var pred = node1.left
        if (pred != null) {
            while (pred?.right != null) {
                pred = pred.right
            }
            return pred
        }
        while (node1?.parent != null && node1 == node1.parent?.left) {
            node1 = node1.parent
        }
        return node1?.parent
    }

    private fun successor(node: TreeNode<E>?): TreeNode<E>? {
        node ?: return null
        var node1 = node
        var pred = node1.right
        if (pred != null) {
            while (pred?.left != null) {
                pred = pred.left
            }
            return pred
        }
        while (node1?.parent != null && node1 == node1.parent?.right) {
            node1 = node1.parent
        }
        return node1?.parent
    }

    override fun contains(element: E): Boolean {
        return node(element) != null
    }

    private fun node(element: E): TreeNode<E>? {
        var node = root
        while (node != null) {
            val cmp = compare(element, node.element)
            if (cmp == 0) {
                return node
            }
            if (cmp < 0) {
                node = node.left
            } else {
                node = node.right
            }
        }
        return null
    }

    override fun isEmpty(): Boolean {
        return size == 0
    }

    override fun clear() {
        root = null
        size = 0
    }

    override fun size(): Int {
        return size
    }

    override fun height(): Int {
        if (isHeightDirty) {
            isHeightDirty = false
            height = heightByLevelTraversal(root)
        }
        return height
    }

    private fun heightByRecursive(node: TreeNode<E>?): Int {
        node ?: return 0
        return 1 + max(heightByRecursive(node.left), heightByRecursive(node.right))
    }

    private fun heightByLevelTraversal(node: TreeNode<E>?): Int {
        node ?: return 0
        var height = 0
        var levelSize = 1
        val queue: Queue<TreeNode<E>> = LinkedList<TreeNode<E>>().also {
            it.offer(node)
        }
        while (queue.isNotEmpty()) {
            val poll = queue.poll()
            levelSize--
            if (poll.hasLeft) {
                queue.offer(poll.left)
            }
            if (poll.hasRight) {
                queue.offer(poll.right)
            }
            if (levelSize == 0) {
                levelSize = queue.size
                height++
            }
        }
        return height
    }

    override fun isFull(): Boolean {
        val root = root ?: return false
        val queue: Queue<TreeNode<E>> = LinkedList<TreeNode<E>>().also {
            it.offer(root)
        }
        while (queue.isNotEmpty()) {
            val poll = queue.poll()
            if (poll.hasLeftOnly) {
                return false
            }
            if (poll.hasRightOnly) {
                return false
            }
            if (poll.hasLeft) {
                queue.offer(poll.left)
            }
            if (poll.hasRight) {
                queue.offer(poll.right)
            }
        }
        return true
    }

    override fun isPerfect(): Boolean {
        root ?: return false
        val height = height()
        if (height == 0) return false
        return size == (2 shl height - 1) - 1
    }

    override fun isComplete(): Boolean {
        val root = root ?: return false
        val queue: Queue<TreeNode<E>> = LinkedList<TreeNode<E>>().also {
            it.offer(root)
        }
        var isLeafFound = false
        while (queue.isNotEmpty()) {
            val poll = queue.poll()
            if (isLeafFound && !poll.isLeaf) {
                return false
            }
            if (poll.hasLeft) {
                queue.offer(poll.left)
            } else if (poll.hasRight) {
                return false
            }
            if (poll.hasRight) {
                queue.offer(poll.right)
            } else {
                isLeafFound = true
            }
        }
        return true
    }

    override fun preorderTraversal(visitor: IVisitor<E>) {
        var node = root ?: return
        val stack: Deque<TreeNode<E>> = LinkedList<TreeNode<E>>().also {
            it.push(node)
        }
        while (stack.isNotEmpty()) {
            node = stack.pop()
            visitor.stop = visitor.visit(node.element)
            if (visitor.stop) return
            if (node.hasRight) {
                stack.push(node.right)
            }
            if (node.hasLeft) {
                stack.push(node.left)
            }
        }
    }

    override fun inorderTraversal(visitor: IVisitor<E>) {
        val root = root ?: return
        var node: TreeNode<E>? = root
        val stack: Deque<TreeNode<E>> = LinkedList<TreeNode<E>>()
        while (node != null || stack.isNotEmpty()) {
            if (visitor.stop) return
            if (node != null) {
                stack.push(node)
                node = node.left
            } else {
                node = stack.pop()
                visitor.stop = visitor.visit(node.element)
                node = node.right
            }
        }
    }

    override fun postorderTraversal(visitor: IVisitor<E>) {
        val root = root ?: return
        var node: TreeNode<E>? = root
        val inStack: Deque<TreeNode<E>> = LinkedList<TreeNode<E>>()
        val outStack: Deque<TreeNode<E>> = LinkedList<TreeNode<E>>()
        inStack.push(node)
        while (inStack.isNotEmpty()) {
            node = inStack.pop()
            outStack.push(node)
            if (node.hasLeft) {
                inStack.push(node.left)
            }
            if (node.hasRight) {
                inStack.push(node.right)
            }
        }
        while (outStack.isNotEmpty()) {
            if (visitor.stop) return
            visitor.stop = visitor.visit(outStack.pop().element)
        }
    }

    override fun levelOrderTraversal(visitor: IVisitor<E>) {
        val root = root ?: return
        val queue: Queue<TreeNode<E>> = LinkedList<TreeNode<E>>().also {
            it.offer(root)
        }
        while (queue.isNotEmpty()) {
            if (visitor.stop) return
            val node = queue.poll()
            visitor.stop = visitor.visit(node.element)
            if (node.hasLeft) {
                queue.offer(node.left)
            }
            if (node.hasRight) {
                queue.offer(node.right)
            }
        }
    }

    override fun root(): Any? {
        return root
    }

    override fun left(node: Any?): Any? {
        return (node as? TreeNode<*>)?.left
    }

    override fun right(node: Any?): Any? {
        return (node as? TreeNode<*>)?.right
    }

    override fun string(node: Any?): Any? {
        return (node as? TreeNode<*>)?.element?.toString()
    }

    private class TreeNode<E>(
        var element: E,
        var parent: TreeNode<E>?
    ) {

        var left: TreeNode<E>? = null
        var right: TreeNode<E>? = null

        val isLeaf: Boolean
            get() = left == null && right == null

        val hasTwoChildren: Boolean
            get() = left != null && right != null

        val hasLeft: Boolean
            get() = left != null

        val hasRight: Boolean
            get() = right != null

        val hasLeftOnly: Boolean
            get() = left != null && right == null

        val hasRightOnly: Boolean
            get() = left == null && right != null
    }
}