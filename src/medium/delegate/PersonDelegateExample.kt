package medium.delegate

interface Greeter {
    fun greet(name: String)
}

class EnglishGreeter : Greeter {
    override fun greet(name: String) {
        println("Hello, $name!")
    }
}

class SpanishGreeter: Greeter {
    override fun greet(name: String) {
        println("¡Hola, $name!")
    }
}

/**
 * by 키워드로, Person이 구현하고있는 Greeter를 Person이 가지고있는 생성자 객체인 greeter가 위임받음으로써
 * Person객체가 생성될 때 전달받는 Greeter객체에게 동작에 대한 책임을 위임시킨다.
 */
class Person(private val greeter: Greeter) : Greeter by greeter
// 아래와 같은 코드를 적지않아도 된다.
//{
//    override fun greet(name: String) {
//       greeter.greet(name)
//    }
//}


fun main() {
    val person1 = Person(EnglishGreeter())
    val person2 = Person(SpanishGreeter())
    person1.greet("John")  // Output: Hello, John!
    person2.greet("John")  // Output: ¡Hola, John!
}