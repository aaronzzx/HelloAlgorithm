package com.aaron.helloalgorithm.algorithm.数组

import com.aaron.helloalgorithm.algorithm.LeetCode
import com.aaron.helloalgorithm.algorithm.Printer

/**
 * # 1. 两数之和
 *
 * 给定一个整数数组 nums和一个整数目标值 target，请你在该数组中找出 和为目标值 target
 * 的那两个整数，并返回它们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 *
 * 你可以按任意顺序返回答案。
 *
 * 示例 1：
 *
 * ```
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 * ```
 *
 * 示例 2：
 *
 * ```
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 * ```
 *
 * 示例 3：
 *
 * ```
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 * ```
 *
 * 来源：力扣（LeetCode）
 *
 * 链接：[https://leetcode-cn.com/problems/two-sum]
 *
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author aaronzzxup@gmail.com
 * @since 2021/11/18
 */
class _1_两数之和

private val NUMS = intArrayOf(21, 11, 43, 25, 6, 1, 76)
private const val TARGET = 101

fun LeetCode.数组.run_1_两数之和_暴力枚举(
    nums: IntArray = com.aaron.helloalgorithm.algorithm.数组.NUMS,
    target: Int = com.aaron.helloalgorithm.algorithm.数组.TARGET
) {
    val indices = LeetCode.数组._1_两数之和_暴力枚举(nums, target)
    Printer.print(
        title = "两数之和-暴力枚举",
        input = "nums = ${nums.contentToString()}, target = $target",
        output = "${indices.contentToString()}"
    )
}

fun LeetCode.数组.run_1_两数之和_哈希表(
    nums: IntArray = com.aaron.helloalgorithm.algorithm.数组.NUMS,
    target: Int = com.aaron.helloalgorithm.algorithm.数组.TARGET
) {
    val indices = LeetCode.数组._1_两数之和_哈希表(nums, target)
    Printer.print(
        title = "两数之和-哈希表",
        input = "nums = ${nums.contentToString()}, target = $target",
        output = "${indices.contentToString()}"
    )
}

/**
 * # 解法：暴力枚举
 *
 * 选定一个值，然后遍历后面所有值，分别相加判断是否等于期望和。很显然就需要两个指针 i 和 j ，
 * 外层循环 i 为了找到选定值，内层循环 j 为了找到被加值。
 *
 * T(n) = O(n^2), S(n) = O(1)
 */
fun LeetCode.数组._1_两数之和_暴力枚举(nums: IntArray, target: Int): IntArray {
    for (i in nums.indices) {
        for (j in (i + 1)..nums.lastIndex) {
            if (nums[i] + nums[j] == target) {
                return intArrayOf(i, j)
            }
        }
    }
    return intArrayOf()
}

/**
 * # 解法：哈希表
 *
 * 由于期望和是已知条件，并且遍历过程中被加值也是已知条件（`nums[i]`），可以得出
 * `选定值 = target - nums[i]` ，因此当每次循环时可以将 `nums[i]` 存于哈希表中，
 * 这样选定值就可以从哈希表中去匹配，当匹配成功后取出索引进行返回。
 *
 * 另外选定值不可以作为 map 的 value ，如果作为 value ，当查找时本质上是通过遍历
 * 整个哈希表，这样效率其实并没有变化，而作为 key ，map 实际是将 key hash 过后
 * 变为指定索引，那么通过 key 来查找时就相当于直接使用索引查找。
 *
 * T(n) = O(n), S(n) = O(n)
 */
fun LeetCode.数组._1_两数之和_哈希表(nums: IntArray, target: Int): IntArray {
    val map = hashMapOf<Int, Int>()
    for (i in nums.indices) {
        if (map.containsKey(target - nums[i])) {
            return intArrayOf(map[target - nums[i]]!!, i)
        }
        map[nums[i]] = i
    }
    return intArrayOf()
}