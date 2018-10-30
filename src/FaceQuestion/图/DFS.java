package FaceQuestion.图;


import java.util.HashSet;
import java.util.Stack;

/**
 * 图的深度优先搜索算法
 * 1,利用栈实现
 * 2,从源节点开始把节点按照深度放入栈,然后弹出
 * 3,每弹出一个点,把该节点下一个没有进过栈的邻接点放入栈
 * 4,直到栈变空
 */
public class DFS {

    public static void dfs(Node node){
        if (node==null){
            return;
        }
        Stack<Node> stack=new Stack<>();//存储节点
        HashSet<Node> set=new HashSet<>();//判断是否进过栈的集合
        //首先当前源节点进栈和集合，并打印
        stack.add(node);
        set.add(node);
        System.out.println(node.value);
        while (!stack.isEmpty()){//不断从栈弹出元素
            Node cur=stack.pop();
            for (Node next:cur.nexts){
                //遍历当前节点的邻居节点
                if (!set.contains(next)){
                    //只要存在一个邻居节点没有进过栈，那么就直接结束循环
                    //注意，此时，邻居节点并不会遍历完，这也是深度优先遍历的特点
                    stack.push(cur);
                    stack.push(next);
                    set.add(next);
                    System.out.println(next.value);
                    break;
                }
            }
        }
    }

}
