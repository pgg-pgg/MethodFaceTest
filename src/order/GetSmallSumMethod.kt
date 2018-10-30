package order

/**
 * 获取数组中的小和数
 * 例：[1,2,6,8,3,7,0,2,3,5]
 * 所有小和数为：1+(1+2）+（1+2+6）+(1+2）+(1+2+6+3）+0+1+(1+2+2)+(1+2+2+3+3)
 * =1+3+9+3+12+1+5+11=45
 */
class GetSmallSumMethod {

    fun getSmallSum(a: IntArray?): Int {
        return if (a == null || a.size < 2) {
            0
        } else mergeProgress(a, 0, a.size - 1)
    }

    //递归调用
    private fun mergeProgress(a: IntArray, l: Int, r: Int): Int {
        if (l == r) {
            return 0
        }
        val mid = l + (r - l shr 1)
        return (mergeProgress(a, l, mid)//计算出左半边的所有小和数

                + mergeProgress(a, mid + 1, r)//右边

                + merge(a, l, mid, r))//总共
    }

    private fun merge(a: IntArray, l: Int, mid: Int, r: Int): Int {
        val help = IntArray(r - l + 1)
        var i = 0
        var p1 = l
        var p2 = mid + 1
        var res = 0
        while (p1 <= mid && p2 <= r) {
            res += if (a[p1] < a[p2]) (r - p2 + 1) * a[p1] else 0
            help[i++] = if (a[p1] < a[p2]) a[p1++] else a[p2++]
        }
        while (p1 <= mid) {
            help[i++] = a[p1++]
        }
        while (p2 <= r) {
            help[i++] = a[p2++]
        }
        i = 0
        while (i < help.size) {
            a[l + i] = help[i]
            i++
        }
        return res
    }
}
