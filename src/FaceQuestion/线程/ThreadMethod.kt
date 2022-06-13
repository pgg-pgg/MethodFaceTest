package FaceQuestion.线程

import java.util.concurrent.Callable
import java.util.concurrent.FutureTask


object ThreadMethod {


    private val call = Callable<Int> {
        println("sdasd")
        Thread.sleep(1000)
        1
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val thread = Thread(Runnable {
            while (!Thread.interrupted()) {
                println("running")
            }
        })
        thread.start()
        Thread.sleep(1000)
        Thread.yield()

        thread.interrupt()
    }
}