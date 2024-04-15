package chapter_04.`object`.anonymous

interface OnClickListener {
    fun onClick()
}

interface OnTouchListener {
    fun onTouchDown()
    fun onTouchUp()
}

class Test(clickListener: OnClickListener, touchListener: OnTouchListener)

fun main() {
    // 쉼표를 이용하여 여러 인터페이스를 구현할 수 있다.
    val listener = object: OnClickListener, OnTouchListener {
        override fun onClick() {
            TODO("Not yet implemented")
        }

        override fun onTouchDown() {
            TODO("Not yet implemented")
        }

        override fun onTouchUp() {
            TODO("Not yet implemented")
        }
    }

    val test = Test(listener, listener)
}