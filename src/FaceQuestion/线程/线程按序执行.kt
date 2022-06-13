package FaceQuestion.线程

import java.util.concurrent.CountDownLatch
import java.util.concurrent.CyclicBarrier
import java.util.concurrent.locks.ReentrantLock


/**
 * 使用Thread.join方法
 */
object ThreadOrderExecuted1 {
    private val thread1 = Thread {
        println("第一个线程执行")
    }

    private val thread2 = Thread {
        //join方法，表示将thread1合并到thread2中，让thread1执行完之后，才能执行后面的代码
        thread1.join()
        println("第二个线程执行")
    }

    private val thread3 = Thread {
        thread2.join()
        println("第三个线程执行")
    }

    @JvmStatic
    fun main(args: Array<String>) {
        thread2.start()
        thread1.start()
        thread3.start()
    }
}

/**
 * 使用wait，notify
 */
object ThreadOrderExecuted2 {
    private val LOCK = Object()
    private var thread1IsRunning = false
    private val thread1 = Thread {
        synchronized(LOCK) {
            Thread.sleep(1000)
            println("第一个线程执行")
            thread1IsRunning = true
            LOCK.notify()
        }
    }

    private val thread2 = Thread {
        synchronized(LOCK) {
            if (!thread1IsRunning) {
                LOCK.wait()
            }
            println("第二个线程执行")
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        thread2.start()
        thread1.start()
    }
}

/**
 * 使用ReentrantLock
 */
object ThreadOrderExecuted3 {
    private val lock = ReentrantLock()
    private val thread1Running = lock.newCondition()
    private var thread1IsRunning = false


    private val thread1 = Thread {
        lock.lock()
        try {
            Thread.sleep(1000)
            println("第一个线程执行")
            thread1IsRunning = true
            thread1Running.signal()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            lock.unlock()
        }
    }

    private val thread2 = Thread {
        lock.lock()
        try {
            if (!thread1IsRunning) {
                thread1Running.await()
            }
            println("第二个线程执行")
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            lock.unlock()
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        thread2.start()
        thread1.start()
    }
}

/**
 * 使用CountDownLatch
 * 比如有一个任务C，它要等待其他任务A,B执行完毕之后才能执行，此时就可以利用CountDownLatch来实现这种功能了
 */
object ThreadOrderExecuted4 {

    private val countDownLatch = CountDownLatch(1)

    private val thread1 = Thread {
        Thread.sleep(1000)
        println("第一个线程执行")
        countDownLatch.countDown()
    }

    private val thread2 = Thread {
        countDownLatch.await()
        println("第二个线程执行")
    }

    @JvmStatic
    fun main(args: Array<String>) {
        thread2.start()
        thread1.start()
    }
}

/**
 * 使用CyclicBarrier
 *
 */
object ThreadOrderExecuted5 {

    /**
     * 此处设置为2
     */
    private val cyclicBarrier = CyclicBarrier(2)

    private val thread1 = Thread {
        Thread.sleep(1000)
        println("第一个线程执行")
        //线程1执行完，调用await方法，将state++，如果线程1先执行，那么此时state = 1 < 2, thread2调用await时才会执行
        //如果线程2先执行，state = 1 < 2,thread2将会阻塞，当thread1 调用await后才会释放thread2
        cyclicBarrier.await()
    }

    private val thread2 = Thread {
        cyclicBarrier.await()
        println("第二个线程执行")
    }

    @JvmStatic
    fun main(args: Array<String>) {
        thread2.start()
        thread1.start()
    }
}

