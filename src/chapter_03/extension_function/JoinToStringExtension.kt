package chapter_03.extension_function

import java.lang.StringBuilder

// Collection<T>에 대한 확장함수
fun <T> Collection<T>.joinToString(separator: String = ", ", prefix: String = "", postfix: String = ""): String {
    val result = StringBuilder(prefix)
    for ((index, element) in this.withIndex())
        if (index > 0) result.append(separator)
    result.append(postfix)
    return result.toString()
}

// Collection<String>에 대한 확장함수
fun Collection<String>.join(separator: String = ", ", prefix: String = "", postfix: String = "") =
    joinToString(separator, prefix, postfix)


class JoinToStringExtension {
}

fun main() {
    val list = listOf(1, 2, 3)
    list.joinToString()

    println(listOf("one", "two", "three").join(" "))
//    list.join()
}