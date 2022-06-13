package FaceQuestion.线程


object ChainOfResponsibilityMode {

    @JvmStatic
    fun main(args: Array<String>) {
        val interceptors = arrayListOf<Interceptor>()
        interceptors.add(RetryAndFollowUpInterceptor())
        interceptors.add(BridgeInterceptor())
        interceptors.add(CacheInterceptor())
        interceptors.add(ConnectInterceptor())
        interceptors.add(CallServerInterceptor())
        val chain = Chain(interceptors, 0)
        println(chain.processed("Http请求"))
    }

}

class Chain(
    private val interceptors: List<Interceptor>,
    private val index: Int,
    val request: String = ""
) {

    fun processed(request: String): String{
        if (index >= interceptors.size) return request
        val chain = Chain(interceptors, index + 1, request)
        val interceptor = interceptors[index]
        return interceptor.intercept(chain)
    }

}

interface Interceptor{
    fun intercept(chain: Chain): String
}

class RetryAndFollowUpInterceptor: Interceptor {
    override fun intercept(chain: Chain): String {
        println("开始测试重试重定向拦截器")
        val processed = chain.processed(chain.request + "==>经过重试重定向拦截器")
        println("结束执行重试重定向拦截器")
        return processed
    }
}

class BridgeInterceptor: Interceptor {
    override fun intercept(chain: Chain): String {
        println("开始测试桥接拦截器")
        val processed = chain.processed(chain.request + "==>经过桥接拦截器")
        println("结束执行桥接拦截器")
        return processed
    }
}

class CacheInterceptor: Interceptor {
    override fun intercept(chain: Chain): String {
        println("开始测试缓存拦截器")
        val processed = chain.processed(chain.request + "==>经过缓存拦截器")
        println("结束执行缓存拦截器")
        return processed
    }
}

class ConnectInterceptor: Interceptor {
    override fun intercept(chain: Chain): String {
        println("开始测试连接拦截器")
        val processed = chain.processed(chain.request + "==>经过连接拦截器")
        println("结束执行连接拦截器")
        return processed
    }
}

class CallServerInterceptor: Interceptor {
    override fun intercept(chain: Chain): String {
        println("开始测试请求拦截器")
        val processed = chain.processed(chain.request + "==>经过请求拦截器")
        println("结束执行请求拦截器")
        return processed
    }
}

