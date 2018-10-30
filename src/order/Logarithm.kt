package order

/**
 * 对数器
 * 对数器的概念和使用
 * 0，有一个你想要测的方法a，
 * 1，实现一个绝对正确但是复杂度不好的方法b，
 * 2，实现一个随机样本产生器
 * 3，实现比对的方法
 * 4，把方法a和方法b比对很多次来验证方法a是否正确。
 * 5，如果有一个样本使得比对出错，打印样本分析是哪个方法出 错
 * 6，当样本数量很多时比对测试依然正确，可以确定方法a已经 正确。
 */
object OrderLogarithm {

    /**
     * 随机数组生成器
     */
    private fun dataCreateFactory(value: Int, size: Int): IntArray {
        //随机生成数组长度为size内大小的数据
        val array = IntArray(((size + 1) * Math.random()).toInt())
        for (i in 0..(array.size - 1)) {
            val a = ((value + 1) * Math.random()).toInt() - ((value) * Math.random()).toInt()
            array[i] = a
        }
        return array
    }

    fun test(time: Int, maxSize: Int, maxValue: Int, listener: IOrderListener) {
        var isRight = true
        for (i in 0..time) {
            val a = dataCreateFactory(maxValue, maxSize)
            val b = a.copyOf()
            val c = a.copyOf()
            val d = listener.order(b)
            a.sort()
            if (!d.contentEquals(a)) {
                isRight = false
                println("未通过"+ "出错样本为："+ c.contentToString())
                return
            }
        }
        if (isRight) {
            println("您已通过全部测试")
        }
    }
    fun swap(arr: IntArray, i: Int, i1: Int) {
        val temp = arr[i]
        arr[i] = arr[i1]
        arr[i1] = temp
    }

    fun swapList(arr: ArrayList<Int>, i: Int, i1: Int) {
        val temp = arr[i]
        arr[i] = arr[i1]
        arr[i1] = temp
    }
}
interface IOrderListener {
    fun order(array: IntArray): IntArray
}