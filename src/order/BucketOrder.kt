package order


/**
  桶排序中的计数排序
  比如有这样一个数组[1,5,4,3,7,8,2,5,4,3,2,2,9,0,5]
  这里有15个元素，数组最小值为0，最大值为9
  这时创建一个help数组，长度为10，代表着0-9这几个数字
  help存储的是待排序数组中元素出现的次数，也就是词频
  然后开始遍历数组，第一个元素是1，那么就把help[1]++；
  第二个元素5，那么就help[5]++
  。。。
  等到遍历完成之后，help存储的就是
  [1,1,3,2,2,3,0,1,1,1]
  意思就是，待排序数组中，0出现1次，1出现1次，2出现3次...
  然后再把help数组拷贝到数组中，就实现排序
 */
class BucketOrder {


    fun bucketOrder(arr: IntArray?) {
        if (arr == null || arr.size < 2) {
            return
        }

        //找出数组中最大值，确定数组元素值的范围
        var max = Integer.MIN_VALUE
        for (i in arr.indices) {
            max = Math.max(max, arr[i])
        }

        //help数组，长度为max+1
        val bucket = IntArray(max + 1)
        for (i in arr.indices) {
            bucket[arr[i]]++//将数组词频存到对应位置
        }

        //进行拷贝
        var i = 0
        for (j in bucket.indices) {
            while (bucket[j]-- > 0) {
                arr[i++] = j
            }
        }
    }
}
