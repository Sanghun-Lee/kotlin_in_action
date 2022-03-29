# 3.2.3 정적인 유틸리티 클래스 없애기 - 최상위 함수와 프로퍼티

## 최상위 함수 정의

> 예시 파일 : [Join.kt](Join.kt)

- JVM의 경우 클래스 안에 있는 코드만을 실행할 수 있다.
- 그래서 공통되는 메소드를 정의할 때는 `Util.java`를 종종 만들곤 한다.
- 코틀린에서는 해당 클래스를 작성하지 않고, **클래스 선언 없이**, 함수나 변수가 파일에 바로 올 수 있다.
- 이런 함수를 최상위 함수라고 한다.

## 최상위 함수 동작

> 참고 파일 : [Join.kt](Join.kt)

- JVM은 클래스안에 있는 코드만을 실행할 수 있기 때문에, `Join.kt`와 같은 파일은 컴파일시에 새로운 클래스를 정의해 준다.

```java
// Join.kt파일이 아래와 같이 컴파일된다.
package chapter_03.top_level;

public class JoinKt {
    public static class joinToString( /* ... */) {
        // ....
    }
}
```

- Java에서는 static class를 호출하는 것 처럼 해당 메소드를 부르면 된다.

## 최상위 프로퍼티

- 파일 최상단에 함수를 넣는 것 처럼, 프로퍼티 (변수)를 넣을 수 있다.
- 상수를 만들 때 사용할 수 있는 방법이다.
- 그냥 `val`이나 `var`로 만들게 되면 getter, setter가 같이 생길수 밖에 없다.
- java의 `public static final`타입으로 만들기 위해선 `const` 키워드를 붙여야 한다.

```kotlin
/**
 * Join.kt 파일
 */

// const 정의는 primitive type과 String type만 가능하다.
const val UNIX_LINE_SEPARATOR = "\n"
val value = "some String"
```

```text
/**
 * Join.kt에 정의한 최상위 프로퍼티, 변수 사용
 */
// ....
System.out.println(Join.UNIX_LINE_SEPERATOR)
System.out.println(Join.getValue())
```

### @JVMName

- 코틀린 최상위 함수가 포함되는 클래스의 이름을 바꾸고 싶으면 `@JVMName`어노테이션을 사용하면 된다.
- 파일 최상단, 패키지 선언 전에 사용해야 한다.

```kotlin
@file:JvmName("StringFunction")

package strings

fun joinToString(/* ... */) {
    // ...
}
```

```java
// java파일 JvmName 사용
import strings.StringFunction;
StringFunction.joinToString( /* ..... */ );
```