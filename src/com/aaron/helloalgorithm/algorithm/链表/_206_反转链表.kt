package com.aaron.helloalgorithm.algorithm.链表

import com.aaron.helloalgorithm.algorithm.LeetCode
import com.aaron.helloalgorithm.algorithm.ListNode

/**
 * # 206. 反转链表
 *
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 *
 * 来源：力扣（LeetCode）
 *
 * 链接：[https://leetcode-cn.com/problems/reverse-linked-list/]
 *
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author aaronzzxup@gmail.com
 * @since 2021/11/26
 */
class _206_反转链表

/**
 * # 递归
 *
 * The linked list: 1->2->3
 * ```
 * 1. reverse(head-1): external call
 *    val next-2 = head-1.next
 *    val newHead-? = reverse(next-2)
 *    // ...
 *
 * 2. val next-3 = head-2.next
 *    val newHead-? = reverse(next-3)
 *    // ...
 *
 * 3. val null = head-3.next ?: return head-3
 *
 * back 2.
 *    // ...
 *    val newHead-3 = reverse(next-3)
 *    next-3.next = head-2
 *    head-2.next = null
 *    return newHead-3
 *
 * back 1.
 *    // ...
 *    val newHead-3 = reverse(next-2)
 *    next-2.next = 1
 *    head-1.next = null
 *    return newHead-3 // back to extern
 * ```
 */
fun LeetCode.链表._206_反转链表_递归(head: ListNode?): ListNode? {
    val next = head?.next ?: return head
    val newHead = _206_反转链表_递归(next)
    next.next = head
    head.next = null
    return newHead
}

fun LeetCode.链表._206_反转链表_遍历(head: ListNode?): ListNode? {
    var newHead: ListNode? = null
    var node: ListNode? = head
    while (node != null) {
        val next = node.next
        node.next = newHead
        newHead = node
        node = next
    }
    return newHead
}