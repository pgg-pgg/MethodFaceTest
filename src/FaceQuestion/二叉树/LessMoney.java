package FaceQuestion.二叉树;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 一块金条切成两半,是需要花费和长度数值一样的铜板的。比如长度为20的
 * 金条,不管切成长度多大的两半,都要花费20个铜板。一群人想整分整块金
 * 条,怎么分最省铜板?
 * 思路：
 * 利用小根堆，将需要划分的范围存储到小根堆中，每次从小根堆中弹出两个元素，将它们相加之后记录下
 * 然后将记录值再扔回堆中，再次返回两个值，一直递归，直到堆中无元素，记录值的和就是答案
 */
public class LessMoney {

    public static int lessMoney(int[] arr){
        PriorityQueue<Integer> pQ=new PriorityQueue<>(new MinheapComparator());
        for (int i=0;i<arr.length;i++){
            pQ.add(arr[i]);
        }
        int sum=0;
        int cur=0;
        while (pQ.size()>1){
            cur=pQ.poll()+pQ.poll();
            sum+=cur;
            pQ.add(cur);
        }
        return sum;
    }


    public static class MinheapComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }

    }
}
