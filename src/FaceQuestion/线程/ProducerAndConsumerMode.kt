package FaceQuestion.线程

import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.locks.ReentrantLock


/**
 * 基于wait notify实现的生产者消费者模式
 */
object ProducerAndConsumerMode1 {


    private val LOCK = Object()

    private const val FULL = 10
    private var count = 0
    @JvmStatic
    fun main(args: Array<String>) {
        Thread(Producer, "生产者线程1").start()
        Thread(Producer, "生产者线程2").start()
        Thread(Producer, "生产者线程3").start()
        Thread(Consumer, "消费者线程1").start()
        Thread(Consumer, "消费者线程2").start()
        Thread(Consumer, "消费者线程3").start()
        Thread(Producer, "生产者线程4").start()
        Thread(Producer, "生产者线程5").start()
        Thread(Consumer, "消费者线程4").start()

    }

    object Producer: Runnable {
        override fun run() {
            for (i in 0 .. 10) {
                Thread.sleep(1000)
                synchronized(LOCK) {
                    while (count == FULL) {
                        LOCK.wait()
                    }

                    count ++
                    println(Thread.currentThread().name + "生产，目前共有$count")
                    LOCK.notifyAll()
                }
            }
        }
    }

    object Consumer: Runnable {
        override fun run() {
            for (i in 0..10) {
                Thread.sleep(1000)
                synchronized(LOCK) {
                    while (count == 0) {
                        LOCK.wait()
                    }

                    count --
                    println(Thread.currentThread().name + "消费，目前共有$count")
                    LOCK.notifyAll()
                }
            }
        }
    }
}


/**
 * 基于ReentrantLock可重入锁实现的生产者消费者模式
 */
object ProducerAndConsumerMode2 {

    private val lock = ReentrantLock()
    private var count = 0
    private const val FULL = 10

    //两个变量，一个缓冲区非满，一个缓冲区非空
    private val notFull = lock.newCondition()
    private val notEmpty = lock.newCondition()

    @JvmStatic
    fun main(args: Array<String>) {
        Thread(Producer, "生产者线程1").start()
        Thread(Producer, "生产者线程2").start()
        Thread(Producer, "生产者线程3").start()
        Thread(Consumer, "消费者线程1").start()
        Thread(Consumer, "消费者线程2").start()
        Thread(Consumer, "消费者线程3").start()
        Thread(Producer, "生产者线程4").start()
        Thread(Producer, "生产者线程5").start()
        Thread(Consumer, "消费者线程4").start()
    }
    object Producer: Runnable {
        override fun run() {
            for (i in 0 .. 10) {
                Thread.sleep(1000)
                //先获取锁
                lock.lock()
                try {
                    while (count == FULL) {
                        //如果队列已满，那么就非满条件阻塞，生产者不再生产
                        notFull.await()
                    }
                    //队列未满，生产者开始生产
                    count++
                    println(Thread.currentThread().name + "生产，目前共有$count")
                    //唤醒非空条件，通知消费者可以消费了
                    notEmpty.signal()
                }catch (e: Exception) {
                    e.printStackTrace()
                }finally {
                    //释放锁
                    lock.unlock()
                }
            }
        }
    }

    object Consumer: Runnable {
        override fun run() {
            for (i in 0..10) {
                Thread.sleep(1000)
                //先获取锁
                lock.lock()
                try {
                    while (count == 0) {
                        //当队列未空时，非空条件阻塞，消费者不可消费
                        notEmpty.await()
                    }
                    //队列不为空，消费者消费
                    count--
                    println(Thread.currentThread().name + "消费，目前共有$count")
                    //唤醒非满条件，通知生产者可以继续生产
                    notFull.signal()
                }catch (e: Exception) {
                    e.printStackTrace()
                }finally {
                    //释放锁
                    lock.unlock()
                }
            }
        }
    }
}

/**
 * 基于BlockQueue阻塞队列实现的生产者消费者模式
 */
object ProducerAndConsumerMode3 {

    private var count = 0
    private val arrayBlockingQueue = ArrayBlockingQueue<Int>(10)

    @JvmStatic
    fun main(args: Array<String>) {
        Thread(Producer, "生产者线程1").start()
        Thread(Producer, "生产者线程2").start()
        Thread(Producer, "生产者线程3").start()
        Thread(Consumer, "消费者线程1").start()
        Thread(Consumer, "消费者线程2").start()
        Thread(Consumer, "消费者线程3").start()
        Thread(Producer, "生产者线程4").start()
        Thread(Producer, "生产者线程5").start()
        Thread(Consumer, "消费者线程4").start()
    }
    object Producer: Runnable {
        override fun run() {
            for (i in 0 .. 10) {
                Thread.sleep(1000)
                arrayBlockingQueue.put(1)
                count++
                println(Thread.currentThread().name + "生产，目前共有$count")
            }
        }
    }

    object Consumer: Runnable {
        override fun run() {
            for (i in 0..10) {
                Thread.sleep(1000)
                arrayBlockingQueue.take()
                count--
                println(Thread.currentThread().name + "消费，目前共有$count")
            }
        }
    }
}

