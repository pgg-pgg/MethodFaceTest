package FaceQuestion.链表;

/**
 * 将单向链表按某值划分成左边小、中间相等、右边大的形式
 * 1.参照快速排序的方法去进行区域的划分
 * 2.提前设置三个区域smaller，equal，bigger，遍历链表，将对应元素添加到对应区域中
 */
public class SmallerEqualBigger {


    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 第一种方法：参照快速排序的方法去进行区域的划分，将链表节点存储到数组中，按照节点的Value进行划分
     * @param head
     * @param pivot
     * @return
     */
    public static Node listPartition1(Node head, int pivot) {
        if (head == null) {
            return head;
        }
        //获取链表总长度
        Node cur = head;
        int i = 0;
        while (cur != null) {
            i++;
            cur = cur.next;
        }
        //创建一个存储链表节点的数组
        Node[] nodeArr = new Node[i];
        i = 0;
        cur = head;
        //将链表元素存储到数组中
        for (i = 0; i != nodeArr.length; i++) {
            nodeArr[i] = cur;
            cur = cur.next;
        }
        //划分区域
        arrPartition(nodeArr, pivot);
        for (i = 1; i != nodeArr.length; i++) {
            nodeArr[i - 1].next = nodeArr[i];
        }
        nodeArr[i - 1].next = null;
        return nodeArr[0];
    }

    /**
     * 划分区域的方法，参照快排的思想
     * @param nodeArr
     * @param pivot
     */
    public static void arrPartition(Node[] nodeArr, int pivot) {
        int small = -1;
        int big = nodeArr.length;
        int index = 0;
        while (index != big) {
            if (nodeArr[index].value < pivot) {
                swap(nodeArr, ++small, index++);
            } else if (nodeArr[index].value == pivot) {
                index++;
            } else {
                swap(nodeArr, --big, index);
            }
        }
    }

    public static void swap(Node[] nodeArr, int a, int b) {
        Node tmp = nodeArr[a];
        nodeArr[a] = nodeArr[b];
        nodeArr[b] = tmp;
    }

    public Node listPartition2(Node head,int pivot){
        Node sH=null;//小于区域的头结点
        Node sT=null;//小于区域的尾节点
        Node eH=null;//等与区域的头结点
        Node eT=null;//等于区域的尾节点
        Node bH=null;//大于区域的头节点
        Node bT=null;//大于区域的尾节点

        Node next=null;//用于保存下一个节点
        while (head!=null){
            next=head.next;
            head.next=null;
            if (head.value<pivot){//当前元素小于划分值
                if (sH==null){
                    sH=head;
                    sT=head;
                }else {
                    sT.next=head;
                    sT=head;
                }
            }else if (head.value==pivot){//当前元素等于划分值
                if (eH==null){
                    eH=head;
                    eT=head;
                }else {
                    eT.next=head;
                    eT=head;
                }
            }else {//当前元素大于划分值
                if (bH==null){
                    bH=head;
                    bT=head;
                }else {
                    bT.next=head;
                    bT=head;
                }
            }
            head=next;
        }
        //连接三个区域
        if (sT!=null){
            sT.next=eH;
            eT=eT==null?sT:eT;
        }
        if (eT!=null){
            eT.next=bH;
        }
        return sH!=null?sH:eH!=null?eH:bH;
    }
}
