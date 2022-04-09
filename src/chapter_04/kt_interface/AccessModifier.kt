package chapter_04.kt_interface

class AccessModifier {
    internal open class TalkactiveButton : KtInterfaceExample.Focusable {
        private fun yell() = println("Hey!")
        protected fun whisper() = println("Let's talk")

    }

//    // 기본 접근지정자 : public
//    fun TalkactiveButton.giveSpeech() { // error : public 멤버가 internal타입인 TalkActiveButton을 노출한다. >> 클래스를 public or 메소드를 internal로 변경
//        yell()      // private 메소드(필드)에 접근할 수 없다.
//        whisper()    // protected 멤버에도 접근할 수 없다.
//    }
}

open class TopParent {
    val topParentProperty: Int = 10
}

open class Parent: TopParent() {
    val parentProperty: Int = 20
}

class Child: Parent() {
    val childProperty: Int = 30
}

var test: TopParent = Parent()

fun main() {

    when(test) {
        is TopParent -> {
            test.topParentProperty
        }
        is Parent -> {
            test.topParentProperty
//            test.parentProperty   // 스마트 캐스팅 불가능 -> 중간에 다른곳에서 객체의값이 변경될 수 있다.
        }
        is Child -> {
            test.topParentProperty
//            test.parentProperty // 스마트 캐스팅 불가능
//            test.childProperty // 불가능
        }
    }
}