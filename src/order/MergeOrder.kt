package order

/**
 * 时间复杂度O(N*logN)
 * 空间复杂度O（N）
 */
object MergeOrder {

    fun mergeSort(a: IntArray?) {
        if (a == null || a.size < 2) {
            return
        }

        mergeSort(a, 0, a.size - 1)
    }

    /**
     * 递归排序
     * @param a
     * @param i
     * @param length
     */
    private fun mergeSort(a: IntArray, i: Int, length: Int) {

        //递归出口，当左边等于右边时，说明已经排好，递归结束
        if (i == length) {
            return
        }
        val mid = i + (length - i shr 1)//每次递归选取中点进行分割
        mergeSort(a, i, mid)//将中点左边排好
        mergeSort(a, mid + 1, length)//将中点右边排好
        merge(a, i, mid, length)
    }

    /**
     * 将排好序的左右两部分数组重排序到辅助数组中
     * @param a
     * @param l
     * @param mid
     * @param r
     */
    private fun merge(a: IntArray, l: Int, mid: Int, r: Int) {

        val help = IntArray(r - l + 1)//辅助数组
        var i = 0
        var p1 = l//p1代表在左边到中点之间移动的指针
        var p2 = mid + 1//p2代表在中点到右边之间移动的指针

        while (p1 <= mid && p2 <= r) {//当p1和p2都未越界时，把原数组按顺序拷贝到辅助数组中
            help[i++] = if (a[p1] < a[p2]) a[p1++] else a[p2++]
        }

        //如果右边先越界，把左边直接拷贝
        while (p1 <= mid) {
            help[i++] = a[p1++]
        }
        //如果左边先越界，把右边直接拷贝
        while (p2 <= r) {
            help[i++] = a[p2++]
        }

        //将辅助数组中的元素重新拷贝到原数组中
        i = 0
        while (i < help.size) {
            a[l + i] = help[i]
            i++
        }
    }


}