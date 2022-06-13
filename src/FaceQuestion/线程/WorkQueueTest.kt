package com.example.pggtestapp.okhttp使用

import java.util.concurrent.*

object WorkQueueTest {

    private val arrayBlockingQueue = ArrayBlockingQueue<Runnable>(1)
    private val linkedBlockingQueue = LinkedBlockingQueue<Runnable>()
    private val synchronousQueue = SynchronousQueue<Runnable>()

    @JvmStatic
    fun main(args: Array<String>) {
        val pool = ThreadPoolExecutor(0, Int.MAX_VALUE, 60, TimeUnit.SECONDS, synchronousQueue)
        val task1 = Runnable {
            println("执行task1")
            while (true) {

            }
        }

        pool.execute(task1)
        Thread.sleep(100)
        val task2 = Runnable {
            println("执行task2")
        }

        pool.execute(task2)

        val task3 = Runnable {
            println("执行task3")
        }

        pool.execute(task3)

        while (true) {

        }
    }
}