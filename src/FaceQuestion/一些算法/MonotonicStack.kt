package FaceQuestion.一些算法

import java.util.*

/**
 * 单调栈
 *
 * 单调递增栈可以找到左起第一个比当前数字小的元素。
 * 比如数组 [2 1 4 6 5]，
 * 刚开始2入栈，数字1入栈的时候，发现栈顶元素2比较大，将2移出栈，
 * 此时1入栈。那么2和1都没左起比自身小的数字。然后数字4入栈的时候，
 * 栈顶元素1小于4，于是1就是4左起第一个小的数字。此时栈里有1和4，
 * 然后数字6入栈的时候，栈顶元素4小于6，于是4就是6左起第一个小的数字。
 * 此时栈里有1，4，6，然后数字5入栈的时候，栈顶元素6大于5，将6移除，
 * 此时新的栈顶元素4小于5，那么4就是5左起的第一个小的数字，最终栈内数字为1，4，5。
 *
 *
 * 单调递减栈可以找到左起第一个比当前数字大的元素。
 */
object MonotonicStack {
    @JvmStatic
    fun main(args: Array<String>) {
        maxRecSiz(arrayOf(intArrayOf(1, 0, 1, 1), intArrayOf(1, 1, 1, 1), intArrayOf(1, 1, 1, 0)))
    }

    fun monotonicStack(nums: IntArray) {
        if (nums.isEmpty() || nums.size <= 1) return
        //初始化一个栈
        val stack = Stack<Int>()
        val result = arrayListOf<IntArray>()
        for (i in 0 until nums.size) {
            //当栈不为空且当前元素 > 栈顶元素时
            while (!stack.isEmpty() && nums[i] >= nums[stack.peek()]) {
                stack.pop()
                result.add(intArrayOf(if (stack.isEmpty()) -1 else nums[stack.peek()], nums[i]))
            }
            stack.push(i)
        }
        while (!stack.isEmpty()) {
            stack.pop()
            result.add(intArrayOf(if (stack.isEmpty()) -1 else nums[stack.peek()], -1))
        }
        result.forEach {
            println(it.contentToString())
        }
    }


    /**
     * 单调栈应用1
     * 求最大子矩阵的大小
     * 给定一个整形矩阵map，其中的值只有0，1两种，求其中全是1的所有矩形区域中，最大的矩形区域为1的数量
     * eg: 1  1  1  0
     * 其中最大矩形区域右3个1，所以返回3
     *
     * 1 0 1 1
     * 1 1 1 1
     * 1 1 1 0
     * 其中最大为6个1，所以返回6
     * 思路：
     * 将此题转化成求直方图矩形面积最大的问题，使用单调递增栈解决
     */
    private fun maxRecSiz(map: Array<IntArray>): Int {
        if (map.isEmpty() || map[0].isEmpty()) return 0
        var maxArea = 0
        val height = IntArray(map[0].size)
        for (i in 0 until map.size) {
            for (j in 0 until map[0].size) {
                height[j] = if (map[i][j] == 0) 0 else height[j] + 1
            }
            maxArea = Math.max(maxArea, maxRecFromBottom(height))
        }
        return maxArea
    }

    private fun maxRecFromBottom(heights: IntArray): Int {
        if (heights.isEmpty()) return 0
        var maxArea = 0
        val stack = Stack<Int>()
        for (i in 0 until heights.size) {
            //当栈不为空，且当前值 <= 栈顶位置的元素
            while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {
                //当前已哪个位置计算最大面积
                val j = stack.pop()
                //当前位置的左边界
                val k = if (stack.isEmpty()) -1 else stack.peek()
                //当前计算的面积为高端 * 能移动的最大值
                val curArea = (i - k - 1) * heights[j]
                //获取最大值
                maxArea = Math.max(maxArea, curArea)
            }
            stack.push(i)
        }
        //当遍历完栈内还有元素，那么一一清算
        while (!stack.isEmpty()) {
            val j = stack.pop()
            val k = if (stack.isEmpty()) -1 else stack.peek()
            val curArea = (heights.size - k - 1) * heights[j]
            maxArea = Math.max(maxArea, curArea)
        }
        return maxArea
    }


    /**
     * 单调栈应用2
     *
     * n 个 烽火台围成一个圈，
     * 任意两个烽火台只要中间的烽火台比他们两个都低就能看见彼此，
     * 当然相邻的肯定能看见对面，求能看见彼此的对数
     * https://img-blog.csdnimg.cn/20190716200408485.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3d5c3cxOTk4,size_16,color_FFFFFF,t_70
     * https://blog.csdn.net/wysw1998/article/details/96177003
     */
    fun communications(arr: IntArray): Long {
        if (arr.size < 2) return 0
        var maxIndex = 0
        //遍历获取当前数组的第一个最大值的位置
        for (i in 0 until arr.size) {
            maxIndex = if (arr[maxIndex] < arr[i]) i else maxIndex
        }
        var value = arr[maxIndex]
        var next = nextIndex(arr.size, maxIndex)
        //能相互看见烽火台的对数
        var res = 0L
        //单调递减栈 pair.first 记录当前数组的值 pair.second 记录当前值出现了多少次
        val stack = Stack<Pair>()
        //将最大值压入栈中
        stack.push(Pair(value))
        while (next != maxIndex) {
            value = arr[next]
            while (!stack.isEmpty() && value > stack.peek().value) {
                val times = stack.pop().times
                res += getInternalSum(value) + 2 * times
            }
            if (!stack.isEmpty() && value == stack.peek().value) {
                //如果当前值和栈顶元素相等，那么栈顶元素出现次数+1
                stack.peek().times++
            } else {
                //如果不相等，那么直接压栈
                stack.push(Pair(value))
            }
            next = nextIndex(arr.size, next)
        }

        //当遍历完之后，栈中可能还有元素，那么开始处理
        while (!stack.isEmpty()) {
            //1、如果是倒数第三及以上一定是C(2,times)+2*k
            //2、如果是倒数第二，倒数第一times为1，C(2,times)+k
            //3、如果是倒数第二，倒数第一times为2，C(2,times)+2*k
            val times = stack.pop().times
            res += getInternalSum(times)//C(2,times)内部必产生山峰对
            if (!stack.isEmpty()) {
                res += times
                if (stack.size > 1) {
                    //不是倒数第一个元素
                    //满足了1、的情况
                    res += times
                } else {
                    //size==1,看最大数的times是1还是为k>1
                    //如果times>1就是 res+=times满足3、的情况
                    //吐过times不满足，那么就是res=res+0；满足了2、的情况
                    res += if (stack.peek().times > 1) times else 0
                }
            }
        }
        return res
    }

    /**
     * 获取下一个位置
     * 因为是环，所以设置了size确定是否到达底部，未达到就i+1，达到了就置为0，说明到达了开头
     */
    private fun nextIndex(size: Int, i: Int): Int {
        return if (i < size - 1) i + 1 else 0
    }

    /**
     * 计算Cn2 组合的值
     */
    private fun getInternalSum(n: Int): Long {
        return if (n == 1) 0 else n * (n - 1) / 2L
    }

    class Pair(var value: Int) {
        var times: Int = 1

        init {
            times = 1
        }
    }

}