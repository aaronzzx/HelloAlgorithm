package com.aaron.helloalgorithm.structure.tree.ktx

import com.aaron.helloalgorithm.structure.tree.printer.BinaryTreeInfo
import com.aaron.helloalgorithm.structure.tree.printer.BinaryTrees
import com.aaron.helloalgorithm.structure.tree.printer.InorderPrinter
import com.aaron.helloalgorithm.structure.tree.printer.LevelOrderPrinter

/**
 * @author aaronzzxup@gmail.com
 * @since 2021/11/23
 */

fun BinaryTreeInfo.printString(style: BinaryTrees.PrintStyle): String {
    return when (style) {
        BinaryTrees.PrintStyle.INORDER -> InorderPrinter(this).printString()
        BinaryTrees.PrintStyle.LEVEL_ORDER -> LevelOrderPrinter(this).printString()
    }
}

fun BinaryTreeInfo.print(style: BinaryTrees.PrintStyle) {
    when (style) {
        BinaryTrees.PrintStyle.INORDER -> InorderPrinter(this).print()
        BinaryTrees.PrintStyle.LEVEL_ORDER -> LevelOrderPrinter(this).print()
    }
}

fun BinaryTreeInfo.println(style: BinaryTrees.PrintStyle) {
    when (style) {
        BinaryTrees.PrintStyle.INORDER -> InorderPrinter(this).println()
        BinaryTrees.PrintStyle.LEVEL_ORDER -> LevelOrderPrinter(this).println()
    }
}