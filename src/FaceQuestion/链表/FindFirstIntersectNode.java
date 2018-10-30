package FaceQuestion.链表;

/**
 * 两个链表相交的一系列问题
 */
public class FindFirstIntersectNode {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public static void main(String[] args) {
        Node head1 = new Node(1);
        Node node1 = new Node(2);
        Node node2 = new Node(3);
        Node node3 = new Node(4);
        Node node4 = new Node(5);
        Node node5 = new Node(6);
        head1.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        Node head2 = new Node(1);
        head2.next = node4;
        System.out.println(noLoop(head1, head2).value);
    }

    /**
     * 判断链表是否有环，如果链表有环，那么返回环的第一个节点，如果没有换，直接返回空
     * @param head
     * @return
     */
    public static Node getLoopNode(Node head){
        if (head==null||head.next==null||head.next.next==null){
            return null;
        }
        Node n1=head.next;//慢指针
        Node n2=head.next.next;//快指针
        while (n1!=n2){//当快慢指针相遇是结束循环
            if (n2.next==null||n2.next.next==null){
                return null;
            }
            n2=n2.next.next;//快指针一次走两步
            n1=n1.next;//慢指针一次走一步
        }
        //快慢指针相遇，将快指针指向链表头
        n2=head;
        while (n1!=n2){
            //快慢指针一次都直走一步，再次相遇时就是环的第一个节点
            n1=n1.next;
            n2=n2.next;
        }
        return n1;
    }

    /**
     * 两个链表都无环时，判断是否相交
     * @param head1
     * @param head2
     * @return
     */
    public static Node noLoop(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node cur1 = head1;
        Node cur2 = head2;
        int n = 0;//两条链表的长度差
        while (cur1.next != null) {
            n++;
            cur1 = cur1.next;
        }
        while (cur2.next != null) {
            n--;
            cur2 = cur2.next;
        }

        if (cur1 != cur2) {
            return null;
        }
        cur1 = n > 0 ? head1 : head2;
        cur2 = cur1 == head1 ? head2 : head1;
        n = Math.abs(n);
        while (n != 0) {
            n--;
            cur1 = cur1.next;
        }
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    /**
     * 两个链表都有环时，判断相交问题
     * @param head1
     * @param loop1
     * @param head2
     * @param loop2
     * @return
     */
    public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        Node cur1 = null;
        Node cur2 = null;
        if (loop1 == loop2) {//如果不在环上相交
            cur1 = head1;
            cur2 = head2;
            int n = 0;
            while (cur1 != loop1) {
                n++;
                cur1 = cur1.next;
            }
            while (cur2 != loop2) {
                n--;
                cur2 = cur2.next;
            }
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n != 0) {
                n--;
                cur1 = cur1.next;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else {//在环上相交
            cur1 = loop1.next;
            while (cur1 != loop1) {
                if (cur1 == loop2) {
                    return loop1;
                }
                cur1 = cur1.next;
            }
            return null;
        }
    }
}
