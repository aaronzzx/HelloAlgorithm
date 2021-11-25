package com.aaron.helloalgorithm.structure.list

import com.aaron.helloalgorithm.downUntil

/**
 * @author aaronzzxup@gmail.com
 * @since 2021/11/25
 */
class LinkedList<E> : AbstractList<E>() {

    private var first: Node<E>? = null

    private var last: Node<E>? = null

    private var _size = 0

    override val size: Int
        get() = _size

    override fun add(index: Int, item: E): Boolean {
        checkIndexForAdd(index)
        if (index == size) {
            linkLast(item)
        } else {
            linkBefore(item, node(index))
        }
        return true
    }

    private fun linkLast(item: E) {
        val l = last
        val newNode = Node(item, l, null)
        last = newNode
        if (l == null) {
            first = newNode
        } else {
            l.next = newNode
        }
        _size++
    }

    private fun linkBefore(item: E, node: Node<E>) {
        val prev = node.prev
        val newNode = Node(item, prev, node)
        node.prev = newNode
        if (prev == null) {
            first = newNode
        } else {
            prev.next = newNode
        }
        _size++
    }

    override fun remove(item: E): Boolean {
        val index = indexOf(item)
        if (index < 0) {
            return false
        }
        unlink(node(index))
        return true
    }

    override fun removeAt(index: Int): E {
        checkIndex(index)
        return unlink(node(index))
    }

    private fun unlink(node: Node<E>): E {
        val oldVal = node.item
        val prev = node.prev
        val next = node.next
        if (prev == null) {
            first = next
        } else {
            prev.next = next
            node.prev = null
        }
        if (next == null) {
            last = prev
        } else {
            next.prev = prev
            node.next = null
        }
        node.item = null
        _size--
        return oldVal as E
    }

    override fun set(index: Int, item: E): E {
        checkIndex(index)
        val node = node(index)
        val oldVal = node.item
        node.item = item
        return oldVal as E
    }

    private fun node(index: Int): Node<E> {
        checkIndex(index)
        var node: Node<E>?
        if (index < (size shr 1)) {
            node = first
            for (i in 0 until index) {
                node = node?.next
            }
            return node!!
        } else {
            node = last
            for (i in (size - 1) downUntil index) {
                node = node?.prev
            }
            return node!!
        }
    }

    override fun get(index: Int): E {
        checkIndex(index)
        var node: Node<E>?
        if (index < (size shr 1)) {
            node = first
            for (i in 0 until index) {
                node = node?.next
            }
            return node!!.item as E
        } else {
            node = last
            for (i in (size - 1) downUntil index) {
                node = node?.prev
            }
            return node!!.item as E
        }
    }

    private fun checkIndexForAdd(index: Int) {
        if (index < 0 || index > size) {
            throw IndexOutOfBoundsException(errorMsg(index))
        }
    }

    private fun checkIndex(index: Int) {
        if (index < 0 || index >= size) {
            throw IndexOutOfBoundsException(errorMsg(index))
        }
    }

    private fun errorMsg(index: Int): String {
        return "Index: $index, Size: $size"
    }

    override fun indexOf(item: E): Int {
        var node = first
        for (i in 0 until size) {
            if (node?.item == item) {
                return i
            }
            node = node?.next
        }
        return -1
    }

    override fun clear() {
        first = null
        last = null
        _size = 0
    }

    override fun toString(): String {
        var node: Node<E>? = first ?: return "[]"
        val sb = StringBuilder().also {
            it.append("[")
        }
        while (true) {
            sb.append(node?.item)
            if (node?.next == null) {
                return sb.append("]").toString()
            }
            node = node.next
            sb.append(", ")
        }
    }

    private class Node<E>(
        var item: E?,
        var prev: Node<E>?,
        var next: Node<E>?
    )
}