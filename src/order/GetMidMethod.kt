
import java.util.Comparator
import java.util.PriorityQueue

private var count = 0
private val minHeap = PriorityQueue<Int>()
private val maxHeap = PriorityQueue(15, Comparator<Int> { o1, o2 -> o2!! - o1!! })

//读入字符，放到合适位置
fun Insert(num: Int) {
    if (count % 2 == 0) {
        maxHeap.offer(num)
        val filteredMaxNum = maxHeap.poll()
        minHeap.offer(filteredMaxNum)
    } else {
        minHeap.offer(num)
        val filteredMinNum = minHeap.poll()
        maxHeap.offer(filteredMinNum)
    }
    count++
}

//求中位数
fun GetMedian(): Double? {
    return if (count % 2 == 0) {
        ((minHeap.peek().toDouble() + maxHeap.peek().toDouble()) / 2)
    } else {
        minHeap.peek().toDouble()
    }
}