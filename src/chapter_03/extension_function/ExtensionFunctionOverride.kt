package chapter_03.extension_function

class ExtensionFunctionOverride {
}

open class View {
    open fun click() = println("View Clicked")
}

class Button : View() {
    override fun click() = println("button clicked")
}

fun View.showOff() = println("view ext function")
fun Button.showOff() = println("button ext function")

fun main() {
    val view: View = Button()
    view.click()    // button clicked

    view.showOff()  // view ext function출력
}