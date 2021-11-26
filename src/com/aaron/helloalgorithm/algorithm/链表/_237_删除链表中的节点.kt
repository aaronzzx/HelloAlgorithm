package com.aaron.helloalgorithm.algorithm.链表

import com.aaron.helloalgorithm.algorithm.LeetCode
import com.aaron.helloalgorithm.algorithm.ListNode

/**
 * # 237. 删除链表中的节点
 *
 * 请编写一个函数，用于 删除单链表中某个特定节点 。在设计函数时需要注意，你无法访问链表的头节点 head ，只能直接访问 要被删除的节点 。
 *
 * 题目数据保证需要删除的节点 不是末尾节点 。
 *
 * 来源：力扣（LeetCode）
 *
 * 链接：[https://leetcode-cn.com/problems/delete-node-in-a-linked-list]
 *
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author aaronzzxup@gmail.com
 * @since 2021/11/26
 */
class _237_删除链表中的节点

fun LeetCode.链表._237_删除链表中的节点(node: ListNode?) {
    val next = node?.next ?: return
    node.`val` = next.`val`
    node.next = next.next
}