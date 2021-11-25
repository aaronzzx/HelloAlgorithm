package com.aaron.helloalgorithm.algorithm.数组

import com.aaron.helloalgorithm.algorithm.LeetCode
import com.aaron.helloalgorithm.algorithm.Printer

/**
 * # 26. 删除有序数组中的重复项
 *
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 *
 * 示例 1：
 *
 * ```
 * 输入：nums = [1,1,2]
 * 输出：2, nums = [1,2]
 * ```
 *
 * 解释：函数应该返回新的长度 2 ，并且原数组 nums 的前两个元素被修改为 1, 2 。不需要考虑数组中超出新长度后面的元素。
 *
 * 示例 2：
 *
 * ```
 * 输入：nums = [0,0,1,1,1,2,2,3,3,4]
 * 输出：5, nums = [0,1,2,3,4]
 * ```
 *
 * 解释：函数应该返回新的长度 5 ， 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4 。不需要考虑数组中超出新长度后面的元素。
 *
 * 来源：力扣（LeetCode）
 *
 * 链接：[https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array]
 *
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author aaronzzxup@gmail.com
 * @since 2021/11/18
 */
class _26_删除有序数组中的重复项

private val NUMS = intArrayOf(0, 0, 1, 1, 1, 2, 2, 3, 3, 4)

fun LeetCode.数组.run_26_删除有序数组中的重复项_双指针(nums: IntArray = NUMS) {
    val old = nums.contentToString()
    val newSize = _26_删除有序数组中的重复项_双指针(nums)
    Printer.print(
        title = "删除有序数组中的重复项-双指针",
        input = "nums = $old",
        output = "$newSize, ${nums.copyOf(newSize).contentToString()}"
    )
}

/**
 * # 解法：双指针
 *
 * 当数组 nums 的长度大于 0 时，数组中至少包含一个元素，在删除重复元素之后也至少剩下一个元素，
 * 因此下标 0 保持原状即可，从下标 1 开始删除重复元素。
 *
 * 通过 fast 指针遍历数组，每次都取当前 fast 索引和 fast - 1 索引进行比较，如果两个数不相等
 * 则将当前 fast 值更新到 slow 位置，然后 slow 指针后移一位。
 *
 * 如果数组中没有重复元素，那么 slow 和 fast 一直都是相等的，只有当发现了重复元素后，slow 指针
 * 原地踏步，这个时候的 slow 指针指向的值是重复值，需要通过 fast 指针寻找不同值将它替换掉，然后
 * slow 指针后移，每次 slow 指针后移，它前面的值一定都是不重复的。
 *
 * T(n) = O(n); S(n) = O(1)
 */
fun LeetCode.数组._26_删除有序数组中的重复项_双指针(nums: IntArray): Int {
    if (nums.isEmpty()) return 0
    var slow = 1
    var fast = 1
    while (fast < nums.size) {
        if (nums[fast] != nums[fast - 1]) {
            nums[slow] = nums[fast]
            ++slow
        }
        ++fast
    }
    return slow
}