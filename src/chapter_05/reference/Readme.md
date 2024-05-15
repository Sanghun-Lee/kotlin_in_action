## 5.1.5 멤버 참조

람다 내에서 전달하려는 코드가 이미 함수로 정의되어있는경우

한번 더 코드를 호출하면되지만, 이는 중복이기 때문에 함수를 직접 넘기기 위한 방법

즉 함수를 `값`으로 바꿀 때 사용한다. 이를 위해선 이중콜론 `::`을 사용한다.

` val getAge = Person::age`

`::`을 사용하는 식을 `멤버 참조`라고 부른다.

멤버 참조는 프로퍼티나 메서드를 하나만 호출하는 값으로 만들어준다.

```kotlin
val getAge = { person: Person -> person.age }
val getAge = Person::age // 위 getAge와 같은 표현

fun main() {
    val person = Person("kim", 30)
    getAge.invoke(person)
}
```

멤버 참조는 그 멤버를 호출하는 람다와 같은 타입이다.

그래서 아래와같이 자유롭게 쓸 수 있다.

```kotlin
people.maxBy(Person::age)
people.maxBy { p -> p.age }
people.maxBy { it.age }
```

멤버 참조는 **인자가 여러개**인 다른 함수에게 작업을 위임할 때 편하게 사용할 수 있다.

```kotlin
// action과 nextAction은 동일한 역할을 한다.
val action = { person: Person, message: String -> sendEmail(person, message) }
val nextAction = ::sendEmail
```

**생성자를 멤버 참조**처럼 사용하면 클래스 생성작업을 연기하거나 저장해 둘 수 있다.

```kotlin
val createPerson = ::Person
val p = createPerson("AA", 30)
println(p)
```

**확장함수**도 동일하게 참조할 수 있다.

```kotlin
fun Person.isAdult() = age > 20
val predicate = Person::isAdult
```

---

#### 바운드 멤버 참조

코틀린 1.1부터는 클래스의 참조가 아닌, 필드에 대한 참조를 사용할 수 있다.

그렇게 되면 객체가 미리 정의되어있기때문에 함수 인자로 넘겨주지 않아도 된다.

```kotlin
val p = Person("AAA", 30)

val getAge = Person::age
println(getAge.invoke(p))

// 바운드 멤버 참조
val getAge = p::age // p객체 (인스턴스)에 대해 age멤버를 참조한다.
println(getAge.invoke())
```