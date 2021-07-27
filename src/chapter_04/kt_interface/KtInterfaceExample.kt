package chapter_04.kt_interface

class KtInterfaceExample {
    interface Clickable {
        fun click()

        // interface default method
        fun showOff() {
            println("I'm clickable!")
        }
    }

    interface Focusable {
        fun setFocus(b: Boolean) = println("I ${if (b) "got" else "lost"} focus.")
        fun showOff() = println("I'm focusable!")
    }

    class Button : Clickable, Focusable {
        override fun click() {}

        // Clickable과 Focusable 둘 다 showOff메소드가 있기 때문에
        // 그 둘을 동시에 구현하는 Button에서는 명시적으로 showOff메소드를 재정의 하여야 한다.
        // 하지 않으면 컴파일 에러가 발생한다.
        override fun showOff() {
            super<Clickable>.showOff()  // Clickable의 showOff를 호출
            super<Focusable>.showOff()  // Focusable의 showOff를 호출
        }
    }
}

class ParentParentClass {
    var fragileValue = 30

    open fun fragileClass() {
        fragileValue = 100
        println("ParentParentClass")
    }
}

