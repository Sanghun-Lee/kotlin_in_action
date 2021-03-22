package chapter_03.top_level

const val CHAPTER = 3

fun <T> joinToString(collection: Collection<T>, separator: String, prefix: String, postfix: String): String {
    val result = StringBuilder(prefix)
    for ((index, element) in collection.withIndex()) {
        if (index > 0) result.append(separator)  // 첫 원소는 구분자를 제거한다.
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}
// 클래스 없이 해당 파일의 최상단에 함수만 존재한다.