import java.lang.IllegalArgumentException

interface Expr
class Num(val value: Int) : Expr {
    val values : Int get() {
        return 10
    }
}
class Sum(val left: Expr, val right: Expr) : Expr

fun main() {

}

fun eval(e: Expr): Int {
    if (e is Num) {
        val n = e as Num
        return n.value
    }
    if (e is Sum) {
        return eval(e.right) + eval(e.left)
    }
    throw IllegalArgumentException("unknown expression")
}