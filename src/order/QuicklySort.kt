package order

class QuicklySort {


    fun quicksort(a: IntArray, l: Int, r: Int) {
        if (l < r) {

            //随机选择排序
            swap(a, (l + Math.random() * (r - l + 1)).toInt(), r)
            val part = parttion(a, l, r)
            quicksort(a, l, part[0] - 1)
            quicksort(a, part[1] + 1, r)
        }
    }

    private fun parttion(a: IntArray, l: Int, r: Int): IntArray {
        var l = l
        var less = l - 1
        var more = r
        while (l < more) {
            if (a[l] < a[r]) {
                swap(a, ++less, l++)
            } else if (a[l] == a[r]) {
                l++
            } else {
                swap(a, --more, l)
            }
        }
        swap(a, more, r)
        return intArrayOf(less + 1, more)
    }


    fun swap(a: IntArray, i: Int, j: Int) {
        val temp = a[i]
        a[i] = a[j]
        a[j] = temp
    }
}
