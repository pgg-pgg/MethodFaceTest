package FaceQuestion.数组_栈_队列

/**
 * 定长数组实现栈
 * 设置标志位，start和end
 * 从end中插入，start处弹出
 * 同时使用size判断数组越界问题，size记录的是数组中元素个数
 * 每插入一个元素，size++，元素放在end处，并使用nextIndex判断end位置，如果到数组边界，那么就置为0
 * 每弹出一个元素，size--，弹出start处元素，并使用nextIndex判断start位置，如果到数组边界，那么置为0；
 */
class ArrayQueue
/**
 * 初始化定长数组
 * @param initSize
 */
(initSize: Int) {

    private val arr: IntArray
    private var size: Int = 0
    private var start: Int = 0
    private var end: Int = 0

    init {
        if (initSize < 0) {
            throw IllegalArgumentException("数组初始化失败")
        }

        arr = IntArray(initSize)
        size = 0
        start = 0
        end = 0
    }


    fun peek(): Int? {
        return if (size == 0) {
            null
        } else arr[start]
    }

    fun push(obj: Int) {
        if (size == arr.size) {
            throw ArrayIndexOutOfBoundsException("越界")
        }

        size++
        arr[end] = obj
        end = nextIndex(arr.size, end)
    }

    fun poll(): Int? {
        if (size == 0) {
            throw ArrayIndexOutOfBoundsException("越界")
        }
        size--
        val tmp = start!!
        start = nextIndex(arr.size, start)
        return arr[tmp]
    }


    /**
     * 获取start和end的移动的下一个位置，如果到了边界，那么就置为0
     * @param length
     * @param index
     * @return
     */
    private fun nextIndex(length: Int, index: Int?): Int {
        return if (index == length - 1) 0 else index!! + 1
    }

}
