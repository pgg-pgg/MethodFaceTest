package FaceQuestion.二叉树;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 随时找到数据流的中位数
 * 思路：
 * 利用一个大根堆和一个小根堆去保存数据,保证前一半的数放在大根堆，后一半的数据放在小根堆中
 * 在添加数据的时候，不断的调整两个堆的大小，使两者保持平衡
 * 要取得的中位数就是两个堆堆顶的元素
 */
public class MadianQuick {

    public static class MedianHolder {
        private PriorityQueue<Integer> maxQueue=new PriorityQueue<>(new MaxComparator());
        private PriorityQueue<Integer> minQueue=new PriorityQueue<>(new MinComparator());


        /**
         * 调整堆的大小
         * 当两个堆的大小差值变大时，从数据多的堆中弹出一个数据进入另一个堆中
         */
        public void modifyHeapSize(){
            if (maxQueue.size()==minQueue.size()+2){
                minQueue.add(maxQueue.poll());
            }
            if (minQueue.size()==maxQueue.size()+2){
                maxQueue.add(minQueue.poll());
            }
        }

        /**
         * 添加数据的过程
         * @param num
         */
        public void addNum(Integer num){
            if (maxQueue.isEmpty()){
                maxQueue.add(num);
            }
            if (this.maxQueue.peek() >= num) {
                this.maxQueue.add(num);
            } else {
                if (this.minQueue.isEmpty()) {
                    this.minQueue.add(num);
                    return;
                }
                if (this.minQueue.peek() > num) {
                    this.maxQueue.add(num);
                } else {
                    this.minQueue.add(num);
                }
            }
            modifyHeapSize();
        }

        /**
         * 获取中位数
         * @return
         */
        public Integer getMedian(){
            int maxHeapSize=this.maxQueue.size();
            int minHeapSize=this.minQueue.size();
            if (maxHeapSize+minHeapSize==0){
                return null;
            }
            Integer maxHeapHead=this.maxQueue.peek();
            Integer minHeapHead=this.minQueue.peek();

            if (((maxHeapHead+minHeapHead)&1)==0){
                return (maxHeapHead+minHeapHead)/2;
            }
            return maxHeapSize>minHeapSize?maxHeapHead:minHeapHead;
        }

        /**
         * 大根堆比较器
         */
        class MaxComparator implements Comparator<Integer> {

            @Override
            public int compare(Integer o1, Integer o2) {
                 if (o2>o1){
                     return 1;
                 }else {
                     return -1;
                 }
            }
        }

        /**
         * 小根堆比较器
         */
        class MinComparator implements Comparator<Integer>{
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o2<o1){
                    return 1;
                }else {
                    return -1;
                }

            }
        }
    }

}
