package FaceQuestion.图;

/**
 * 图的边类
 */
public class Edge {

    public int weight;//权重
    public Node from;//入节点
    public Node to;//出节点

    public Edge(int weight,Node from,Node to){
        this.weight=weight;
        this.from=from;
        this.to=to;
    }
}
