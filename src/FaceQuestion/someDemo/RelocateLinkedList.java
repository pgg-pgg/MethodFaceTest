package FaceQuestion.someDemo;


/**
 * 给定一个链表list,
 * 如果:
 * list = 1 调整之后1。
 * list = 1->2 调整之后1->2
 * list = 1->2->3 调整之后1->2->3
 * list = 1->2->3->4 调整之后1->3->2->4
 * list = 1->2->3->4->5 调整之后1->3->2->4->5
 * list = 1->2->3->4->5->6 调整之后1->4->2->5->3->6
 * list = 1->2->3->4->5->6->7 调整之后1->4->2->5->3->6->7
 * 根据上面的规律,调整一个任意长度的链表。
 */
public class RelocateLinkedList {

    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void relocate(Node head){
        if (head==null||head.next==null){

        }
    }
}
