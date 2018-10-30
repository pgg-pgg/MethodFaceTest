package FaceQuestion.一些算法

/**
 * 求一个字符串的最大回文字串
 */
object Manacher {

    fun manacherString(str: String): CharArray {
        val length = str.length * 2 + 1
        val charArray = CharArray(length)
        //格式化字符串
        //eg: "abckdeflv"  ->  "#a#b#c#k#d#e#f#l#v#,
        //为了解决字符串奇偶的问题
        for (i in 0 until length) {
            if (i % 2 == 0) charArray[i] = '#' else charArray[i] = str[i / 2]
        }

        println(charArray.joinToString())
        return charArray
    }

    fun manacher(str: String): Int {
        if (str.isEmpty()) return 0
        val manacherCharArray = manacherString(str)
        //回文字串的最右边位置
        var maxR = -1
        //回文字串的中心点位置
        var mid = -1
        //已知字串的回文半径数
        val pArr = IntArray(manacherCharArray.size)
        //最大的回文半径数
        var max = Int.MIN_VALUE
        for (i in 0 until pArr.size) {
            //如果当前位置在最长右边界内部，那么当前位置的最长回文字串长度就是对称点和当前点到右边界的距离中的较小值
            //如果在外部，那么就需要暴力外扩
            pArr[i] = if (i < maxR) Math.min(maxR - i, pArr[2 * mid - i]) else 1
            //当i外扩没有越界时
            while (i + pArr[i] < pArr.size && i - pArr[i] > -1) {
                //如果当前外扩的位置的字符是回文的话，那么直接++，否则直接结束外扩
                if (manacherCharArray[i + pArr[i]] == manacherCharArray[i - pArr[i]]) {
                    pArr[i]++
                } else {
                    break
                }
            }
            //更新回文字串的最右边位置和回文字串的中心点位置
            if (i + pArr[i] > maxR) {
                maxR = i + pArr[i]
                mid = i
            }
            max = Math.max(max, pArr[i])
        }
        println(max - 1)
        return max - 1
    }


}