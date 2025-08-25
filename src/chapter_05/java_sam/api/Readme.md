## 5.5 수신 객체 지정 람다: with, apply

> 수신 객체를 명시하지 않고, (특정 객체를 지정하지 않고)
> 
> 람다의 본문 안에서 다른 객체의 메소드를 호출 가능하게 해주는 함수를 `수신 객체 지정 람다` 라고 부른다.


### with 함수

어떤 객체의 이름을 반복하지 않고도 그 객체의 다양한 연산을 수행한다.

- 원형

```kotlin
inline fun <T, R> with(receiver: T, block: T.() -> R): R {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return receiver.block()
}
```

특정 객체 `receiver`를 받아서, 이 객체를 람다 (block) 안에서 호출할 수 있다.

- 예시

```kotlin
import java.lang.StringBuilder

fun alphabet(): String {
    val stringBuilder = StringBuilder()
    return with (stringBuilder) { // 메소드를 호출하려는 수신 객체를 지정한다.
        for (letter in 'A'..'Z') {
            this.append(letter) // this 키워드로 수신객체를 바로 참조할 수 있다.
        }
        append("\n alphabets") // this를 생략하고 호출할 수 있다.
        this.toString() // 람다에서 값을 반환한다.
    }
}
```

특별한 구문처럼 보이지만, 실제로는 원형처럼 인자가 2개인 함수이다.

두 번째 파라미터는 람다이고, 마지막에 오는 람다를 괄호 밖으로 빼서 정의한다.

첫 번째 인자로 받은 객체를 두 번째 인자로 받은 람다의 수신 객체로 만든다.

두 번째 인자의 전달은 확장함수를 통해 이루어진다. `block: T.() -> R`

> 예전에 이렇게 `this`를 사용한 케이스는 **확장함수**가 있다.
> 
> 어떤 의미에서는 확장 함수를 수신 객체 지정 함수라고도 할 수 있다.

---

#### with를 중첩으로 사용한 경우

with를 중첩으로 사용하였을때는, `this@OuterClass`와 같이 `@외부 클래스 이름`을 붙인다.

그냥 this를 사용하게 되면 가장 가까운 with의 객체를 지칭하게 되고, 외부의 클래스를 지칭 할 때에는 위와 같은 방법으로 호출해야 한다.


---

### apply

> 람다의 결과를 원할때는 `with`를 사용하고, 처음 전달한 `수신 객체를 반환`받고 싶다면, apply를 사용하자.

- 원형

```kotlin
inline fun <T> T.apply(block: T.() -> Unit): T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    block()
    return this
}
```

apply는 **확장함수**로 이루어져 있고, 최초 수신한 객체를 그대로 반환 (`T`)한다는 점 외에는 with와 거의 동일하다.

```kotlin
fun alphabet(): String = StringBuilder().apply { 
    append("A to Z")
}.toString()
```

보통 apply의 경우, 객체의 인스턴스를 만들면서, 즉시 (해당 객체의) 프로퍼티 중 일부를 초기화 해야하는 경우에 유용하다.

---

위 예시들에서 StringBuilder + apply, with 를 사용하였는데

코틀린에서는 이런 동작을 더 우아하게 수행할 수 있는 `buildString` 함수가 있다.

StringBuilder 객체 생성 및 마지막에 toString()으로 변환해준다.

```kotlin
fun alphabet(): String = buildString {
    append("A to Z")
}
```

buildString의 경우 인자는 수신 객체는 항상 `StringBuilder`이다.

이는 추후에 11장 DSL에서 더 다룰 예정이다.