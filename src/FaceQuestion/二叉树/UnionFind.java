package FaceQuestion.二叉树;


import java.util.HashMap;
import java.util.List;

/**
 * 并查集
 */
public class UnionFind {

    public static class Node {
        // whatever you like
    }

    public static class DisjointSets {
        //存储的是一个节点和它的父节点的对应关系
        public HashMap<Node, Node> fatherMap;
        //存储的是一个节点如过是一个集合的特征节点，那么代表的就是特征节点和集合大小的对应关系
        public HashMap<Node, Integer> rankMap;


        public DisjointSets() {
            fatherMap = new HashMap<Node, Node>();
            rankMap = new HashMap<Node, Integer>();
        }

        /**
         * 初始化所有元素，使得每一个元素各成一个集合
         * @param nodes
         */
        public void makeSets(List<Node> nodes) {
            fatherMap.clear();
            rankMap.clear();
            for (Node node : nodes) {
                fatherMap.put(node, node);
                rankMap.put(node, 1);
            }
        }

        /**
         * 找到一个集合的特征节点
         * @param n
         * @return
         */
        public Node findFather(Node n) {
            Node father = fatherMap.get(n);
            if (father != n) {
                father = findFather(father);
            }
            fatherMap.put(n, father);
            return father;
        }

        /**
         *
         * @param a
         * @param b
         */
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

    public static void main(String[] args) {

    }
}
