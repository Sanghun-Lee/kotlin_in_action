package chapter_04.`object`

/**
 * object class의 최초 호출시점 테스트
 * 처음 object class를 접근하는 시점에, 생성자호출없이 바로 객체가 생성된다.
 */
object ObjectClassCreateTest {
    fun start() {
        println("ObjectClass start call")
    }

    init {
        println("ObjectClass Created")
    }
}

/**
 * function main start
 * ObjectClass Created
 * ObjectClass start call
 */
fun main() {
    println("function main start")
    ObjectClassCreateTest.start()
}