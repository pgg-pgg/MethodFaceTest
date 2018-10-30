package FaceQuestion.图;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 图的最短路径算法
 * Dijkstra算法
 */
public class Dijkstra {


    public static HashMap<Node,Integer> dijkstra1(Node head){
        //所有节点对应的路径长度和
        HashMap<Node,Integer> distanceMap=new HashMap<>();
        //头结点路径为0
        distanceMap.put(head,0);
        //已经被选择过的节点集合
        HashSet<Node> selectedNodes=new HashSet<>();

        //获取当前节点下一个最短路径的节点
        Node minNode=getMinDistanceAndUnselectedNode(distanceMap,selectedNodes);

        while (minNode!=null){
            int distance=distanceMap.get(minNode);//获取当前最短路径长度
            //比较当前节点的下一个节点的权重值，找出下一个最短路径节点
            for (Edge edge:minNode.edges){
                Node toNode=edge.to;
                if (!distanceMap.containsKey(toNode)){
                    distanceMap.put(toNode,distance+edge.weight);
                }
                distanceMap.put(edge.to,Math.min(distanceMap.get(toNode),distance+edge.weight));
            }
            selectedNodes.add(minNode);
            minNode=getMinDistanceAndUnselectedNode(distanceMap,selectedNodes);
        }
        return distanceMap;
    }

    /**
     * 在未被选择的节点中选择拥有最短路径的节点
     * @param distanceMap 所有节点的集合
     * @param selectedNodes 已经选择过节点集合
     * @return
     */
    private static Node getMinDistanceAndUnselectedNode(HashMap<Node, Integer> distanceMap, HashSet<Node> selectedNodes) {

        Node minNode=null;
        int minDistance=Integer.MAX_VALUE;
        for (Map.Entry<Node,Integer> entry:distanceMap.entrySet()){
            Node node=entry.getKey();
            int distance=entry.getValue();
            if (!selectedNodes.contains(node)&&distance<minDistance){
                minNode=node;
                minDistance=distance;
            }
        }

        return minNode;
    }


}
