
/**
 * 之字形打印矩阵
 * 设置三个观察点，t点，r点，end点
 * t点起始点在（0,0）不断向右移动，移动到边界之后，再向下移动
 * r点起始点在（0,0）不断向下移动，移动到边界之后，再向右移动
 * end点一直在右下角
 */
object ZigPrintMatrix {

    fun printMatrixZigZag(matrix: Array<IntArray>) {
        var a = 0//不断向右移动点的y坐标
        var b = 0//不断向右移动点的x坐标
        var c = 0//不断向下移动点的y坐标
        var d = 0//不断向下移动点的x坐标
        val endR = matrix.size - 1
        val endC = matrix[0].size - 1
        var fromUp = false//判断移动的方向
        while (a != endR + 1) {//打印结束条件，t点向下移动到越界
            printLevel(matrix, a, b, c, d, fromUp)//根据方向打印对角线的方法
            //通过不断改变t点和r点的坐标，使得t点和r点处在对角线两端
            a = if (b == endC) a + 1 else a//t点的y坐标起初为0，到达右边界之后，不断+1
            b = if (b == endC) b else b + 1//t点x坐标起初不断+1，到达右边界之后，不变
            d = if (c == endR) d + 1 else d//r点x坐标起初微微0，到达下边界后，不断+1
            c = if (c == endR) c else c + 1//r点y坐标起初不断+1，到达下边界后，不变
            fromUp = !fromUp//一条对角线打印完之后，直接改变方向
        }
        println()
    }

    /**
     * 打印t点和r点之间的点的方法
     * @param m
     * @param a
     * @param b
     * @param c
     * @param d
     * @param f
     */
    fun printLevel(m: Array<IntArray>, a: Int, b: Int, c: Int, d: Int,
                   f: Boolean) {
        var a = a
        var b = b
        var c = c
        var d = d
        if (f) {
            while (a != c + 1) {
                print(m[a++][b--].toString() + " ")
            }
        } else {
            while (c != a - 1) {
                print(m[c--][d++].toString() + " ")
            }
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val matrix = arrayOf(intArrayOf(1, 2, 3, 4), intArrayOf(5, 6, 7, 8), intArrayOf(9, 10, 11, 12))
        printMatrixZigZag(matrix)

    }

}
