package chapter_05.java_sam.lambda

fun main() {
    repeat(20) {
        postponeComputation(1000L) {
            println(42)
        }
    }
    println()
    repeat(20) {
        postponeComputation(2000L, object : OnClickListener {
            override fun onClick() {
                println(42)
            }
        })
    }
    println()
    val value = "String value"
    repeat(20) {
        postponeComputation(3000L) { println(value) }
    }
    println()
    repeat(20) {
        postponeComputation2(4000L) { println(it) }
    }
    println()
    repeat(20) {
        postponeComputation2(5000L) { println() }
    }
}

fun postponeComputation(delay: Long, clickListener: OnClickListener) {
    println("delay : $delay, clickListener hashCode : ${clickListener.hashCode()}")
}

fun postponeComputation2(delay: Long, runnable: Runnable) {
    println("delay : $delay, runnable hashCode : ${runnable.hashCode()}")
}