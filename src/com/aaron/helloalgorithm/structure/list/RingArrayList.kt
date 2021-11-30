package com.aaron.helloalgorithm.structure.list

import kotlin.math.max

/**
 * 环形动态数组
 *
 * @author aaronzzxup@gmail.com
 * @since 2021/11/25
 */
class RingArrayList<E> : AbstractList<E> {

    companion object {
        private const val DEF_CAPACITY = 8
    }

    private var items: Array<E?>

    private var first = 0

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
        if (index < size shr 1) {
            // index 小于 size/2 ，元素向前挪，并重置 first 指针
            for (i in 0 until index) {
                items[mapIndex(i - 1)] = items[mapIndex(i)]
            }
            first = mapIndex(-1)
        } else {
            // index 大于等于 size/2 ，元素向后挪
            for (i in (size - 1) downTo index) {
                items[mapIndex(i + 1)] = items[mapIndex(i)]
            }
        }
        items[mapIndex(index)] = item
        _size++
        return true
    }

    private fun ensureCapacity(capacity: Int) {
        val items = items
        if (capacity <= items.size) {
            return
        }
        val newCapacity = max(capacity, capacity + (capacity shr 1))
        migrate(newCapacity)
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
        val oldVal = items[mapIndex(index)]
        if (index >= size shr 1) {
            // index 大于等于 size/2 ，元素向前挪
            for (i in index until size - 1) {
                items[mapIndex(i)] = items[mapIndex(i + 1)]
            }
            items[mapIndex(size - 1)] = null
        } else {
            // index 小于 size/2 ，元素向后挪，并重置 first 指针
            for (i in index downTo 1) {
                items[mapIndex(i)] = items[mapIndex(i - 1)]
            }
            items[first] = null
            first = mapIndex(1)
        }
        _size--
        if (_size == 0) {
            first = 0
        }
        return oldVal as E
    }

    override fun set(index: Int, item: E): E {
        checkIndex(index)
        val mapped = mapIndex(index)
        val oldVal = items[mapped]
        items[mapped] = item
        return oldVal as E
    }

    override fun get(index: Int): E {
        checkIndex(index)
        return items[mapIndex(index)] as E
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
        for (i in 0 until _size) {
            if (items[mapIndex(i)] == item) {
                return i
            }
        }
        return -1
    }

    override fun clear() {
        for (i in 0 until _size) {
            items[mapIndex(i)] = null
        }
        _size = 0
    }

    private fun mapIndex(index: Int): Int {
        var mapped = first + index
        val arraySize = items.size
        if (mapped < 0) {
            mapped += arraySize
        } else {
            mapped = if (mapped < arraySize) mapped else mapped - arraySize
        }
        return mapped
    }

    fun trimToSize() {
        migrate(size)
    }

    private fun migrate(capacity: Int) {
        if (first != 0) {
            val newArray = arrayOfNulls<Any>(capacity)
            for (i in 0 until _size) {
                newArray[i] = items[mapIndex(i)]
            }
            this.items = newArray as Array<E?>
            first = 0
        } else {
            this.items = items.copyOf(capacity)
        }
    }

    override fun toString(): String {
        return items.contentToString()
    }
}