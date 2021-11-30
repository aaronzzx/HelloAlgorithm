package com.aaron.helloalgorithm.structure.list

/**
 * @author aaronzzxup@gmail.com
 * @since 2021/11/25
 */
fun main() {
    val list = RingArrayList<Int>(5)
    repeat(5) {
        list.add(it)
    }
    println("$list")
    list.add(0, 6)
    list.add(0, 7)
    list.add(0, 8)
    list.add(0, 9)
    println("$list")
    list.add(0, 10)
    list.add(0, 11)
    println("$list")
    println("${list.get(10)}")
}