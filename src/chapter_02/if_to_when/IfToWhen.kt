package chapter_02.if_to_when

import java.lang.IllegalArgumentException

interface Expr
class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr

fun eval(e: Expr): Int =
    if (e is Num) {
        e.value
    } else if (e is Sum) {
        eval(e.right) + eval(e.left)
    } else {
        throw IllegalArgumentException("unknown expression")
    }

// if to when
fun evalWhen(e: Expr): Int =
    when (e) {
        is Num -> e.value
        is Sum -> evalWhen(e.right) + evalWhen(e.left)
        else -> throw IllegalArgumentException("unknown expression")
    }

fun evalWithLogging(e: Expr): Int =
    when (e) {
        is Num -> {
            println("num : ${e.value}")
            e.value
        }
        is Sum -> {
            val left = evalWithLogging(e.left)
            val right = evalWithLogging(e.right)
            println("sum : ${left} + ${right}")
            left + right
        }
        else -> throw IllegalArgumentException("unknown expression")
    }

var nullableGlobalValue: Num? = null

fun getNullableNum(): Num? = Num(10)
fun getNonNullableNum(): Num = Num(20)

fun main() {
    var expr: Expr = Num(10)
    when(expr) {
        is Num -> {
//            expr = Sum(Num(10), Num(20)) // 해당 코드가 있으면 아래 .value문은 에러가 발생한다.
            println(expr.value) // var 타입이라도 스마트 캐스팅이 가능하다.
        }
    }

    var nullableValue: Num? = null

    if(nullableValue != null) {
        nullableValue = getNonNullableNum() // 캐스팅 가능
//        nullableValue = getNullableNum() // non-null 캐스팅 불가능
        println(nullableValue.value)
    }

    if(nullableGlobalValue != null) {
//        println(nullableGlobalValue.value) // 해당 코드는 non-null하지 않게 된다.

    }
}