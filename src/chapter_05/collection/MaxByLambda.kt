package chapter_05.collection

/**
 * 리스트에서 가장 나이가 많은 사람을 찾기위해서는
 * 반복문을 이용해 찾았었지만, 컬랙션에 있는 람다를 사용하게되면 적은 코드로 안정적으로 찾을 수 있다.
 */
data class Person(val name: String, val age: Int)

// 컬렉션 람다를 사용하지 않은 방법
// 반복문을 돌면서 가장 나이가 많은 사람을 찾아야 한다.
fun findOldest(peoples: List<Person>) {
    var maxAge = 0
    var theOldest: Person? = null

    for (person in peoples) {
        if (person.age > maxAge) {
            maxAge = person.age
            theOldest = person
        }
    }
    println(theOldest)
}

// 컬렉션 람다 중 하나인 `maxBy`를 사용하여 가장 나이가 많은 Person 객체를 찾았다.
fun findTheOldest(peoples: List<Person>) {
    println(peoples.maxBy { it.age })
}

// 레퍼런스 참조를 통해 아래와 같이 작성할 수 있다.
// 위 함수와 동일한 역할을 수행한다.
fun findTheOldest2(peoples: List<Person>) {
    println(peoples.maxBy(Person::age))
}
