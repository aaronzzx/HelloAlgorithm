package com.aaron.helloalgorithm.structure.tree

import com.aaron.helloalgorithm.structure.tree.ktx.println
import com.aaron.helloalgorithm.structure.tree.printer.BinaryTrees
import kotlin.concurrent.thread

/**
 * @author aaronzzxup@gmail.com
 * @since 2021/11/23
 */
fun testTree() {
    val tree = BST<Int>()
//    NUMS.forEach {
//        tree.add(it)
//    }
//    repeat((6..10).random()) {
//        Thread.sleep(10)
//        tree.add((1..50).random())
//    }
//    tree.println(BinaryTrees.PrintStyle.LEVEL_ORDER)
//    println("tree: ${tree.toListByInorder()}")
//    println("tree-size: ${tree.size()}")
//    println("tree-height: ${tree.height()}")
//    println("isFull: ${tree.isFull()}")
//    println("isPerfect: ${tree.isPerfect()}")
//    println("isComplete: ${tree.isComplete()}")

//    printFullTree(tree)
//    printPerfectTree(tree)
//    printCompleteTree(tree)

    NUMS.forEach {
        tree.add(it)
    }
    tree.println(BinaryTrees.PrintStyle.LEVEL_ORDER)
    tree.remove(9)
    tree.println(BinaryTrees.PrintStyle.LEVEL_ORDER)
}

private fun randomAddTreeNode(tree: BST<Int>, maxNodeCount: Int = 7) {
    repeat(maxNodeCount) {
        Thread.sleep(10)
        tree.add((1..15).random())
    }
}

private fun printFullTree(tree: BST<Int>) {
    thread {
        while (!tree.isFull()) {
            tree.clear()
            randomAddTreeNode(tree)
        }
        println("\nFull Tree")
        tree.println(BinaryTrees.PrintStyle.LEVEL_ORDER)
    }.join()
}

private fun printPerfectTree(tree: BST<Int>) {
    thread {
        while (!tree.isPerfect() || tree.size() <= 4) {
            tree.clear()
            randomAddTreeNode(tree)
        }
        println("\nPerfect Tree")
        tree.println(BinaryTrees.PrintStyle.LEVEL_ORDER)
    }.join()
}

private fun printCompleteTree(tree: BST<Int>) {
    thread {
        while (!tree.isComplete() || tree.size() > 4) {
            tree.clear()
            randomAddTreeNode(tree)
        }
        println("\nComplete Tree")
        tree.println(BinaryTrees.PrintStyle.LEVEL_ORDER)
    }.join()
}

private val NUMS = intArrayOf(7, 4, 2, 1, 3, 5, 9, 8, 11, 10, 12, 6)
private val NUMS2 = intArrayOf(50, 25, 12, 40, 6, 20, 30, 48, 75, 60, 90, 55, 70, 80, 100)