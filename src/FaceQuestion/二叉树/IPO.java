package FaceQuestion.二叉树;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 做项目问题，你每做完一个项目,马上获得的收益,可以支持你去做下一个项目。
 * 思路：
 * 创建一个大根堆和一个小根堆;
 * 首先将每个项目按照成本存储到小根堆中，和初始成本比较，比初始成本小的项目弹出
 * 然后按照利润存储到大根堆中，然后每次选择从大根堆中弹出元素，将利润加到初始成本中
 * 初始成本改变之后，再次从小根堆中选择元素弹出
 */
public class IPO {

    /**
     * 项目类
     * p：项目利润
     * c：项目成本
     */
    public static class Node{
        public int p;
        public int c;

        public Node(int p,int c){
            this.p=p;
            this.c=c;
        }
    }

    /**
     * 成本小根堆比较器
     */
    public static class MinCostComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o1.c - o2.c;
        }

    }

    /**
     * 利润大根堆比较器
     */
    public static class MaxProfitComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o2.p - o1.p;
        }
    }

    /**
     * 找到利润最大的方案
     * @param k 规定完成的项目数
     * @param W 初始资金
     * @param profits 利润数组
     * @param Capital 成本数组
     * @return
     */
    public static int findMaximizedCapital(int k,int W,int[] profits,int[] Capital){

        Node[] nodes=new Node[profits.length];
        //初始化所有项目
        for (int i=0;i<profits.length;i++){
            nodes[i]=new Node(profits[i],Capital[i]);
        }

        //成本小根堆
        PriorityQueue<Node> minCosts=new PriorityQueue<>(new MinCostComparator());

        //利润大根堆
        PriorityQueue<Node> maxProfits=new PriorityQueue<>(new MaxProfitComparator());
        //一开始将所有元素存储到成本小根堆中
        for (int i=0;i<nodes.length;i++){
            minCosts.add(nodes[i]);
        }
        //找到成本符合要求的项目，按照利润存储到大根堆中
        for (int i=0;i<k;i++){
            while (!minCosts.isEmpty()&&minCosts.peek().c<=W){
                maxProfits.add(minCosts.poll());
            }
            if (maxProfits.isEmpty()){
                return W;
            }
            W+=maxProfits.poll().p;
        }
        return W;
    }

}
