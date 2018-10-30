package FaceQuestion.二叉树;

/**
 * 找到二叉树的后继节点
 * 1.当一个节点存在右子树时，他的最左边的节点就是后继节点
 * 2.当一个节点不存在右子树时，将其不断向上移动，当发现他的父节点的左孩子是node时，parent就是其后继节点
 */
public class DescendantNode {

    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 找到后继节点
     * @param node 当前节点
     * @return
     */
    public static Node getNextNode(Node node) {
        if (node == null) {
            return node;
        }
        //node有右子树，右子树最左的节点就是后继节点
        if (node.right != null) {
            return getLeftMost(node.right);
        } else {//node没有右子树，node不断向上跳，当发现当前node是parent的左孩子，那么parent就是后继节点
            Node parent = node.parent;
            while (parent != null && parent.left != node) {
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }

    /**
     * 找到一个节点的最左边的节点
     * @param node
     * @return
     */
    public static Node getLeftMost(Node node) {
        if (node == null) {
            return node;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public static void main(String[] args) {
        Node head = new Node(6);
        head.parent = null;
        head.left = new Node(3);
        head.left.parent = head;
        head.left.left = new Node(1);
        head.left.left.parent = head.left;
        head.left.left.right = new Node(2);
        head.left.left.right.parent = head.left.left;
        head.left.right = new Node(4);
        head.left.right.parent = head.left;
        head.left.right.right = new Node(5);
        head.left.right.right.parent = head.left.right;
        head.right = new Node(9);
        head.right.parent = head;
        head.right.left = new Node(8);
        head.right.left.parent = head.right;
        head.right.left.left = new Node(7);
        head.right.left.left.parent = head.right.left;
        head.right.right = new Node(10);
        head.right.right.parent = head.right;

        Node test = head.left.left;
        System.out.println(test.value + " next: " + getNextNode(test).value);
        test = head.left.left.right;
        System.out.println(test.value + " next: " + getNextNode(test).value);
        test = head.left;
        System.out.println(test.value + " next: " + getNextNode(test).value);
        test = head.left.right;
        System.out.println(test.value + " next: " + getNextNode(test).value);
        test = head.left.right.right;
        System.out.println(test.value + " next: " + getNextNode(test).value);
        test = head;
        System.out.println(test.value + " next: " + getNextNode(test).value);
        test = head.right.left.left;
        System.out.println(test.value + " next: " + getNextNode(test).value);
        test = head.right.left;
        System.out.println(test.value + " next: " + getNextNode(test).value);
        test = head.right;
        System.out.println(test.value + " next: " + getNextNode(test).value);
        test = head.right.right; // 10's next is null
        System.out.println(test.value + " next: " + getNextNode(test));
    }
}
