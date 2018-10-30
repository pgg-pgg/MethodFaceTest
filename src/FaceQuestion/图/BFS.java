package FaceQuestion.图;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 图的宽度优先搜索算法
 * 其邻居节点尚未被检验过的节点会被放置在一个被称为 open 的容器中（例如队列或是链表），
 * 而被检验过的节点则被放置在被称为 closed 的容器中。（open-closed表）
 * 1,利用队列实现
 * 2,从源节点开始依次按照宽度进队列,然后弹出
 * 3,每弹出一个点,把该节点所有没有进过队列的邻接点放入队列
 * 4,直到队列变空
 */
public class BFS {


    /**
     * 宽度优先搜索
     * @param node 当前节点
     */
    public void bfs(Node node){
        if (node==null){
            return;
        }
        Queue<Node> queue=new LinkedList<>();//未被检验过的节点集合
        HashSet<Node> map=new HashSet<>();//已经被检验过的节点集合
        queue.add(node);
        map.add(node);
        while (!queue.isEmpty()){
            Node cur=queue.poll();
            System.out.println(cur.value);
            for (Node next:cur.nexts){
                //循环遍历当前节点的邻居节点
                if (!map.contains(next)){
                    //如果没有被遍历过
                    map.add(next);
                    queue.add(next);
                }
            }
        }
    }
}
