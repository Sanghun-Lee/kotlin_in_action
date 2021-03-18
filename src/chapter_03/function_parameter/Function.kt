package chapter_03.function_parameter

class Function {
}

/*
 * collection.withIndex()
 * index와 element를 같이 리턴해준다.
 * return type : Iterable<IndexedValue<T>>
 * IndexedValue<T> : public data class IndexedValue<out T>(public val index: Int, public val value: T)
 *
 * list = ['a', 'b', 'c']
 * list.withIndex() : [0, 'a'], [1, 'b'], [2, 'c']
 */

// Collection의 새로운 toString을 정의
// [1, 2, 3] -> (1: 2: 3:) 이런식으로?
fun <T> joinToString(collection: Collection<T>, separator: String, prefix: String, postfix: String): String {
    val result = StringBuilder(prefix)
    for ((index, element) in collection.withIndex()) {
        if (index > 0) result.append(separator)  // 첫 원소는 구분자를 제거한다.
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}

// default parameter value
@JvmOverloads
fun <T> joinToStringWithDefaultParameter(
    collection: Collection<T>,
    separator: String = ", ",
    prefix: String = "",
    postfix: String = ""
): String {
    // 같은 내용
    val result = StringBuilder(prefix)
    for ((index, element) in collection.withIndex()) {
        if (index > 0) result.append(separator)  // 첫 원소는 구분자를 제거한다.
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}

fun main() {
    /* 기존 Java에서도 사용하는 메소드 호출 */
    val list = listOf(1, 2, 3)
    println(joinToString(list, "; ", "(", ")"))

    /* 이름 붙인 인자 */
    println(joinToString(list, separator = "; ", prefix = "(", postfix = ")"))

    println(joinToStringWithDefaultParameter(list))
}