package FaceQuestion.图;

import java.util.ArrayList;

/**
 * 图的节点类
 */
public class Node {

    public int value;//图节点的值
    public int in;//入度
    public int out;//出度
    public ArrayList<Node> nexts;//邻接节点的集合
    public ArrayList<Edge> edges;//当前节点连接边的集合

    public Node(int value){
        this.value=value;
        in=0;
        out=0;
        nexts=new ArrayList<>();
        edges=new ArrayList<>();
    }

}
