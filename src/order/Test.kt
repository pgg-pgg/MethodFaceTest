package order

import FaceQuestion.链表.IsPalindromeList
import GetMedian
import Insert

object Test {


    @JvmStatic
    fun main(args: Array<String>) {

        print(-1/2)

//        val a = intArrayOf(1,2,3,4,5,6)
//        for (i in a.indices) {
//            Insert(a[i])
//        }
//        println(GetMedian())

    }

}


class Node(var value: Int) {
    var next: Node? = null
}

fun main(args: Array<String>) {
    var head: IsPalindromeList.Node? = IsPalindromeList.Node(1)
    head?.next = IsPalindromeList.Node(2)
    head?.next?.next = IsPalindromeList.Node(3)
    head?.next?.next?.next = IsPalindromeList.Node(2)
    head?.next?.next?.next?.next = IsPalindromeList.Node(1)
    print(IsPalindromeList.isPalindrome3(head!!))

}
