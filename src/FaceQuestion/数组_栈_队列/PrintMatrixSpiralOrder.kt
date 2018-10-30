package FaceQuestion.数组_栈_队列

/**
 * 打印矩阵问题
 * 转圈，之字形打印
 *
 */
class PrintMatrixSpiralOrder {

    fun spiralOrderPrint(matrix: Array<IntArray>) {

        var tRow = 0//左上角x坐标
        var tCol = 0//左上角y坐标
        var dRow = matrix.size - 1//右下角x坐标
        var dCol = matrix[0].size - 1//右下角y坐标

        while (tRow <= dRow && tCol <= dCol) {//当还没有到达中点时，不断转圈打印
            printEdge(matrix, tRow++, tCol++, dRow--, dCol--)
        }
    }

    private fun printEdge(matrix: Array<IntArray>, tRow: Int, tCol: Int, dRow: Int, dCol: Int) {
        if (tRow == dRow) {//到了右边界
            for (i in tCol..dCol) {
                println(matrix[tRow][i].toString() + " ")
            }
        } else if (tCol == dCol) {//到了下边界
            for (i in tRow until dRow) {
                println(matrix[i][tCol].toString() + " ")
            }
        } else {
            var curC = tCol
            var curR = tRow
            while (curC != dCol) {
                print(matrix[tRow][curC].toString() + " ")
                curC++
            }
            while (curR != dRow) {
                print(matrix[curR][dCol].toString() + " ")
                curR++
            }
            while (curC != tCol) {
                print(matrix[dRow][curC].toString() + " ")
                curC--
            }
            while (curR != tRow) {
                print(matrix[curR][tCol].toString() + " ")
                curR--
            }
        }

    }

}
