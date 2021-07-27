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