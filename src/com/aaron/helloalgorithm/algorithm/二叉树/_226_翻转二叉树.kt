package com.aaron.helloalgorithm.algorithm.二叉树

import com.aaron.helloalgorithm.algorithm.LeetCode
import com.aaron.helloalgorithm.algorithm.TreeNode
import com.aaron.helloalgorithm.structure.tree.ktx.println
import com.aaron.helloalgorithm.structure.tree.printer.BinaryTrees
import java.util.*

/**
 * # 226. 翻转一棵二叉树
 *
 * 示例：
 *
 * 输入：
 * ```
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * ```
 * 输出：
 * ```
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 * ```
 * 备注:
 * 这个问题是受到 Max Howell 的 原问题 启发的 ：
 *
 * 谷歌：我们90％的工程师使用您编写的软件(Homebrew)，但是您却无法在面试时在白板上写出翻转二叉树这道题，这太糟糕了。
 *
 * 来源：力扣（LeetCode）
 *
 * 链接：[https://leetcode-cn.com/problems/invert-binary-tree/]
 *
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author aaronzzxup@gmail.com
 * @since 2021/11/23
 */
class _226_翻转二叉树

private val NUMS = intArrayOf(50, 25, 75, 12, 40, 60, 90, 6, 20, 30, 48, 55, 70, 80, 100)

fun LeetCode.二叉树.run_226_翻转二叉树(nums: IntArray = NUMS) {
    if (nums.isEmpty()) {
        println("[]")
        return
    }
    val root = TreeNode(nums[0]).also {
        it.root = it
    }

    val list = nums.toMutableList().also {
        it.removeAt(0)
    }
    list.forEach {
        var node: TreeNode? = root
        var parent = root
        var compare = 0
        while (node != null) {
            parent = node
            compare = it.compareTo(node.`val`)
            when (compare) {
                -1 -> {
                    node = node.left
                }
                1 -> {
                    node = node.right
                }
                else -> {
                    node.`val` = it
                    return
                }
            }
        }
        val newNode = TreeNode(it).also { treeNode ->
            treeNode.root = root
        }
        when (compare) {
            -1 -> {
                parent.left = newNode
            }
            else -> {
                parent.right = newNode
            }
        }
    }
    root.println(BinaryTrees.PrintStyle.LEVEL_ORDER)
//    val inverted = _226_翻转二叉树_层序遍历(root)
//    val inverted = _226_翻转二叉树_前序遍历(root)
//    val inverted = _226_翻转二叉树_中序遍历(root)
    val inverted = _226_翻转二叉树_后序遍历(root)
    println()
    inverted!!.println(BinaryTrees.PrintStyle.LEVEL_ORDER)
}

fun LeetCode.二叉树._226_翻转二叉树_前序遍历(root: TreeNode?): TreeNode? {
    root ?: return root
    print("${root.`val`}, ")
    val temp = root.right
    root.right = _226_翻转二叉树_前序遍历(root.left)
    root.left = _226_翻转二叉树_前序遍历(temp)
    return root
}

fun LeetCode.二叉树._226_翻转二叉树_中序遍历(root: TreeNode?): TreeNode? {
    root ?: return root
    val invertedLeft = _226_翻转二叉树_中序遍历(root.left)
    print("${root.`val`}, ")
    val temp = root.right
    root.right = invertedLeft
    root.left = _226_翻转二叉树_中序遍历(temp)
    return root
}

fun LeetCode.二叉树._226_翻转二叉树_后序遍历(root: TreeNode?): TreeNode? {
    root ?: return root
    val left = _226_翻转二叉树_后序遍历(root.left)
    val right = _226_翻转二叉树_后序遍历(root.right)
    print("${root.`val`}, ")
    root.left = right
    root.right = left
    return root
}

fun LeetCode.二叉树._226_翻转二叉树_层序遍历(root: TreeNode?): TreeNode? {
    root ?: return root
    val queue: Queue<TreeNode> = LinkedList<TreeNode>().also {
        it.offer(root)
    }
    while (queue.isNotEmpty()) {
        val poll = queue.poll()
        print("${poll.`val`}, ")
        val temp = poll.left
        poll.left = poll.right
        poll.right = temp
        if (poll.left != null) {
            queue.offer(poll.left)
        }
        if (poll.right != null) {
            queue.offer(poll.right)
        }
    }
    return root
}