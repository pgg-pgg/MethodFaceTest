package FaceQuestion.图;


/**
 * 图的生成器
 */
public class GraphGenerator {

    /**
     * 图的生成方法
     * @param matrix 表示一条边的二维数组
     * @return 一张图
     */
    public static Graph createGraph(Integer[][] matrix){
        Graph graph=new Graph();
        for (int i=0;i<matrix.length;i++){
            Integer from=matrix[i][0];//边的入节点的key值
            Integer to=matrix[i][1];//边的出节点的key值
            Integer weight=matrix[i][2];//边的权重
            if (!graph.nodes.containsKey(from)){
                //当前节点不存在，就添加
                graph.nodes.put(from,new Node(from));
            }
            if (!graph.nodes.containsKey(to)){
                graph.nodes.put(to,new Node(to));
            }

            Node fromNode=graph.nodes.get(from);//获取入节点
            Node toNode=graph.nodes.get(to);//获取出节点
            Edge newEdge=new Edge(weight,fromNode,toNode);
            fromNode.nexts.add(toNode);
            fromNode.out++;
            toNode.in++;
            fromNode.edges.add(newEdge);
            graph.edges.add(newEdge);
        }
        return graph;
    }
}
