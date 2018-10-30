package FaceQuestion.数组_栈_队列

object 旋转矩阵 {


    fun rotateMatrix(matrix: Array<IntArray>) {
        var tR = 0//左上角x坐标
        var tC = 0//左上角y坐标
        var dR = matrix.size - 1//右下角x坐标
        var dC = matrix[0].size - 1//右下角y坐标
        while (tR < dR) {
            rotateEdge(matrix, tR++, tC++, dR--, dC--)
        }

    }
    fun rotateEdge(matrix: Array<IntArray>, a: Int, b: Int, c: Int, d: Int) {
        var times = c - a
        var temp = 0
        for (i in 0 until times) {
            temp = matrix[a][b + i]
            matrix[a][b + i] = matrix[c - i][b]
            matrix[c - i][b] = matrix[c][d - i]
            matrix[c][d - i] = matrix[a + i][d]
            matrix[a + i][d] = temp
        }
    }

    fun printMatrix(matrix: Array<IntArray>) {
        for (i in matrix.indices) {
            for (j in 0 until matrix[0].size) {
                print(matrix[i][j].toString() + " ")
            }
            println()
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val matrix = arrayOf(intArrayOf(1, 2, 3, 4), intArrayOf(5, 6, 7, 8), intArrayOf(9, 10, 11, 12), intArrayOf(13, 14, 15, 16))
        printMatrix(matrix)
        rotateMatrix(matrix)
        println("=========")
        printMatrix(matrix)

    }
}