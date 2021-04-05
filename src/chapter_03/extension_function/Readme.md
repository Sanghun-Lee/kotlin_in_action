# 3.3 확장 함수

## 개요

- 확장 함수는 어떤 클래스의 밖에 선언된 함수를 해당 클래스의 멤버 메소드인 것처럼 호출할 수 있다.

```kotlin
fun String.lastChar(): Char = this.get(this.length - 1)
```

위와 같이 작성하면 `String`클래스 멤버인 것 처럼 사용할 수 있다.

> 확장 함수는 Java -> Kotlin변환때, Kotlin에는 없는 Java API를 사용해야 할 경우, 확장 함수를 이용해서 해당 API를 코틀린으로 사용할 수 있다.

- 확장할 클래스의 이름을 `수신 객체 타입 (receiver type)`이라고 한다.
    + 위 예시에서는 `String`이 수신 객체 타입이 된다.


- 호출되는 대상이 되는 값을 `수신 객체 (receiver type)`이라 부른다.
    + 위 예시에는 `this`가 수신 객체가 된다.

---

### 확장 함수 사용 예시

```kotlin
println("Kotlin".lastChar())
// 수신 객체 타입 : String, 수신 객체 : "Kotlin"
```

- 확장 함수는 클래스 파일이 있는 한 그 클래스에 원하는대로 확장할 수 있다.

> 클래스를 삭제할 때는 해당 클래스로 만들어진 확장 함수도 있는지 확인하는 편이 좋을것 같다.

- 일반 메소드에서 사용하는 것 처럼, 확장 함수에서도 `this`를 생략할 수 있다.

```kotlin
fun String.lastChar(): Char = get(length - 1)   // get과 length는 String에 있는 메소드, 필드이다.
```

- 확장 함수는 다른 확장 함수를 호출할 수 있다.

- 즉 확장 함수도, 클래스의 메소드처럼 똑같이 취급받는다.

- 확장 함수는 `캡슐화`가 유지된다. 그래서 `protected`, `private`멤버를 사용할 수 없다.

### 3.3.1 import와 확장함수

- 한 클래스 내에 같은 이름의 확장 함수가 있을 수 있기 때문에, 다른 클래스에서 확장 함수를 사용하기 위해선 import해야한다.

```kotlin
import [확장 함수가 정의된 클래스의 package이름].[확장 함수 이름]
import strings.lastChar
```

- `as`키워드를 이용해 다른 이름으로 사용할 수 있다.

```kotlin
import strings.lastChar as last

val c = "Kotlin".last()
```

### 3.3.2 자바에서 확장 함수 호출

- 내부적으로 확장 함수는 수신 객체를 첫 번재 인자로 받는 **정적 메소드**이다.

- 그래서 자바에서 호출해도 그냥 클래스의 정적 메소드를 호출하는 것 처럼 사용하면 된다.

```java
char c=ExtensionFunctionKt.lastChar("Java");
```

### 3.3.3 확장 함수로 joinToString정의

> 예시 파일 : [JoinToStringExtension.kt](JoinToStringExtension.kt) 확인

```kotlin
fun <T> Collection<T>.joinToString() // 모든 타입의 collection에 대한 확장함수 정의 

fun Collection<String>.join() // String타입의 collection에 대한 확장 함수 정의
```

### 3.3.4 확장 함수의 override

- 예시 파일 : [ExtensionFunctionOverride.kt](ExtensionFunctionOverride.kt)

- 확장 함수는 override가 불가능 하다.

> 왜 확장 함수는 override가 불가능 할까?
>
> 이를 이해하기 위해선 **동적 디스패치**와 **정적 디스패치**에 대해서 알아야 한다.

#### 동적 디스패치

```kotlin
open class View {
    open fun click() = println("View Clicked")
}

class Button : View {
    override fun click() = println("button clicked")
}
```

- 위와 같은 부모, 자식 클래스가 있을 때

```kotlin
val view: View = Button()
view.click()    // button clicked
```

- 다음과 같이 view의 click함수를 호출하면, view는 `View`타입의 변수이지만,
- Button객체를 가지고있기 때문에, Button클래스의 click이 호출된다.

> 이처럼 **실행 시점**에 변수가 가진 객체 타입에 따라 호출될 대상 메소드가 동적으로 변경되는 경우를 **동적 디스패치**라 한다.

#### 정적 디스패치

- 정적 디스패치는 동적 디스패치와 반대로

> **컴파일 시점**에 변수 타입에 따라 정해진 메소드를 호출하는 것을 **정적 디스패치**라고 한다.

### 확장 함수 override

- 확장 함수는 호출한 변수의 타입에의해서만 확장 함수가 호출된다.
- 즉, 변수가 Button객체를 가지고 있다 하더라도 해당 변수가 view타입인 경우, **view의 확장 함수가** 호출된다.
- 확장 함수는 정적 디스패치 이다.
- 그래서 확장 함수는 override가 되지 않는다.

---

### 3.3.5 확장 프로퍼티

- 함수를 확장할 수 있는 것 처럼
- 프로퍼티(변수)도 확장 할 수 있다.
- 하지만 프로퍼티는 초기화 코드를 사용할 수 없기 때문에
- 최소한 `getter`는 꼭 정의를 해야한다.

```kotlin
val String.lastChar: Char
    get() = get(length - 1)
```

- String은 문자열 중 값 하나만 바꿀 수 없기 때문에
- `StringBuilder`를 사용하면 맨 마지막 문자는 변경 가능해서 `var`로 프로퍼티를 만들 수 있다.

```kotlin
var StringBuilder.lastChar: Char
    get() = get(length - 1)
    set(value: Char) {
        this.setCharAt(length - 1, value)
    }
```

> 확장 프로퍼티의 사용

```kotlin
/* kotlin */
println("Kotlin".lastChar)  // n
val sb = StringBuilder("Kotlin?")
sb.lastChar = "!"
println(sb) // Kotlin!
```

```java
/* java */
StringUtilKt.getLastChar("Java")    // a
```
