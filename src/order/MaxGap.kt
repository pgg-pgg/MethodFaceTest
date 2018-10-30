package order

/**
 * 获取数组中相邻数差的最大值
 * 解题思路：准备N+1个桶，
 * 每个桶把数组元素的范围平分，
 * 第一个桶和最后一个桶必不为空，
 * 而中间必有桶是空的，
 * 那么各个空桶前一个桶的最大值和空桶后一个桶的最小值的差的最大值就是最大差值;
 */
class MaxGap {

    /**
     * 获取最大差值方法
     * @param nums 给定数组
     * @return
     */
    fun maxGap(nums: IntArray?): Int {
        if (nums == null || nums.size < 2) {
            return 0
        }
        val len = nums.size//数组长度
        var min = Integer.MAX_VALUE//初始化一个最小值
        var max = Integer.MIN_VALUE//初始化一个最大值
        //获取数组元素值的范围
        for (i in 0 until len) {
            min = Math.min(min, nums[i])
            max = Math.max(max, nums[i])
        }
        //最大值=最小值，数组元素为一个定值，最大差值为0
        if (min == max) {
            return 0
        }
        //记录每个桶是否有元素
        val hasNum = BooleanArray(len + 1)
        //记录各个桶中元素的最大值
        val maxs = IntArray(len + 1)
        //记录各个桶中元素的最小值
        val mins = IntArray(len + 1)
        var bid = 0//桶序号
        for (i in 0 until len) {
            bid = bucket(nums[i].toLong(), len.toLong(), min.toLong(), max.toLong())
            //更新桶中的最大值，最小值
            mins[bid] = if (hasNum[bid]) Math.min(mins[bid], nums[i]) else nums[i]
            maxs[bid] = if (hasNum[bid]) Math.max(maxs[bid], nums[i]) else nums[i]
            hasNum[bid] = true//桶中有元素
        }
        var res = 0
        var lastMax = maxs[0]//上一个非空桶的最大值
        //后一个非空桶的最小值-前一个桶的最大值为最大差值
        for (i in 1 until len) {
            if (hasNum[i]) {
                res = Math.max(res, mins[i] - lastMax)
                lastMax = maxs[i]
            }
        }
        return res
    }

    /**
     * 通过传惨计算出数组中的该元素应该进入到哪一个桶中
     * @param num 当前遍历到的元素
     * @param len 数组长度
     * @param min 数组的左范围（数组的最小值）
     * @param max 数组的右范围（数组的最大值）
     * @return 进入桶的序号
     */
    private fun bucket(num: Long, len: Long, min: Long, max: Long): Int {
        return ((num - min) * len / (max - min)).toInt()
    }
}
