package FaceQuestion.数组_栈_队列


object 转圈打印矩阵 {

    fun printMartrix(matrix: Array<IntArray>) {
        var tRow = 0//左上角x坐标
        var tCol = 0//左上角y坐标
        var dRow = matrix.size - 1//右下角x坐标
        var dCol = matrix[0].size - 1//右下角y坐标

        while (tRow <= dRow && tCol <= dCol) {//当还没有到达中点时，不断转圈打印
            printEdge(matrix, tRow++, tCol++, dRow--, dCol--)
        }
    }

    fun printEdge(martrix: Array<IntArray>, a: Int, b: Int, c: Int, d: Int) {
        if (a == c) {
            for (i in b..d) {
                print(martrix[a][i].toString() + " ")
            }
        } else if (b == d) {
            for (i in a..c) {
                print(martrix[i][b].toString() + " ")
            }
        } else {
            for (i in b until d) {
                print(martrix[a][i].toString() + " ")
            }
            for (i in a until c) {
                print(martrix[i][d].toString() + " ")
            }
            for (i in d downTo (b + 1)) {
                print(martrix[c][i].toString() + " ")
            }
            for (i in c downTo (a + 1)) {
                print(martrix[i][b].toString() + " ")
            }
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val matrix = arrayOf(intArrayOf(1, 2, 3, 4), intArrayOf(5, 6, 7, 8), intArrayOf(9, 10, 11, 12), intArrayOf(13, 14, 15, 16))
        printMartrix(matrix)
    }
}