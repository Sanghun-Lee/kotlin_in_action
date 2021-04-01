package chapter_03.extension_function

fun ExtensionClass.multiplyValue() = value * 2
fun ExtensionClass.printAllField() {
    println(multiplyValue())
//    println(encapsulate)
}

fun main() {
    val ext = ExtensionClass(10)
    println(ext.multiplyValue())
}