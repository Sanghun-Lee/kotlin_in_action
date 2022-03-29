// 여기에 @file:JvmName("[fileName]")을 정의하면 자바에서 import 할 때 해당 파일이름으로 import한다.
// @file:JvmName("someName")

package chapter_03.top_level

const val CHAPTER = 3   // Java에서는 public static final (상수)로 취급된다. -> [FileName].CHAPTER 로 바로 쓸 수 있다. primitive type과 String만
var variable = "string data"    // getter, setter가 있는 변수로 취급된다.

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