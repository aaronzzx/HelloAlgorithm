package com.aaron.helloalgorithm.structure.list

/**
 * @author aaronzzxup@gmail.com
 * @since 2021/11/25
 */
fun main() {
    val list = ArrayList<Int>()
    repeat(8) {
        list.add(it + 1)
    }
    println("$list")
    list.clear()
    println("$list")
    list.trimToSize()
    println("$list")
}