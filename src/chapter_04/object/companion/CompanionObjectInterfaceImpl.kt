package chapter_04.`object`.companion

/**
 * 모든 타입의 객체를 역직렬화를 통해 생성하는 JSONFactory
 */
interface JSONFactory<T> {
    fun fromJSON(jsonText: String) : T
}

class Person(val name: String) {
    companion object : JSONFactory<Person> { // companion object에서 interface 구현
        override fun fromJSON(jsonText: String): Person {
            TODO("Not yet implemented")
        }
    }
}

/**
 * "특정 타입"의 JSONFactory로 부터 "특정 타입"을 반환하는 loadFromJSON 함수
 */
fun <T> loadFromJSON(factory: JSONFactory<T>) : T {
    val jsonText = "" // from server
    return factory.fromJSON(jsonText)
}

fun main() {
    // JSONFactory는 Person의 companion object가 구현하였는데 Person 이름을 넘겨준다.
    val person = loadFromJSON(Person) // JSONFactory를 필요로하는데, Person이름을 바로 넘겨준다.
}