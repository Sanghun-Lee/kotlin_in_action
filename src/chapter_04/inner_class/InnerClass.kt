package chapter_04.inner_class

import chapter_04.inner_class.JavaInnerClass.Button
import java.io.ByteArrayOutputStream
import java.io.ObjectOutputStream
import java.io.Serializable

fun main() {
    val button = Button()

    /**
     * java.io.NotSerializableException이 발생한다.
     */
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

// JavaInnerClass의 Button 정적 클래스와 동일한 클래스
// 다만 내부의 ButtonState클래스가 코틀린은 자동으로 정적 클래스이다.
class ButtonKt: View {
    override fun getCurrentState(): State {
        return ButtonState()
    }

    override fun restoreState(state: State) {
        /* .. */
    }

    // 코틀린은 내부 클래스가 자동으로 정적 클래스이다.
    class ButtonState: State {}
}