import FaceQuestion.一些算法.KMP
import FaceQuestion.一些算法.Manacher
import leetCode.FaceCode

object a {

    internal var b = IntArray(4)

    @JvmStatic
    fun main(args: Array<String>) {
        val head1 = FaceCode.ListNode(1)
        head1.next = FaceCode.ListNode(2)
        head1.next!!.next = FaceCode.ListNode(4)
        val head2 = FaceCode.ListNode(1)
        head2.next = FaceCode.ListNode(3)
        head2.next!!.next = FaceCode.ListNode(4)
        FaceCode.mergeTwoLists(head1, head2)
    }


}
