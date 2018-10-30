package FaceQuestion.图.最小生成树算法;


import FaceQuestion.二叉树.UnionFind;
import FaceQuestion.图.Edge;
import FaceQuestion.图.Graph;
import FaceQuestion.图.Node;

import java.util.*;

/**
 * 最小生成树Kruskal算法
 * 遍历所有边，依次选择最小权重的边，判断是否存在回路，如果不存在，那么就选择
 * 使用并查集判断是否存在回路问题，边的入节点和出节点是否在一个集合中
 */
public class Kruskal {

    // Union-Find Set
    public static class UnionFind {
        private HashMap<Node, Node> fatherMap;
        private HashMap<Node, Integer> rankMap;

        public UnionFind() {
            fatherMap = new HashMap<Node, Node>();
            rankMap = new HashMap<Node, Integer>();
        }

        private Node findFather(Node n) {
            Node father = fatherMap.get(n);
            if (father != n) {
                father = findFather(father);
            }
            fatherMap.put(n, father);
            return father;
        }

        public void makeSets(Collection<Node> nodes) {
            fatherMap.clear();
            rankMap.clear();
            for (Node node : nodes) {
                fatherMap.put(node, node);
                rankMap.put(node, 1);
            }
        }

        public boolean isSameSet(Node a, Node b) {
            return findFather(a) == findFather(b);
        }

        public void union(Node a, Node b) {
            if (a == null || b == null) {
                return;
            }
            Node aFather = findFather(a);
            Node bFather = findFather(b);
            if (aFather != bFather) {
                int aFrank = rankMap.get(aFather);
                int bFrank = rankMap.get(bFather);
                if (aFrank <= bFrank) {
                    fatherMap.put(aFather, bFather);
                    rankMap.put(bFather, aFrank + bFrank);
                } else {
                    fatherMap.put(bFather, aFather);
                    rankMap.put(aFather, aFrank + bFrank);
                }
            }
        }
    }
    public static class EdgeComparator implements Comparator<Edge> {

        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }

    }

    public Set<Edge> kruskalMST(Graph graph){
        UnionFind unionFind=new UnionFind();
        unionFind.makeSets(graph.nodes.values());
        //将边的权重按小根堆存储
        PriorityQueue<Edge> priorityQueue=new PriorityQueue<>(new EdgeComparator());

        for (Edge edge:graph.edges){
            priorityQueue.add(edge);
        }

        Set<Edge> result=new HashSet<>();
        while (!priorityQueue.isEmpty()){
            Edge edge=priorityQueue.poll();
            if (!unionFind.isSameSet(edge.from,edge.to)){
                //如果边的入节点和出节点不是同一个集合的，那么就意味着不是一个环
                //那么就可一选择这条边作为最小生成树的一个边
                result.add(edge);
                unionFind.union(edge.from,edge.to);
            }
        }
        return result;
    }

}
