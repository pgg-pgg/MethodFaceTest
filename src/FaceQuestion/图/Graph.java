package FaceQuestion.图;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 图的实体类
 */
public class Graph {

    public HashMap<Integer,Node> nodes;//图的所有节点集合
    public HashSet<Edge> edges;//图的所有边的集合

    public Graph(){
        nodes=new HashMap<>();
        edges=new HashSet<>();
    }

}
