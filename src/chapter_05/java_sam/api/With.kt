package chapter_05.java_sam.api

fun main() {
    withItems()
}

fun alphabet(): String {
    val stringBuilder = StringBuilder()
    return with (stringBuilder) { // 메소드를 호출하려는 수신 객체를 지정한다.
        for (letter in 'A'..'Z') {
            this.append(letter) // this 키워드로 수신객체를 바로 참조할 수 있다.
        }
        append("\n alphabets") // this를 생략하고 호출할 수 있다.
        this.toString() // 람다에서 값을 반환한다.
    }
}

fun alphabets(): String = with(StringBuilder()) {
    for (letter in 'A'..'Z') {
        this.append(letter) // this 키워드로 수신객체를 바로 참조할 수 있다.
    }
    append("\n alphabets") // this를 생략하고 호출할 수 있다.
    this.toString() // 람다에서 값을 반환한다.
}

fun withItems() {
    // 아래와 같이 사용할 순 없다.
    with("outer with") {
        with("inner with") {
            println(this) // inner with
            println(this@with) // inner with
        }
    }
}

data class Item(val number: Int)

fun duplicateWith() {
    val number = 10
    with(Item(100)) {
        println(number) // 10
        println(this.number) // 100
        // 참조하는 우선순위가 있는듯하다.
    }.apply {  }
}