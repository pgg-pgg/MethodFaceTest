package order

/**
 * 选择排序
 * 时间复杂度O(N^2)，额外空间复杂度O(1)
 */
object SelectOrder {

    fun selectOrder(arr: IntArray): IntArray {

        if (arr.size < 2) {
            return arr
        }

        //每次在数组寻找到最小的值放在数组的最前部分
        for (i in arr.indices) {
            var min = i
            for(j in i+1 until arr.size) {
                min = if (arr[j] < arr[min]) j else min
            }
            OrderLogarithm.swap(arr, i, min)
        }
        return arr
    }
}
