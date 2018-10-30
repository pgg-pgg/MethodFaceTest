package FaceQuestion.图.最小生成树算法;


import FaceQuestion.图.Edge;
import FaceQuestion.图.Graph;
import FaceQuestion.图.Node;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * prim算法
 */
public class Prim {

    public static class EdgeComparator implements Comparator<Edge> {

        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }

    }

    public static Set<Edge> primMST(Graph graph){
        //边的小根堆
        PriorityQueue<Edge> priorityQueue=new PriorityQueue<>(new EdgeComparator());
        HashSet<Node> set=new HashSet<>();
        Set<Edge> result=new HashSet<>();
        for (Node node:graph.nodes.values()){
            if (!set.contains(node)){
                set.add(node);
                for (Edge edge:node.edges){
                    //将node所有边加入小根堆
                    priorityQueue.add(edge);
                }
                while (!priorityQueue.isEmpty()){
                    //从当前节点的所有边中选择权重最小的一条边
                    Edge edge=priorityQueue.poll();
                    Node toNode=edge.to;
                    //考察最小权重边的出节点是否已经存在与集合中
                    //如果不存在，那么就加入到集合中
                    //如果已经存在，那么忽略
                    if (!set.contains(toNode)){
                        set.add(toNode);
                        result.add(edge);
                        for (Edge nextEdge:node.edges){
                            priorityQueue.add(nextEdge);
                        }
                    }
                }
            }
        }
        return result;
    }
}
