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