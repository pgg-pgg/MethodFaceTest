package order

/**
 * 插入排序
 * 时间复杂度O(N^2)，额外空间复杂度O(1)
 */
object InsertOrder {
    fun insertOrder(arr: IntArray): IntArray {
        if (arr.size < 2) {
            return arr
        }

        //i代表不断右移的指针，i的左边总是有序的，j在0～i-1内遍历，将i位置元素插入到左边数组，保证左边数组有序
        for (i in 1 until arr.size) {
            for (j in i downTo 1) {
                if (arr[j] < arr[j - 1]) {
                    OrderLogarithm.swap(arr, j, j - 1)
                }
            }
        }
        return arr
    }

}
