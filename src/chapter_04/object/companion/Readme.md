## Companion Object

코틀린에서는 static 키워드가 없기때문에, 클래스 내에 정적이 멤버가 없다.

하지만 코틀린에서는 `최상위 함수`와 `객체 선언(object)`을 활용한다.

대부분의 경우에는 최상위 함수를 사용하면 자바의 static method, static field로 활용이 가능하지만...

최상위 함수는 특정 함수의 private 생성자, 프로퍼티, 메서드에 접근할 수 없다.

그래서 팩토리 메서드처럼 클래스의 인스턴스와는 관계없이 호출되어야 하지만, 클래스 내부 정보에 접근해야 하는 함수가 필요할 때에는 `companion object`를 사용해야 한다.

```kotlin
// top level 함수의 한계
class Foo() {
    private val foo: Int = 10
}

fun topLevelFunction() {
    // Foo 함수의 foo 접근불가
}
```

### companion object 선언

클래스 내에 있는 object 들 중에 `companion` 키워드를 붙이면 이는 `동반 객체`가 된다.

`동반 객체`는 상위 클래스의 private 접근자에 접근이 가능해지고, 사용할 때 해당 클래스의 정적 메서드처럼 호출하면 된다.

```kotlin
class A {
    companion object {
        fun bar() {
            println("Companion object call")
        }
    }
}

fun main() {
    A.bar()
}
```

`동반 객체`는 클래스의 private 생성자에도 접근할 수 있어서 팩토리 메서드가 오기에 적합하다. (`getNewInstance`)

4.1.2에서 작성했던 [User interface와 FaceBookUser, SubscribeUser](../property/Example.kt)를 companion object의 factory method를 이용하여 간단하게 변경해보자.

- [CompanionObjectFactoryMethod.kt 참고](CompanionObjectFactoryMethod.kt)

하지만, 상속을 하는 경우에는 하위 클래스에서 상위 클래스의 `companion object`에 정의된 함수를 재정의(override)할 수 없다.

그래서 경우에 따라서 부 생성자를 여러개 두거나, 팩토리 메서드를 만드는것이 좋다.

---

### Companion object Name

`companion object`는 클래스 안에 정의된 일반 객체이다.

그래서 `companion object`에 이름을 붙이거나 인터페이스를 상속하거나, 확장함수와 프로퍼티를 정의할 수 있다.

#### Compaion object name

`companion object`에 이름 붙이기

JSON 객체를 직렬화 로직 추가

```kotlin
class Person(val name: String) {
    companion object Loader {
        fun fromJSON(jsonText: String): Person = /* ... */
    }
}

fun main() {
    Person.Loader.fromJSON("") // 이름을 같이 넣어서 호출가능
    Person.fromJSON("") // 이름을 굳이 적지않아도 된다. (없으면 Compaion이 기본값)
}
```

### Compaion object interface impl

`companion object`도 인터페이스를 구현할 수 있다.

또한 `companion object`를 참조할 때 (인자로 넘겨줄 때) 클래스 이름을 바로 사용할 수 있다.

- [CompanionObjectInterfaceImpl.kt 참고](CompanionObjectInterfaceImpl.kt)

```kotlin
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

fun <T> loadFromJSON(factory: JSONFactory<T>) : T {
    // ....
}

fun main() {
    val person = loadFromJSON(Person) // JSONFactory를 필요로하는데, Person이름을 바로 넘겨준다.
}
```

`companion object`는 해당 클래스에 정의된 정적 (static)필드로 컴파일된다.

또한 따로 이름을 붙이지 않았다면 java에서는 `Companion`이란 이름으로 접근할 수 있다.

`Person.Companion.fromJSON("...");`

- kotlin에서는 Person 클래스의 Companion 이란 이름의 정적 (static)필드로 취급


### Companion object 확장함수

특정 class의 companion object 또한, 확장함수를 만들 수 있고 만든 확장함수를 해당 class의 companion object 호출하는 것 처럼 사용할 수 있다.

```kotlin
class Person(val firstName: String, val lastName: String) {
    companion object {} // 비어있는 companion object 선언
}

// 다른 class 파일
// Person class의 확장함수 선언
fun Person.Companion.fromJSON(json: String): Person {
    // ...
}

// 마치 Person 내부에 정의되어있는 companion object를 호출하는 것 처럼 사용
val person: Person = Person.fromJSON("json response")
```

다만 이렇게 다른 클래스의 companion object를 확장해서 사용하기 위해선

**해당 클래스에서 companion object가 꼭 있어야만 정의할 수 있다.**