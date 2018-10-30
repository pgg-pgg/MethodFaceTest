package FaceQuestion.二叉树;

/**
 * 在数组中找到一个局部最小的位置
 * 假设有N个数，先判断两段的数，0和N-1
 * 0如果比1位置上的数小，那么就是局部最小直接返回
 * N-1如果比N-2位置上的数小，那么就是局部最小，直接返回
 * 如果两段不存在局部最小，那么数组向右方向是递减，向左方向也是递减
 * 那么在0-N-1之间必存在局部最小，此时使用折半查找去寻找
 * 先找到中间位置，判断中间位置是否是局部最小，如果不是，那么就再次使用折半去查找
 */
public class FindOneLessValueIndex {

    public static int getLessIndex(int[] arr){
        if (arr==null||arr.length==0){
            return -1;
        }

        if (arr.length==1||arr[0]<arr[1]){
            return 0;
        }

        if (arr[arr.length-1]<arr[arr.length-2]){
            return arr.length-1;
        }

        int left=1;
        int right=arr.length-2;
        int mid=0;
        while (left<right){
            mid=(left+right)/2;
            if (arr[mid]>arr[mid-1]){
                right=mid-1;
            }else if (arr[mid]>arr[mid+1]){
                left=mid+1;
            }else {
                return mid;
            }
        }
        return left;
    }
}
