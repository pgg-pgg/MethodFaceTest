package FaceQuestion.一些算法

object KMP {

    @JvmStatic
    fun main(args: Array<String>) {
        getIndexOf("aabaaabaaac", "aabaaac")
    }
    /**
     * 判断str1中是否包含str2，如果包含，那么返回在str1的起始位置，不包含则返回0
     */
    fun getIndexOf(str1: String, str2: String): Int {
        if (str2.isEmpty()) return 0
        if (str1.length < str2.length) return -1
        //获取str2的前后缀数组
        val nextArray = getIndexNext(str2)
        //str1对比的起始位置
        var str1Index = 0
        //str2对比的起始位置
        var str2Index = 0
        //当都不越界时
        while (str1Index < str1.length && str2Index < str2.length) {
            //当对比的值相等时，两者都移动到下一位
            if (str1[str1Index] == str2[str2Index]) {
                str1Index++
                str2Index++
            } else {
                //如果对比的值不想等
                str2Index = if (nextArray[str2Index] < 0) {
                    //如果当前str2中对比的字符的前后缀不存在
                    //直接将str1的下一位和str2的起始位置对比
                    str1Index++
                    0
                } else {
                    //如果当前str2中对比的字符的前后缀存在
                    //将str1的当前位置和str2的前缀后一位进行对比
                    nextArray[str2Index]
                }
            }
        }
        return if (str2Index == str2.length) str1Index - str2Index else -1
    }

    /**
     * 获取每个字符的前/后缀数
     * eg: 对于 ababcababc 这个字符串
     * 返回值应该是 [-1, 0, 0, 1, 2, 0, 1, 2, 3, 4]
     * 表示0位置上的a的前后缀数为-1
     * 1位置上的b的前后缀数为0
     * 2位置上的a的前后缀数为0
     * 3位置上的b的前后缀数为1 （前缀为0位置的a，后缀为2位置的a相等，所以为1）
     * 4位置上的c的前后缀数为2 （前缀为0和1位置上ab，后缀为2和3位置上的ab，所以为2）
     * ...
     */
    private fun getIndexNext(str: String): IntArray {
        //如果str的长度 <=1,那么直接返回[-1]
        if (str.length <= 1) return intArrayOf(-1)
        val next = IntArray(str.length)
        //定义0位置的字符的前后缀就是-1
        next[0] = -1
        //1位置的字符的前后缀为0
        next[1] = 0
        //从2位置开始计算
        var i = 2
        //当前位置的前一个位置的前后缀数
        var cn = 0
        while (i < next.size) {
            if (str[i - 1] == str[cn]) {
                //当前位置前一个位置的字符如果等于前缀数的下一个字符
                //当前位置的前缀数为前一个位置的前缀数+1
                //继续计算下一个字符
                //将cn++
                next[i] = cn + 1
                i++
                cn++
            } else {
                if (cn == 0) {
                    //当cn == 0 时，表示当前字符没有前后缀
                    //将当前字符的前后缀数置为0
                    //继续计算下一个字符
                    next[i] = 0
                    i++
                } else {
                    //当cn！=0时，表示还需要计算cn的位置
                    cn = next[cn]
                }
            }
        }
        println(next.contentToString())
        return next
    }
}