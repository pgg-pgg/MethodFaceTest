package leetCode

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.math.max
import kotlin.math.min


object FaceCode {

    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    /**
    给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 的那 两个 整数，并返回它们的数组下标。

    你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。

    你可以按任意顺序返回答案。

    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/two-sum
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val map = HashMap<Int, Int>()
        nums.forEachIndexed { index, num ->
            if (map.containsKey(target - num)) {
                return intArrayOf(index, map[(target - num)]!!)
            }
            map[num] = index
        }
        return intArrayOf(0)
    }

    /**
     * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。

    请你将两个数相加，并以相同形式返回一个表示和的链表。

    你可以假设除了数字 0 之外，这两个数都不会以 0 开头。

    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/add-two-numbers
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        if (l1 == null && l2 == null) return null
        if (l1 == null) return l2
        if (l2 == null) return l1
        var ln1 = l1
        var ln2 = l2
        var lastSum = 0
        val sumNodeList = arrayListOf<ListNode>()
        while (ln1 != null && ln2 != null) {
            val sum = ln1.`val` + ln2.`val` + lastSum
            lastSum = sum / 10
            sumNodeList.add(ListNode(sum % 10))
            ln1 = ln1.next
            ln2 = ln2.next
        }

        while (ln2 != null) {
            val sum = ln2.`val` + lastSum
            lastSum = sum / 10
            sumNodeList.add(ListNode(sum % 10))
            ln2 = ln2.next
        }
        while (ln1 != null) {
            val sum = ln1.`val` + lastSum
            lastSum = sum / 10
            sumNodeList.add(ListNode(sum % 10))
            ln1 = ln1.next
        }
        if (lastSum > 0) {
            sumNodeList.add(ListNode(lastSum))
        }
        var sumNode = sumNodeList[0]
        sumNodeList.forEachIndexed { index, listNode ->
            listNode.next = sumNodeList.getOrNull(index + 1)
        }
        return sumNodeList[0]
    }


    /**
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     * 思路：
     * map中存储每个字符的最新位置
     * left表示滑动窗口的左边界，未遇到重复字符时（map中没有查询到当前字符时），left不变
     * 当遇到重复字符时，left移到重复字符的下一个位置
     * max是index - left + 1
     */
    fun lengthOfLongestSubstring(s: String): Int {
        if (s.isEmpty()) return 0
        val map = HashMap<Char, Int>()
        var left = 0
        var max = 0
        s.forEachIndexed { index, char ->
            if (map.containsKey(char)) {
                left = Math.max(left, map[char]!! + 1)
            }
            map[char] = index
            max = Math.max(max, index - left + 1)
        }
        println(max)
        return max
    }


    /**
     * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
     * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/
     */
    fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
        var medianNum: Double
        val nums = nums1 + nums2
        nums.sort()
        val median = nums.size / 2
        medianNum = if (nums.size % 2 > 0) {
            nums[median].toDouble()
        } else {
            (nums[median - 1] + nums[median]) / 2.toDouble()
        }

        return medianNum
    }

    /**
     * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。

    比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：

    P   A   H   N
    A P L S I I G
    Y   I   R
    之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。

    请你实现这个将字符串进行指定行数变换的函数：

    string convert(string s, int numRows);

    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/zigzag-conversion
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    fun convert(s: String, numRows: Int): String {
        if (numRows == 1) return s

        val ret = StringBuilder()
        val n = s.length
        val cycleLen = 2 * numRows - 2

        for (i in 0 until numRows) {
            var j = 0
            while (j + i < n) {
                ret.append(s[j + i])
                if (i != 0 && i != numRows - 1 && j + cycleLen - i < n)
                    ret.append(s[j + cycleLen - i])
                j += cycleLen
            }
        }
        println(ret.toString())
        return ret.toString()
    }

    /**
     * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。

    如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。

    假设环境不允许存储 64 位整数（有符号或无符号）。

    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/reverse-integer
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    fun reverse(x: Int): Int {
        var sourceNum = x
        var resultNum = 0
        while (sourceNum != 0) {
            val temp = sourceNum % 10
            sourceNum /= 10
            if (resultNum > Int.MAX_VALUE / 10 || (resultNum == Int.MAX_VALUE && temp > 7)) return 0
            if (resultNum < Int.MIN_VALUE / 10 || (resultNum == Int.MIN_VALUE && temp < -8)) return 0
            resultNum = resultNum * 10 + temp
        }
        println(resultNum)
        return resultNum
    }


    /**
    请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。

    函数 myAtoi(string s) 的算法如下：

    读入字符串并丢弃无用的前导空格
    检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
    读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
    将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
    如果整数数超过 32 位有符号整数范围 [−231,  231 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −231 的整数应该被固定为 −231 ，大于 231 − 1 的整数应该被固定为 231 − 1 。
    返回整数作为最终结果。
    注意：

    本题中的空白字符只包括空格字符 ' ' 。
    除前导空格或数字后的其余字符串外，请勿忽略 任何其他字符。
     

    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/string-to-integer-atoi
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    fun myAtoi(s: String): Int {
        if (s.trim().isEmpty()) return 0
        val trim = s.trim()
        if (trim[0].isLowerCase() || trim[0].isUpperCase()) return 0
        val isNegative = trim[0] == '-'
        var resultNum = 0
        trim.let {
            trim.forEachIndexed { index, char ->
                if (index == 0 && (char == '+' || char == '-')) return@forEachIndexed
                if (char.isDigit()) {
                    var temp = char.toString().toInt()
                    if (!isNegative && resultNum > Int.MAX_VALUE / 10 || resultNum == Int.MAX_VALUE / 10 && temp > Integer.MAX_VALUE % 10) {
                        println(resultNum)
                        return Int.MAX_VALUE
                    }
                    if (isNegative && -resultNum < Int.MIN_VALUE / 10 || -resultNum == Int.MIN_VALUE / 10 && temp > -(Integer.MIN_VALUE % 10)) {
                        println(resultNum)
                        return Int.MIN_VALUE
                    }
                    resultNum = resultNum * 10 + temp
                } else {
                    return@let
                }
            }
        }
        println(resultNum)
        return if (isNegative) -resultNum else resultNum
    }


    /**
     *  给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。

    说明：你不能倾斜容器。
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/container-with-most-water
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    fun maxArea(height: IntArray): Int {
        if (height.size <= 1) return 0
        var maxHeight = 0
        var startIndex = 0
        var endIndex = height.size - 1
        while (startIndex < endIndex) {
            maxHeight = Math.max(maxHeight, (endIndex - startIndex) * Math.min(height[endIndex], height[startIndex]))
            if (height[endIndex] < height[startIndex]) {
                endIndex--
            } else {
                startIndex++
            }
        }
        println(maxHeight)
        return maxHeight
    }

    /**
     *给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。

    注意：答案中不可以包含重复的三元组。



    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/3sum
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    fun threeSum(nums: IntArray): List<List<Int>> {
        val resultList = mutableListOf<MutableList<Int>>()
        //现将数组排序
        nums.sort()
        //遍历数组
        nums.forEachIndexed { index, num ->
            //因为已经排好序了，所以当num > 0 表示之后的不可能累加为0了，所以直接返回结果
            if (num > 0) return resultList
            //判断当前值如果与上一个值相等的话，直接结束本次循环，去除重复值
            if (index > 0 && nums[index - 1] == num) return@forEachIndexed

            //定义双指针，left 在当前位置的下一个位置，right在数组的最后一位
            var left = index + 1
            var right = nums.size - 1
            while (left < right) {
                //计算当前几个数的和
                val tmp = num + nums[left] + nums[right]
                when {
                    tmp == 0 -> {
                        //如果恰好==0，那么直接添加到返回结果中
                        resultList.add(arrayListOf(num, nums[left], nums[right]))
                        //跳过中间重复的数字
                        while (left < right && nums[left + 1] == nums[left]) left++
                        while (left < right && nums[right - 1] == nums[right]) right--
                        //left和right都向内部移动
                        left++
                        right--
                    }
                    tmp < 0 -> left++
                    else -> right--
                }
            }
        }
        return resultList
    }


    /**
     *  给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。

        给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。

        示例 1：

        输入：digits = "23"
        输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    fun letterCombinations(digits: String): List<String> {
        if (digits.isEmpty()) return emptyList()
        val resultList = ArrayList<String>()
        val map = HashMap<Char, String>().apply {
            put('2', "abc")
            put('3', "def")
            put('4', "ghi")
            put('5', "jkl")
            put('6', "mno")
            put('7', "pqrs")
            put('8', "tuv")
            put('9', "wxyz")
        }
        solver(resultList, map, digits, 0, StringBuffer())
        return resultList
    }

    fun solver(resultList: ArrayList<String>, map: HashMap<Char, String>, digits: String, index: Int, resultString: StringBuffer) {
        if (index == digits.length) {
            resultList.add(resultString.toString())
        } else {
            val char = digits[index]
            val letter = map[char]!!
            for (i in 0 until letter.length) {
                resultString.append(letter[i])
                solver(resultList, map, digits, index + 1, resultString)
                resultString.deleteCharAt(index)
            }
        }
    }


    /**
     *  给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。

        有效字符串需满足：

        左括号必须用相同类型的右括号闭合。
        左括号必须以正确的顺序闭合。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/valid-parentheses
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    fun isValid(s: String): Boolean {
        if (s.length % 2 == 1) return false
        val map = HashMap<Char, Char>().apply {
            put(')','(')
            put('}','{')
            put(']','[')
        }
        val stack = Stack<Char>()
        s.forEach {
            if (map.containsKey(it)) {
                if (stack.isEmpty() || stack.peek() != map[it]) {
                    return false
                }
                stack.pop()
            } else {
                stack.push(it)
            }
        }

        return stack.isEmpty()
    }

    /**
     *  给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。

        进阶：你能尝试使用一趟扫描实现吗？
        https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/

        输入：head = [1,2,3,4,5], n = 2
        输出：[1,2,3,5]
        输入：head = [1], n = 1
        输出：[]

     eg：使用双指针，两个指针相差n个位置
     */
    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        if (head == null) return null
        val tmpHead = ListNode(0)
        tmpHead.next = head

        var start: ListNode? = tmpHead
        var gap = n
        var end: ListNode? = tmpHead
        while (gap >= 0) {
            end = end?.next
            gap--
        }
        while (end?.next != null) {
            start = start?.next
            end = end.next
        }
        start?.next = start?.next?.next
        return tmpHead.next

    }

    /**
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     *
     * https://leetcode-cn.com/problems/merge-two-sorted-lists/
     */
    fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {
        if (l1 == null && l2 == null) return null
        if (l1 == null) return l2
        if (l2 == null) return l1
        var cur1 = l1
        var cur2 = l2
        val list = arrayListOf<ListNode>()
        while (cur1 != null && cur2 != null) {
            if (cur1.`val` < cur2.`val`) {
                list.add(cur1)
                cur1 = cur1.next
            } else {
                list.add(cur2)
                cur2 = cur2.next
            }
        }
        while (cur1 != null) {
            list.add(cur1)
            cur1 = cur1.next
        }
        while (cur2 != null) {
            list.add(cur2)
            cur2 = cur2.next
        }
        list.forEachIndexed { index, listNode ->
            if (index < list.size - 1) {
                listNode.next = list[index + 1]
            }
        }
        return list[0]
    }

}
