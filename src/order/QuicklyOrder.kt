package order


/**
 * 快速排序，选取一个划分点，经典的是选取数组的最后一位作为划分点，然后将数组分为小于划分点的放在前面，等于划分点的放在中间，大于划分点的放在后面
 * 然后，不断递归调用，最终实现排序
 * 经典快排（永远选取数组最后一个值作为划分点）的时间复杂度为:
 * 最好情况下可以达到O(N*logN)
 * 最坏情况下可以达到O(N^2)
 * 随机快排的时间复杂度的数学期望可以达到O(N*logN)
 *
 * 其空间复杂度为O(logN)
 *
 * 划分过程：
 * 1.当前值curr<划分值 小于区域的下一个值与curr交换，并将小于区域向右扩大一个位置，curr向右移动一个位置
 * 2.当前值curr=划分值 curr直接向右移动一个位置
 * 3.当前值curr>划分值 大于区域的前一个值与curr交换，并将大于区域向左扩大一个位置，curr的位置不变
</划分值> */
class QuicklyOrder {


    fun quickOrder(a: IntArray?) {
        if (a == null || a.size < 2) {
            return
        }

        quickOrder(a, 0, a.size - 1)
    }

    /**
     * 快排的主方法，调用递归
     * @param a 给定的数组
     * @param l 数组左边界
     * @param r 数组右边界
     */
    private fun quickOrder(a: IntArray, l: Int, r: Int) {
        if (l < r) {//递归调用的出口
            //随机快速排序，随机选取一个划分值，并让这个划分值和数组最后一位交换
            //随机快排可以做到时间复杂度的期望为O(N*logN)
            swap(a, l + (Math.random() * (r - l + 1)).toInt(), r)
            val p = partition(a, l, r)//划分区域
            quickOrder(a, l, p[0] - 1)//递归划分小于区域
            quickOrder(a, p[1] + 1, r)//递归划分大于区域
        }
    }


    /**
     * 快排的主要思想过程，划分区域的方法
     * @param a 给定的数组
     * @param l 数组左边界
     * @param r 数组右边界
     * @return 划分好区域的数组中相等区域的左右边界
     */
    private fun partition(a: IntArray, l: Int, r: Int): IntArray {
        var cur = l
        var less = l - 1//小于区域的初始右边界，不包含数组第一个数
        var more = r//大于区域的初始左边界，包含了数组的最后一个数

        while (cur < more) {//循环的结束条件是当前值与大于区域的左边界碰撞
            if (a[cur] < a[r]) {//当前值<划分值时
                //当前值与小于区域的下一个值交换，小于区域+1，当前值向右+1
                swap(a, ++less, cur++)
            } else if (a[cur] > a[r]) {//当前值>划分值时
                //当前值与大于区域的前一个值交换，大于区域+1，当前值不变
                swap(a, --more, cur)
            } else {//当前值=划分值时
                //当前值直接+1
                cur++
            }
        }
        //最后将数组最后一个值与大于区域的第一个值交换
        swap(a, more, r)
        return intArrayOf(less + 1, more)
    }


    private fun swap(arr: IntArray, i: Int, i1: Int) {
        val temp = arr[i]
        arr[i] = arr[i1]
        arr[i1] = temp
    }

}
