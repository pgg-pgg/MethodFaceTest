package order

/**
 * 冒泡排序
 * 时间复杂度O(N^2)，额外空间复杂度O(1)
 */
object BubbleOrder {


    fun bubbleOrder(arr: IntArray): IntArray {
        if (arr.isEmpty() || arr.size < 2) {
            return arr
        }

        for (i in (arr.size - 1) downTo 0) {
            for (j in 0 until i) {
                if (arr[j] > arr[j+1]) {
                    OrderLogarithm.swap(arr, j, j + 1)
                }
            }
        }
        return arr
    }



}
