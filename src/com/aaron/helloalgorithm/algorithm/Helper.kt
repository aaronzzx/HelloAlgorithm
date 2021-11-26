package com.aaron.helloalgorithm.algorithm

import com.aaron.helloalgorithm.structure.tree.printer.BinaryTreeInfo

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

class TreeNode(var `val`: Int) : BinaryTreeInfo {

    var left: TreeNode? = null

    var right: TreeNode? = null

    var root: TreeNode? = null

    override fun root(): Any? {
        return root
    }

    override fun left(node: Any?): Any? {
        return (node as? TreeNode?)?.left
    }

    override fun right(node: Any?): Any? {
        return (node as? TreeNode?)?.right
    }

    override fun string(node: Any?): Any? {
        return (node as? TreeNode)?.`val`
    }
}