## 4.4 Object

코틀린에서 object의 사용은 클래스를 정의하면서 동시에 객체(instance)를 생성한다.

object를 사용하는 여러 상황
- 객체 선언은 싱글턴을 정의하는 방법 중 하나이다.
- `companion object`는 인스턴스 메서드는 아니지만, 어떤 클래스와 관련 있는 메서드와 팩토리 메서드를 담을 때 쓰인다.
- 객체 식은 자바의 `anonymous inner class`대신 쓰인다.

### 4.4.1 객체 선언: 싱글턴을 쉽게 만들기

> 객체를 하나만 필요한 클래스가 유용한 경우에 사용한다.
> 
> 예를들면 DB instance를 생성할때가 대표적이다.

일반적으로 자바에서 객체를 하나만 가지게되는 싱글턴 패턴을 사용하기 위해선

1. 생성자를 private으로 둔다.
2. 클래스 내부에서 객체를 하나 생성한다.
3. static 메서드로 해당 객체를 리턴한다.

하지만, 코틀린에서는 `object`를 선언하는 것 만으로도 쉽게 싱글턴 패턴을 만들 수 있다.

`object`로 클래스를 만드는것을 **객체 선언**이라고 하는데 이는 클래스의 선언과 단일 인스턴스의 선언을 합친 선언이다.

> `object class` 내부에는 **생성자**(주/부 생성자 모두)를 사용할 수 없다.
> 
> 싱글턴 객체는 처음 해당 객체를 사용하는 순간에 생성자 호출 없이 만들어지기 때문에 생성자를 정의할 필요가 없다.
> 
> [ObjectClassCreateTest 코드 참고](ObjectClassCreateTest.kt)

`object class`또한 클래스, 인터페이스를 상속 / 구현 가능하다.

이는 프로퍼티를 저장할 필요가 없는 경우에 상속/구현을 이용하여 object class를 만드는것이 유용하다.

[CaseInsensitiveFileComparator 참고](CaseInsensitiveFileComparator.kt)


> 이렇게 간단하게 객체를 생성할 수 있지만, 대규모 프로젝트에서는 object 사용이 적합하지 않다.
> 
> 왜냐하면 객체를 제어할 방법이 없고 (언제 생성되는지 정확히 알 수 없다) 생성자 파라미터를 넘겨줄 수 없어 단위 테스트, 객체의 의존관계를 바꿀 수 없다.

<br>

클래스 내부에 `object class`를 선언하여도 객체는 하나만 선언된다. (클래스 객체마다 하나씩 생성되지 않는다.)

```kotlin
data class Person(val name: String) {
    object NameComparator : Comparator<Person> {
        override fun compare(o1: Person?, o2: Person?): Int {
            o1.name.compareTo(o2.name)
        }
    }
}
```

---

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

`동반 객체`는 클래스의 private 접근자에도 접근할 수 있어서 팩토리 메서드가 오기에 아주 적합하다. (`getNewInstance`)

4.1.2에서 작성했던 [User interface와 FaceBookUser, SubscribeUser](../property/Example.kt)를 companion object의 factory method를 이용하여 간단하게 변경해보자.

- [CompanionObjectFactoryMethod.kt 참고](CompanionObjectFactoryMethod.kt)

하지만, 상속을 하는 경우에는 하위 클래스에서 상위 클래스의 `companion object`에 정의된 함수를 재정의(override)할 수 없다.

그래서 경우에 따라서 부 생성자를 여러개 두거나, 팩토리 메서드를 만드는것이 좋다.