package order


/**
 * 堆排序
 * 1.先将数组调整为大根堆，这个过程的时间复杂度为O(N)
 * 2.调整过后，将堆顶元素（最大值）与最后一个元素交换，将堆大小自减一个，然后再次调整为大根堆
 * 排序过程的时间复杂度为O(N*logN)
 * 空间复杂度为O(1)
 */
class HeapOrder {

    fun heapOrder(a: IntArray?) {
        if (a == null || a.size < 2) {
            return
        }

        //第一步，先将数组调整为大根堆
        for (i in a.indices) {
            heapInsert(a, i)
        }

        //第二步，首尾交换，并把堆大小自减1
        var heapSize = a.size
        swap(a, 0, --heapSize)
        //当堆大小大于0时，调整当前堆为大根堆
        while (heapSize > 0) {
            heapify(a, 0, heapSize)
            swap(a, 0, --heapSize)
        }
    }


    /**
     * 调整数组为大根堆
     * @param a 给定的数组
     * @param i 当前的数组元素下标
     */
    private fun heapInsert(a: IntArray, i: Int) {
        var cur = i
        while (a[cur] > a[(cur - 1) / 2]) {
            //循环条件是当前元素的大小大于其父节点的大小
            //那么就交换两者位置
            swap(a, cur, (cur - 1) / 2)
            //把当前位置变为父节点位置，再次比较
            cur = (cur - 1) / 2
        }
    }

    /**
     * 堆排序
     * @param a
     * @param index
     * @param size
     */
    private fun heapify(a: IntArray, index: Int, size: Int) {
        var curIndex = index
        var left = curIndex * 2 + 1//获取此位置的左孩子
        while (left < size) {            //当左孩子不越界
            //获取子节点中较大的一个的下标
            var largest = if (left + 1 < size && a[left + 1] > a[left]) left + 1 else left
            //比较父子节点，选取最大节点的下标作为largest
            largest = if (a[largest] > a[curIndex]) largest else curIndex
            if (largest == curIndex) {
                //如果最大值和当前值的下标相等
                //那么代表不需要调整
                break
            }
            //如果需要调整，那么就将较大元素和当前元素交换
            swap(a, largest, curIndex)
            //将调整后的堆的下标重新赋值
            curIndex = largest
            left = curIndex * 2 + 1
        }
    }

    companion object {


        fun swap(arr: IntArray, i: Int, i1: Int) {
            val temp = arr[i]
            arr[i] = arr[i1]
            arr[i1] = temp
        }
    }
}
