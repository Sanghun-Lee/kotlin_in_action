package chapter_04.sealed_class

import java.lang.IllegalArgumentException

class NotSealedClass {
    interface Expr
    class Num(val value: Int) : Expr
    class Sum(val left: Expr, val right: Expr) : Expr

    fun eval(e: Expr): Int =
        when (e) {
            is Num -> e.value
            is Sum -> eval(e.left) + eval(e.right)
            else -> throw IllegalArgumentException("Unknown expression")
        }
}

class SealedClass {
    sealed class Expr { // sealed 클래스는 open 클래스, 생성자가 private인 클래스이다.
        class Num(val value: Int) : Expr()
        class Sum(val left: Expr, val right: Expr) : Expr()
        // 여기에 새로운 클래스가 생기면 eval함수의 when식에 컴파일 에러가 생긴다 -> 프로그래머가 쉽게 새로운 클래스를 챙길 수 있다.
    }

    fun eval(e: Expr): Int =
        when (e) {
            is Expr.Num -> e.value
            is Expr.Sum -> eval(e.left) + eval(e.right)
            // else 문을 쓰지 않아도 된다.
        }
}

// sealed class examples
sealed class Event() {
    init {
        println("event object created")
    }

    object Start: Event()
    object Finish: Event()
    object Error: Event()
}

sealed class Item(open val value: String) {
    init {
        println("Item object created, value : ${this.value}")
    }

    class Confirm(override val value: String): Item(value) {
        fun getString() = this.value
    }

    class Cancel(override val value: String): Item(value) {
        fun getCancelValue() = this.value
    }

    object Ignore: Item("value")
}

fun main() {
    val event: Event = Event.Start

    // object로 싱글톤으로 만들었기 때문에, 같은 객체를 바라보고있다.
    println("event == Event.start : ${event == Event.Start}") // true

    val item: Item = Item.Confirm("yes")

    // 다른 객체를 바라보고 있다.
    println("item == Item.Confirm(yes) : ${item == Item.Confirm("yes")}") // false

    val ignoreItem: Item = Item.Ignore

    println("ignoreItem == Item.Ignore : ${ignoreItem == Item.Ignore}") // true

    /**
     * 출력 결과
     * event object created
     * event == Event.start : true
     * Item object created, value : null
     * Item object created, value : null
     * item == Item.Confirm(yes) : false
     * Item object created, value : value
     * ignoreItem == Item.Ignore : true
     */
}