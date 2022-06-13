package FaceQuestion.线程

import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.locks.ReentrantLock

/**
 * 使用wait和notify实现打印奇偶
 */
object ThreadAlternateExecution1 {
    private val LOCK = Object()


    private val threadJi = Thread {
        for (i in 1..100 step 2) {
            synchronized(LOCK) {
                println(i)
                LOCK.notifyAll()
                LOCK.wait()
            }
        }
    }

    private val threadOu = Thread {
        for (i in 0..100 step 2) {
            synchronized(LOCK) {
                println(i)
                LOCK.notifyAll()
                LOCK.wait()
            }
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        threadJi.start()
        threadOu.start()
    }
}


/**
 * 使用CAS + volatile
 */
object ThreadAlternateExecution2 {

    //原子类，保证自增操作的原子性
    private val num = AtomicInteger(0)

    //volatile修饰，保证flag的可见性
    //当flag为false时，打印偶数，true时打印奇数
    @Volatile
    private var flag = false


    private val threadJi = Thread {
        //这里当没有打印到指定值时，会一直循环
        while (num.get() < 100) {
            if (!flag && (num.get() == 0 || num.incrementAndGet() % 2 == 0)) {
                println(num.get())
                flag = true
            }
        }
    }

    private val threadOu = Thread {
        while (num.get() < 100) {
            if (flag && num.incrementAndGet() % 2 == 1) {
                println(num.get())
                flag = false
            }
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        threadJi.start()
        threadOu.start()
    }
}


/**
 * 使用ReentrantLock
 */
object ThreadAlternateExecution3 {

    private val lock = ReentrantLock()
    //false打印偶数，true打印奇数
    private var flag = false
    private val print = lock.newCondition()


    private val threadJi = Thread {
        for (i in 1..100 step 2) {
            lock.lock()
            try {
                if (!flag) {
                    print.await()
                }
                println(i)
                flag = false
                print.signal()
            } catch (e: Exception) {

            } finally {
                lock.unlock()
            }
        }
    }

    private val threadOu = Thread {
        for (i in 0..100 step 2) {
            lock.lock()
            try {
                if (flag) {
                    print.await()
                }
                println(i)
                flag = true
                print.signal()
            } catch (e: Exception) {

            } finally {
                lock.unlock()
            }
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        threadJi.start()
        threadOu.start()
    }
}


