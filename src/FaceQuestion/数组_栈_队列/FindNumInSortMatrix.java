package FaceQuestion.数组_栈_队列;

/**
 * 在已排好序的矩阵中判断一个数是否存在
 */
public class FindNumInSortMatrix {

    public boolean isContains(int[][] matrix,int num){
        int row = 0;
        int col = matrix[0].length - 1;
        while (row < matrix.length && col > -1) {
            if (matrix[row][col] == num) {
                return true;
            } else if (matrix[row][col] > num) {
                col--;
            } else {
                row++;
            }
        }
        return false;
    }
}
