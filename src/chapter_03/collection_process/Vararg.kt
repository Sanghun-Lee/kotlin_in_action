package chapter_03.collection_process

fun main() {
    val list = arrayOf<String>("one", "two", "three", "four", "five", "six")
    val monthList = arrayOf<String>("Jan", "Feb", "Mar", "Apr", "May", "Jun")

//    varargExample(list) // type mismatch에러가 일어난다.
    varargExample(*list) // 아래 코드와 동일한 역할을 한다.
    varargExample(list[0], list[1], list[2], list[3], list[4], list[5])
    varargExample(*monthList)
}

fun varargExample(vararg values: String) {
    println(values)
}