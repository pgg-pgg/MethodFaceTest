package FaceQuestion.二叉树;


import java.util.Stack;

/**
 * 二叉树先序、中序,后序遍历的非递归实现
 */
public class PreInPosTraversal {

    /**
     * 二叉树节点类
     */
    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int data){
            this.value=data;
        }
    }


    /**
     * 二叉树前序遍历递归实现
     * @param head
     */
    public static void preOrderRecur(Node head){
        if (head==null){
            return;
        }

        System.out.println(head.value+" ");
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }

    /**
     * 二叉树中序遍历递归实现
     * @param head
     */
    public static void inOrderRecur(Node head) {
        if (head == null) {
            return;
        }
        inOrderRecur(head.left);
        System.out.print(head.value + " ");
        inOrderRecur(head.right);
    }

    /**
     * 二叉树后序遍历递归实现
     * @param head
     */
    public static void posOrderRecur(Node head) {
        if (head == null) {
            return;
        }
        posOrderRecur(head.left);
        posOrderRecur(head.right);
        System.out.print(head.value + " ");
    }

    /**
     * 二叉树前序遍历非递归实现
     * 使用栈实现，先弹出打印头节点
     * 然后先将右子树压栈，然后压入左子树
     * 然后遍历打印
     * @param head
     */
    public static void preOrderUnRecur(Node head){
        System.out.println("pre-order");
        if (head!=null){
            Stack<Node> stack=new Stack<>();
            stack.add(head);
            while (!stack.isEmpty()){
                head=stack.pop();
                System.out.print(head.value+" ");
                if (head.right!=null){
                    stack.push(head.right);
                }
                if (head.left!=null){
                    stack.push(head.left);
                }
            }
        }
    }

    /**
     * 二叉树的中序遍历非递归实现
     * @param head
     */
    public static void inOrderUnRecur(Node head) {
        System.out.print("in-order: ");
        if (head != null) {
            Stack<Node> stack = new Stack<Node>();
            while (!stack.isEmpty() || head != null) {
                if (head != null) {
                    stack.push(head);
                    head = head.left;
                } else {
                    head = stack.pop();
                    System.out.print(head.value + " ");
                    head = head.right;
                }
            }
        }
        System.out.println();
    }

    /**
     * 后序遍历非递归实现
     * @param head
     */
    public static void posOrderUnRecur1(Node head) {
        System.out.print("pos-order: ");
        if (head != null) {
            Stack<Node> s1 = new Stack<Node>();
            Stack<Node> s2 = new Stack<Node>();
            s1.push(head);
            while (!s1.isEmpty()) {
                head = s1.pop();
                s2.push(head);
                if (head.left != null) {
                    s1.push(head.left);
                }
                if (head.right != null) {
                    s1.push(head.right);
                }
            }
            while (!s2.isEmpty()) {
                System.out.print(s2.pop().value + " ");
            }
        }
        System.out.println();
    }
}
