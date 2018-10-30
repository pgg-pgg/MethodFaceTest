package FaceQuestion.链表;

/**
 * 打印两个有序链表的公共部分
 * 链表1的头结点head1，链表2的头结点为head2
 * head指针每次移动一步前，都要判断两个链表元素的大小，哪个元素小，哪个指针就走一步，另一个指针不动，如果两个元素相等，打印，并且两个指针都走一步
 */
public class PrintCommonPart {

    /**
     * 链表节点类
     */
    public static class Node{
        public int value;
        public Node next;
        public Node(int data){
            this.value=data;
        }
    }


    public static void printCommmonPart(Node head1,Node head2){
        System.out.print("公共部分为：");
        while (head1!=null&&head2!=null){//判断两个链表都不能为空
            if (head1.value<head2.value){//如果head1的值小于head2的值
                head1=head1.next;//head1走一步
            }else if (head1.value>head2.value){//同理
                head2=head2.next;
            }else {//两者相等
                System.out.print(head1.value+" ");
                //head1和2都走一步
                head1=head1.next;
                head2=head2.next;
            }
        }
        System.out.println();
    }



}
