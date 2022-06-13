package FaceQuestion.线程

import java.util.*

object ThreadLocalTest {
    private val runnable = object :Runnable {
        private val userThreadLocal = ThreadLocal<User>()

        override fun run() {
            println(Thread.currentThread().name + "is Running...")
            val age = Random().nextInt(100)
            val user = getUser()
            user.age = age
            println(Thread.currentThread().name + "first get age: = " + user.age)
            Thread.sleep(1000)
            println(Thread.currentThread().name + "second get age: = " + user.age)

        }

        fun getUser(): User {
            var user = userThreadLocal.get()
            if (user == null) {
                user = User()
                userThreadLocal.set(user)
            }
            return user
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        Thread(runnable, "thread1").start()
        Thread(runnable, "thread2").start()
    }
}


class User {
    var age = 0
}
