package FaceQuestion.图;

import java.util.*;

/**
 * 图的拓扑排序算法
 * 1.遍历所有节点，找到所有入度为0 的节点
 * 2.将所有入度为0的节点的邻居节点的入度减1，那么就会产生新的入度为0的节点
 */
public class TopologySort {

    /**
     * 拓扑排序算法
     * @param graph
     * @return
     */
    public List<Node> sortedTopology(Graph graph){
        HashMap<Node,Integer> inMap=new HashMap<>();//所有节点的入度哈希表
        Queue<Node> zeroInQueue=new LinkedList<>();//所有入度为0 节点的哈希表
        for (Node node:graph.nodes.values()){
            inMap.put(node,node.in);//将所有节点的入度存储
            if (node.in==0){
                zeroInQueue.add(node);//将所有入度为0的节点存储
            }
        }

        List<Node> result=new ArrayList<>();//结果集
        while (!zeroInQueue.isEmpty()){
            //遍历所有入度为0 的节点的邻居节点，并将其入度减1
            Node cur=zeroInQueue.poll();
            result.add(cur);
            for (Node next:cur.nexts){
                inMap.put(next,inMap.get(next)-1);
                if (inMap.get(next)==0){
                    zeroInQueue.add(next);
                }
            }
        }
        return result;
    }
}
