package FaceQuestion.数组_栈_队列

class Node {
    var value: Int = 0
    var next: Node? = null
}


class DoubleNode(var value: Int) {
    var last: DoubleNode? = null
    var next: DoubleNode? = null
}

object ReverseList {
    fun reverse(head : Node) {
        var temp: Node?
        var pre: Node? = null
        var cur: Node? = head
        while (cur != null) {
            temp = cur.next
            cur.next = pre
            pre = cur
            cur = temp
        }
    }

    fun reverseDouble(head: DoubleNode) {
        var temp: DoubleNode?
        var pre: DoubleNode? = null
        var cur: DoubleNode? = head
        while (cur != null) {
            temp = cur.next
            cur.next = pre
            cur.last = temp
            pre = cur
            cur = temp
        }
    }
}