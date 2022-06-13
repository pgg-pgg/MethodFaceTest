package FaceQuestion.线程

import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicMarkableReference
import java.util.concurrent.atomic.AtomicStampedReference

object AtomicTest {

    private var a = 0
    @JvmStatic
    fun main(args: Array<String>) {
        Thread {
            for (i in 0 until 10000) {
                synchronized(this){
                    a++
                }
            }

        }.start()
        Thread {
            for (i in 0 until 10000) {
                synchronized(this){
                    a++
                }
            }
        }.start()

        Thread.sleep(1000)
        println(a)
    }
}



object AtomicTest1 {

    @Volatile
    private var a = 0
    @JvmStatic
    fun main(args: Array<String>) {
        Thread {
            for (i in 0 until 10000) {
                a++
            }

        }.start()
        Thread {
            for (i in 0 until 10000) {
                a++
            }
        }.start()

        Thread.sleep(1000)
        println(a)
    }
}

object AtomicTest2 {

    private val atomicInt = AtomicInteger(0)
    @JvmStatic
    fun main(args: Array<String>) {
        Thread {
            for (i in 0 until 10000) {
                atomicInt.addAndGet(1)
            }

        }.start()
        Thread {
            for (i in 0 until 10000) {
                atomicInt.addAndGet(1)
            }
        }.start()

        Thread.sleep(1000)
        println(atomicInt.get())
    }
}