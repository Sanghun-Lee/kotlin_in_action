package chapter_05.reference

/**
 * 멤버 참조 여러 예시
 */
fun main() {
    // 같은파일의 최상위함수에 정의되어있는 함수도 멤버참조 할 수 있다.
    run(::salute)

    val person: Person = Person("hello", 30)

    val getAge = { p: Person -> p.age}
    val getAge2 = Person::age
    // 위 두 정의는 동일한 역할을 한다.

    println(getAge.invoke(person))
    println(getAge2.invoke(person))

    // 여러인자를 다른함수에 바로 넘겨줄 때 멤버 참조를 사용하면 빠르게 넘겨줄 수 있다.
    // 아래 println과 같은 예시보다는, 함수의 리턴값을 바로 다음 함수에게 넘겨줄 때 (dialogBuilder) 사용하면 간략하게 작성할 수 있다.
    val action = { sender: Int, address: String, title: String ->
        sendEmail(sender, address, title)
    }

    val nextAction = ::sendEmail

    println(action.invoke(123, "aa@aa.com", "hello"))
    println(nextAction.invoke(123, "aa@aa.com", "hello"))

    // 확장함수의 멤버 참조
    val predicate = Person::isAdult
    println(predicate.invoke(person))

    // 생성자의 멤버 참조
    val createPerson = ::Person
    println("createPerson declare")
    val p = createPerson("aaa", 10) // 해당시점에 객체가 생성된다.
    val p2 = createPerson("bbb", 20) // 동일하게 해당시점에 객체가 생성된다.
}

fun salute() = println("salute")

data class Person(val name: String, val age: Int) {
    init {
        println("person object created, name : $name, age : $age")
    }
}

fun sendEmail(sender: Int, address: String, title: String) {
    // do something...
}

fun Person.isAdult() = age > 20