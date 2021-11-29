package com.aaron.helloalgorithm.structure.tree

import com.aaron.helloalgorithm.structure.tree.printer.BinaryTreeInfo
import java.util.*
import kotlin.math.max

/**
 * @author aaronzzxup@gmail.com
 * @since 2021/11/29
 */
class BST<E> : IBinarySearchTree<E>, IBinaryTreeTraversal<E>, BinaryTreeInfo {

    private var root: TreeNode<E>? = null

    private var size = 0

    private var comparator: Comparator<E>? = null

    constructor() : this(null)

    constructor(comparator: Comparator<E>?) {
        this.comparator = comparator
    }

    override fun add(element: E) {
        var node: TreeNode<E>? = root
        // 根节点为空，创建根节点
        if (node == null) {
            root = TreeNode(element, null)
            size++
            return
        }
        var parent = node
        var compare = 0
        // 遍历找到待插入节点的父节点，如果元素相等则覆盖即可
        while (node != null) {
            parent = node
            compare = compare(element, node.item as E)
            when {
                // 往左子树查找
                compare < 0 -> node = node.left
                // 往右子树查找
                compare > 0 -> node = node.right
                else -> {
                    node.item = element
                    return
                }
            }
        }
        val newNode = TreeNode(element, parent)
        if (compare < 0) {
            // 待插入节点应插入到左节点
            parent?.left = newNode
        } else {
            // 待插入节点应插入到右节点
            parent?.right = newNode
        }
        size++
    }

    override fun remove(element: E) {
        remove(node(element))
    }

    private fun remove(node: TreeNode<E>?) {
        var _node = node ?: return
        // 度为 2
        if (_node.hasTwoChildren) {
            // 找到后继，用前驱也行
            val succ = successor(_node)!!
            // 直接将后继/前驱的值覆盖上去
            _node.item = succ.item
            // 然后将后继/前驱的引用赋值 _node ，往下走统一处理
            _node = succ
        }
        // 以下处理度为 0 或 1 的情况
        val replacement = when (_node.hasLeftOnly) {
            true -> _node.left
            else -> _node.right
        }
        if (replacement != null) {
            // 度为 1 ，首先将待删除的节点的父节点赋值给替代节点
            replacement.parent = _node.parent
            if (_node.hasParent) {
                // 如果待删除节点有父节点，需要判断待删除节点是
                // 父节点的哪个子节点，然后将替代节点赋值给父节点的子节点
                if (_node == _node.parent?.left) {
                    _node.parent?.left = replacement
                } else {
                    _node.parent?.right = replacement
                }
            } else {
                // 待删除节点没有父节点，那它就是根节点，直接将替代节点赋值给根节点
                root = replacement
            }
        } else if (!_node.hasParent) {
            // 删除的是根节点
            root = null
        } else {
            // 度为 0 ，直接将待删除节点的相应子节点置空
            if (_node == _node.parent?.left) {
                _node.parent?.left = null
            } else {
                _node.parent?.right = null
            }
        }
        size--
    }

    override fun predecessorOf(element: E): E? {
        val node = node(element) ?: return null
        return predecessor(node)?.item
    }

    override fun successorOf(element: E): E? {
        val node = node(element) ?: return null
        return successor(node)?.item
    }

    private fun predecessor(node: TreeNode<E>): TreeNode<E>? {
        var _node: TreeNode<E>? = node
        // 有左子树，则从左子树开始一直向右查找，最终的节点就是前驱
        // 如果左子树没有右节点，则左子树就是前驱
        if (_node?.hasLeft == true) {
            _node = _node.left
            while (_node?.hasRight == true) {
                _node = _node.right
            }
            return _node
        }
        // 来到这里表示 node 节点没有左子树，因此需要向上查找
        // 前提是待查找前驱节点有父节点并且待查找前驱节点是父节点的左节点
        while (_node?.hasParent == true && _node == _node.parent?.left) {
            _node = _node.parent
        }
        return _node?.parent
    }

    private fun successor(node: TreeNode<E>): TreeNode<E>? {
        // 情况与查找前驱相反
        var _node: TreeNode<E>? = node
        if (_node?.hasRight == true) {
            _node = _node.right
            while (_node?.hasLeft == true) {
                _node = _node.left
            }
            return _node
        }
        // 来到这里表示 node 节点没有右子树，因此需要向上查找
        while (_node?.hasParent == true && _node == _node.parent?.right) {
            _node = _node.parent
        }
        return _node?.parent
    }

    override fun contains(element: E): Boolean {
        return node(element) != null
    }

    private fun node(element: E): TreeNode<E>? {
        var node: TreeNode<E>? = root
        while (node != null) {
            if (node.item == element) {
                return node
            }
            if (compare(element, node.item as E) < 0) {
                // 在左子树
                node = node.left
            } else {
                // 在右子树
                node = node.right
            }
        }
        return null
    }

    private fun compare(e1: E, e2: E): Int {
        val com = comparator
        if (com != null) {
            return com.compare(e1, e2)
        }
        return (e1 as Comparable<E>).compareTo(e2)
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
//        return getHeightByRecursive(root)
        return getHeightByTraversal(root)
    }

    private fun getHeightByRecursive(node: TreeNode<E>?): Int {
        node ?: return 0
        return 1 + max(getHeightByRecursive(node.left), getHeightByRecursive(node.right))
    }

    private fun getHeightByTraversal(node: TreeNode<E>?): Int {
        node ?: return 0
        val queue = LinkedList<TreeNode<E>>().also {
            it.offer(node)
        }
        var height = 0
        var levelSize = 1
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
        val node = root ?: return false
        val queue = LinkedList<TreeNode<E>>().also {
            it.offer(node)
        }
        while (queue.isNotEmpty()) {
            val poll = queue.poll()
            if (poll.hasLeftOnly || poll.hasRightOnly) {
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
        val height = height()
        if (height == 0) return false
        return (2 shl (height - 1)) - 1 == size
    }

    override fun isComplete(): Boolean {
        val node = root ?: return false
        val queue = LinkedList<TreeNode<E>>().also {
            it.offer(node)
        }
        var foundLeaf = false
        while (queue.isNotEmpty()) {
            val poll = queue.poll()
            if (foundLeaf && !poll.isLeaf) {
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
                foundLeaf = true
            }
        }
        return true
    }

    override fun preorderTraversal(visitor: IVisitor<E>) {
        val node = root ?: return
        val stack = LinkedList<TreeNode<E>>().also {
            it.push(node)
        }
        while (stack.isNotEmpty()) {
            val pop = stack.pop()
            visitor.stop = visitor.visit(pop.item as E)
            if (visitor.stop) return
            if (pop.hasRight) {
                stack.push(pop.right)
            }
            if (pop.hasLeft) {
                stack.push(pop.left)
            }
        }
    }

    override fun inorderTraversal(visitor: IVisitor<E>) {
        var node: TreeNode<E>? = root ?: return
        val stack = LinkedList<TreeNode<E>>()
        while (node != null || stack.isNotEmpty()) {
            if (visitor.stop) return
            if (node != null) {
                stack.push(node)
                node = node.left
            } else {
                node = stack.pop()
                visitor.stop = visitor.visit(node.item as E)
                if (visitor.stop) return
                node = node?.right
            }
        }
    }

    override fun postorderTraversal(visitor: IVisitor<E>) {
        val node = root ?: return
        val inStack = LinkedList<TreeNode<E>>().also {
            it.push(node)
        }
        val outStack = LinkedList<TreeNode<E>>()
        while (inStack.isNotEmpty()) {
            val pop = inStack.pop()
            outStack.push(pop)
            if (pop.hasLeft) {
                inStack.push(pop.left)
            }
            if (pop.hasRight) {
                inStack.push(pop.right)
            }
        }
        while (outStack.isNotEmpty()) {
            if (visitor.stop) return
            visitor.stop = visitor.visit(outStack.pop().item as E)
        }
    }

    override fun levelOrderTraversal(visitor: IVisitor<E>) {
        val node = root ?: return
        val queue = LinkedList<TreeNode<E>>().also {
            it.offer(node)
        }
        while (queue.isNotEmpty()) {
            if (visitor.stop) return
            val poll = queue.poll()
            visitor.stop = visitor.visit(poll.item as E)
            if (visitor.stop) return
            if (poll.hasLeft) {
                queue.offer(poll.left)
            }
            if (poll.hasRight) {
                queue.offer(poll.right)
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
        return (node as? TreeNode<*>)?.item
    }

    private class TreeNode<E>(var item: E?, var parent: TreeNode<E>?) {

        var left: TreeNode<E>? = null
        var right: TreeNode<E>? = null

        val hasParent: Boolean
            get() = parent != null

        val hasTwoChildren: Boolean
            get() = left != null && right != null

        val isLeaf: Boolean
            get() = left == null && right == null

        val hasLeft: Boolean
            get() = left != null

        val hasLeftOnly: Boolean
            get() = hasLeft && right == null

        val hasRight: Boolean
            get() = right != null

        val hasRightOnly: Boolean
            get() = !hasLeft && hasRight
    }
}