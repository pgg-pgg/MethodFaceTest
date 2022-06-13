package FaceQuestion.线程

import org.omg.SendingContext.RunTime
import java.text.DateFormat
import java.util.*
import java.util.concurrent.*

object ThreadPoolDemo {

    object CacheThreadPoolTest {
        private val cacheThreadPool = Executors.newCachedThreadPool()

        fun test() {
            for (i in 0..10) {
                Thread.sleep(1000)
                cacheThreadPool.execute {
                    println(Thread.currentThread().name + "执行第" + i + "次")
                }
            }
        }
    }

    object FixedThreadPoolTest {
        private val fixedThreadPool = Executors.newFixedThreadPool(10)

        fun test() {
            for (i in 0..10) {
                Thread.sleep(1000)
                fixedThreadPool.execute {
                    println(Thread.currentThread().name + "执行第" + i + "次")
                }
            }
        }
    }


    object ScheduledThreadPoolTest {
        private val scheduledThreadPool = Executors.newScheduledThreadPool(5)

        /**
         * scheduleAtFixedRate方法间隔时间受任务执行时间影响
         * 如果任务执行时间 > 间隔时间的话，那么任务执行结束之后，会立马执行，至此间隔时间就会被打乱。
         */
        fun test1() {
            scheduledThreadPool.scheduleAtFixedRate({
                val start = System.currentTimeMillis()
                println("开始执行时间" + DateFormat.getTimeInstance().format(start))
                Thread.sleep(8000)
                val end = System.currentTimeMillis()
                println("执行花费时间" + (end - start) /1000 + "s")
                println("执行完成时间" + DateFormat.getTimeInstance().format(Date()))
                println("=========================")
            },1, 5, TimeUnit.SECONDS)
        }


        /**
         * scheduleWithFixedDelay方法间隔时间不受任务执行时间影响
         * 如果任务执行时间 > 间隔时间的话，仍然会按照间隔时间执行任务
         */
        fun test2() {
            scheduledThreadPool.scheduleWithFixedDelay({
                val start = System.currentTimeMillis()
                println("开始执行时间" + DateFormat.getTimeInstance().format(start))
                Thread.sleep(8000)
                val end = System.currentTimeMillis()
                println("执行花费时间" + (end - start) /1000 + "s")
                println("执行完成时间" + DateFormat.getTimeInstance().format(Date()))
                println("=========================")
            },1, 5, TimeUnit.SECONDS)
        }
    }

    object SingleThreadPoolTest {
        private val singleThreadPool = Executors.newSingleThreadExecutor()

        fun test() {
            for (i in 0..10) {
                Thread.sleep(1000)
                singleThreadPool.execute {
                    println(Thread.currentThread().name + "执行第" + i + "次")
                }
            }
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        SingleThreadPoolTest.test()
    }
}