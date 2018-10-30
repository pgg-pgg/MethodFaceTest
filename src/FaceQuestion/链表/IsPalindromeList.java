package FaceQuestion.链表;

import kotlin.jvm.JvmStatic;

import java.util.Stack;

/**
 * 判断链表是否回文
 * 1.使用一个栈，将链表元素压入栈，然后不断弹出元素与链表元素比较，需要额外O(N)的空间
 * 2.使用快慢指针，找到链表中点，将链表中点后的部分压入栈中，需要额外O(N/2)的空间
 * 3.使用快慢指针找到链表中点，然后将右边部分反转，再和左边部分比较
 */
public class IsPalindromeList {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 第一种方法，使用一个栈
     * @param head
     * @return
     */
    public boolean isPalindrome1(Node head){
        Stack<Node> stack =new Stack<>();
        Node cur=head;
        while (cur!=null){
            stack.push(cur);
            cur=cur.next;
        }
        while (head!=null){
            if (head.value!=stack.pop().value){
                return false;
            }
            head=head.next;
        }
        return true;
    }

    /**
     * 第二种方法，使用快慢指针，找到链表中点，将链表中点后的部分压入栈中
     * @param head
     * @return
     */
    public boolean isPalindrome2(Node head){
        if (head==null||head.next==null){
            return true;
        }
        Node right=head.next;
        Node cur=head;
        while (cur.next!=null&&cur.next.next!=null){
            right=right.next;
            cur=cur.next.next;
        }
        Stack<Node> stack=new Stack<>();
        while (right!=null){
            stack.push(right);
            right=right.next;
        }
        while (!stack.isEmpty()){
            if (head.value!=stack.pop().value){
                return false;
            }
            head=head.next;
        }
        return true;
    }

    /**
     * 第三种方法：使用快慢指针找到链表中点，然后将右边部分反转，再和左边部分比较
     * @param head
     * @return
     */
    @JvmStatic
    public static boolean isPalindrome3(Node head){
        if (head ==null||head.next==null){
            return true;
        }
        Node low=head;
        Node fast=head;
        while (fast.next!=null&&fast.next.next!=null){
            low=low.next;
            fast=fast.next.next;
        }
        fast=low.next;
        low.next=null;
        Node temp=null;
        while (fast!=null){//中点右边反转
            temp=fast.next;//保存下一个节点
            fast.next=low;//反转节点
            low=fast;//n1移动
            fast=temp;//n2移动
        }
        temp=low;
        fast=head;
        boolean res=true;
        while (low!=null&&fast!=null){
            if (low.value!=fast.value){
                res=false;
                break;
            }
            low=low.next;
            fast=fast.next;
        }
        //将反转的链表恢复
        low=temp.next;
        temp.next=null;
        while (low!=null){
            fast=low.next;
            low.next=temp;
            temp=low;
            low=fast;
        }
        return res;
    }

}
