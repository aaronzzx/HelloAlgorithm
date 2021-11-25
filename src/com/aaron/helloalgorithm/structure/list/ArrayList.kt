package com.aaron.helloalgorithm.structure.list

import kotlin.math.max

/**
 * @author aaronzzxup@gmail.com
 * @since 2021/11/25
 */
class ArrayList<E> : AbstractList<E> {

    companion object {
        private const val DEF_CAPACITY = 8
    }

    private var items: Array<E?>

    private var _size = 0

    override val size: Int
        get() = _size

    constructor() : this(DEF_CAPACITY)

    constructor(capacity: Int) {
        val safeCapacity = when {
            capacity <= 0 -> DEF_CAPACITY
            else -> capacity
        }
        items = arrayOfNulls<Any>(safeCapacity) as Array<E?>
    }

    override fun add(index: Int, item: E): Boolean {
        checkIndexForAdd(index)
        ensureCapacity(size + 1)
        val items = items
        val size = size
        if (index == size) {
            items[size] = item
        } else {
            System.arraycopy(items, index, items, index + 1, size)
            items[index] = item
        }
        _size++
        return true
    }

    private fun ensureCapacity(capacity: Int) {
        val items = items
        if (capacity <= items.size) {
            return
        }
        val newCapacity = max(capacity, capacity + (capacity shr 1))
        this.items = items.copyOf(newCapacity)
    }

    override fun remove(item: E): Boolean {
        val index = indexOf(item)
        if (index < 0) {
            return false
        }
        removeAt(index)
        return true
    }

    override fun removeAt(index: Int): E {
        checkIndex(index)
        val items = items
        val oldVal = items[index]
        val needRemoved = size - index - 1
        if (needRemoved != 0) {
            System.arraycopy(items, index + 1, items, index, needRemoved)
        }
        items[--_size] = null
        return oldVal as E
    }

    override fun set(index: Int, item: E): E {
        checkIndex(index)
        val oldVal = items[index]
        items[index] = item
        return oldVal as E
    }

    override fun get(index: Int): E {
        checkIndex(index)
        return items[index] as E
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
        for (i in items.indices) {
            if (items[i] == item) {
                return i
            }
        }
        return -1
    }

    override fun clear() {
        for (i in items.indices) {
            items[i] = null
        }
        _size = 0
    }

    fun trimToSize() {
        items = items.copyOf(0)
    }

    override fun toString(): String {
        return items.contentToString()
    }
}