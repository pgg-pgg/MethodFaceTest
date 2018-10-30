package FaceQuestion.一些算法

import java.util.*

/**
 * 滑动窗口中的最大值的结构
 */
object ScrollWindow {

    /**
     * 使用一个双端链表，将
     */
    fun getWindowMax(arr: IntArray, w: Int): IntArray? {
        if (w < 1 || arr.size < w) {
            return null
        }
        //记录最大值的双端链表
        val qMax = LinkedList<Int>()
        //记录结果的数组
        val resultArray = IntArray(arr.size - w + 1)
        //记录当前结果的位置
        var index = 0
        for (i in 0 until arr.size) {
            //当链表不为空 && 链表记录的最后一个值 <= 当前值，那么就需要将链表的值弹出
            //保证当前链表是从大到小排列
            while (!qMax.isEmpty() && arr[qMax.peekLast()] <= arr[i]) {
                qMax.pollLast()
            }
            //将当前值添加到链表
            qMax.addLast(i)
            //判断链表的头是否已经不在窗口内了，如果不在，则需要弹出
            if (qMax.peekFirst() == i - w) {
                qMax.pollFirst()
            }
            //每次循环需要取当前链表中的最大值（链表的头节点）作为结果
            if (i >= w - 1) {
                resultArray[index] = arr[qMax.peekFirst()]
                index ++
            }
        }
        return resultArray
    }

    /**
     * 获取最大值 - 最小值 < num 的所有子数组的数量
     */
    fun getMaxMinNumArrNum(arr: IntArray, num: Int): Int {
        if (arr.isEmpty()) return 0
        //记录滑动窗口的最大值链表
        val qMax = LinkedList<Int>()
        //记录滑动窗口的最小值链表
        val qMin = LinkedList<Int>()
        //子数组数量
        var result = 0
        //窗口左边界
        var left = 0
        //窗口右边界
        var right = 0
        //左未越界
        while (left < arr.size) {
            //右未越界
            while (right < arr.size) {
                //保证当前最小值队列由小到大排列，当前值如果比链表最后值小，那么就需要弹出链表最后值
                while (!qMin.isEmpty() && arr[qMin.peekLast()] >= arr[right]) {
                    qMin.pollLast()
                }
                qMin.addLast(right)
                //保证当前最大值队列由大到小排列，当前值如果比链表最后值大，那么就需要弹出链表最后值
                while (!qMax.isEmpty() && arr[qMax.peekLast()] <= arr[right]) {
                    qMax.pollLast()
                }
                qMax.addLast(right)
                //如果当前窗口的最大值 - 最小值 > num ,说明以当前left为左边界的窗口内不可能再有符合条件的子数组了，直接结束循环
                if (arr[qMax.first] - arr[qMin.first] > num) {
                    break
                }
                right++
            }
            //判断链表的头是否已经不在窗口内了，如果不在，则需要弹出
            if (qMin.peekFirst() == left) {
                qMin.pollFirst()
            }
            //判断链表的头是否已经不在窗口内了，如果不在，则需要弹出
            if (qMax.peekFirst() == left) {
                qMax.pollFirst()
            }
            //一次性计算出窗口内的子数组数量
            result += right - left
            left++
        }
        return result
    }
}