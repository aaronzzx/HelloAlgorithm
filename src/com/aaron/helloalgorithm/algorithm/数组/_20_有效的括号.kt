package com.aaron.helloalgorithm.algorithm.数组

import com.aaron.helloalgorithm.algorithm.LeetCode
import java.util.*

/**
 * # 20. 有效的括号
 *
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 *
 * 来源：力扣（LeetCode）
 *
 * 链接：[https://leetcode-cn.com/problems/valid-parentheses]
 *
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author aaronzzxup@gmail.com
 * @since 2021/11/26
 */
class _20_有效的括号

/**
 * 使用栈对非右括号的元素进行入栈，如果是右括号则判断栈是否为空，不为空意味着以右括号开头，
 * 显然是错误的；如果栈不为空且出栈元素不是左括号那也是错的。
 *
 * 使用 Map 简化条件判断，提前将成对括号存储，直接使用字符取对应括号字符。
 */
fun LeetCode.数组._20_有效的括号(s: String): Boolean {
    if (s.isEmpty()) return false
    val stack: Deque<Char> = LinkedList()
    val map = hashMapOf(
        '}' to '{',
        ']' to '[',
        ')' to '('
    )
    s.forEach {
        if (map.containsKey(it)) {
            // 是右括号
            if (stack.isEmpty() || map[it] != stack.pop()) {
                // 1. 栈为空，意味着右括号开头，不成立
                // 2. 出栈元素不是左括号，不成立
                return false
            }
        } else {
            // 是左括号，入栈
            stack.push(it)
        }
    }
    return stack.isEmpty()
}