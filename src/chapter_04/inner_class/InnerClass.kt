package chapter_04.inner_class

import java.io.ByteArrayOutputStream
import java.io.ObjectOutputStream
import java.io.Serializable

class InnerClass {
    fun test() {
        val button = Button()

        val byteStream = ByteArrayOutputStream()
        val outputStream = ObjectOutputStream(byteStream)
        outputStream.writeObject(button.ButtonState())

        val serialized = byteStream.toByteArray()

        println(button)
    }
}

fun main() {
    val button = Button()

    val byteStream = ByteArrayOutputStream()
    val outputStream = ObjectOutputStream(byteStream)
    outputStream.writeObject(button.ButtonState())

    val serialized = byteStream.toByteArray()

    println(serialized)
}

interface State : Serializable

interface View {
    fun getCurrentState(): State
    fun restoreState(state: State) {}
}