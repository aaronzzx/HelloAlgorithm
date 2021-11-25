package com.aaron.helloalgorithm.algorithm.数组

import com.aaron.helloalgorithm.algorithm.LeetCode
import com.aaron.helloalgorithm.algorithm.Printer

/**
 * # 27. 移除元素
 *
 * 给你一个数组 nums和一个值 val，你需要 原地 移除所有数值等于val的元素，并返回移除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 *
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 *
 * 示例 1：
 *
 * ```
 * 输入：nums = [3,2,2,3], val = 3
 * 输出：2, nums = [2,2]
 * 解释：函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。你不需要考虑数组中超出新长度后面的元素。例如，函数返回的新长度为 2 ，而 nums = [2,2,3,3] 或 nums = [2,2,0,0]，也会被视作正确答案。
 * ```
 *
 * 示例 2：
 *
 * ```
 * 输入：nums = [0,1,2,2,3,0,4,2], val = 2
 * 输出：5, nums = [0,1,4,0,3]
 * 解释：函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。注意这五个元素可为任意顺序。你不需要考虑数组中超出新长度后面的元素。
 * ```
 *
 * 来源：力扣（LeetCode）
 *
 * 链接：[https://leetcode-cn.com/problems/remove-element]
 *
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author aaronzzxup@gmail.com
 * @since 2021/11/18
 */
class _27_移除元素

private val NUMS = intArrayOf(2, 2, 2, 2, 2, 0, 1, 2, 3, 4, 5)
private const val VAL = 2

fun LeetCode.数组.run_27_移除元素_双指针(nums: IntArray = NUMS, `val`: Int = VAL) {
    val old = nums.contentToString()
    val newSize = _27_移除元素_双指针(nums, `val`)
    Printer.print(
        title = "移除元素-双指针",
        input = "nums = $old, val = $`val`",
        output = "$newSize, nums = ${nums.copyOf(newSize).contentToString()}"
    )
}

fun LeetCode.数组.run_27_移除元素_双指针优化(nums: IntArray = NUMS, `val`: Int = VAL) {
    val old = nums.contentToString()
    val newSize = _27_移除元素_双指针优化(nums, `val`)
    Printer.print(
        title = "移除元素-双指针优化",
        input = "nums = $old, val = $`val`",
        output = "$newSize, nums = ${nums.copyOf(newSize).contentToString()}"
    )
}

/**
 * # 解法：双指针
 *
 * left 和 right 指针都从 0 开始，left 代表的可能需要移除的元素下标，right 负责向后
 * 移动查找可供替换 left 指向的值，当 right 值不等于 val 时表示可以替换 left 值，替换后
 * left 和 right 都后移一位开始新一轮循环；如果等于 val 则 right 指针直接后移查找下一个。
 *
 * 这种解法下，left 和 right 都需要遍历完整个数组，即使数组中没有等于 val 的元素。
 *
 * T(n) = O(n), S(n) = O(1)
 */
fun LeetCode.数组._27_移除元素_双指针(nums: IntArray, `val`: Int): Int {
    var left = 0
    for (right in nums.indices) {
        if (nums[right] != `val`) {
            nums[left] = nums[right]
            ++left
        }
    }
    return left
}

/**
 * # 解法：双指针优化
 *
 * 未优化版本存在的问题是无论如何都需要遍历整个数组，而优化版本将 right 指针指向了数组末端，
 * 从后开始向前查找，这里用的是 left 值来判断是否等于 val ，如果等于则表示 left 需要被替换，
 * 于是将 right - 1 值覆盖过去；如果不等则 left 可以后移一位。
 *
 * 在 left 值等于 val 并使用 right - 1 值覆盖后，其实并不算完，因为可能覆盖过来的 right - 1
 * 值也等于 val ，因此覆盖完后 left 指针暂时不后移，等到下一轮循环时检查 left 值，如果还是相等，
 * 这时候 right 相较于上一次循环已经是减一了，于是继续拿 right - 1 覆盖，相当于上次循环的 right - 2 ；
 * 如果不等，则当前 left 值是不重复的，这时候才可以将 left 后移一位。
 *
 * 这种解法下，right 指针需要从尾遍历到头，表示数组中全是 val
 * 值，或者 left 指针从头遍历到尾，表示数组中没有等于 val 的元素，又或者 left
 * 和 right 各自遍历几次，总之 left 和 right 的遍历次数加起来相当于一个指针
 * 遍历一次数组，而未优化版本的两个指针各自都需要遍历一次。
 *
 * 优化版本避免了需要保留元素的重复赋值。
 *
 * T(n) = O(n), S(n) = O(1)
 */
fun LeetCode.数组._27_移除元素_双指针优化(nums: IntArray, `val`: Int): Int {
    var left = 0
    var right = nums.size
    while (left < right) {
        if (nums[left] == `val`) {
            nums[left] = nums[right - 1]
            --right
        } else {
            ++left
        }
    }
    return left
}