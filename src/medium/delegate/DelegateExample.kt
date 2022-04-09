package medium.delegate

class DelegateExample: Analytics by AnalyticsImpl() {
    fun onCreate() {
        registerAnalytics()
    }
}

fun main() {
    val delegate = DelegateExample()
    delegate.onCreate()
}

interface Analytics {
    fun registerAnalytics()
}

class AnalyticsImpl: Analytics {
    override fun registerAnalytics() {
        println("analytics register complete")
    }
}

interface Logout {
    fun registerLogout()
}

class LogoutImpl: Logout {
    override fun registerLogout() {
        println("logout register complete")
    }
}