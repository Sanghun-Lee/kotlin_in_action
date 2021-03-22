# 3.2 함수 호출, default parameter

## 예시를 위한 함수 생성

- Collection의 toString을 재정의하는 `joinToString`
- `joinToString(collection, separator, prefix, postfix)`로 이루어져 있다.

> 함수 호출 예시
> ```kotlin
> println(joinToString(list, "; ", "(", ")"))
> // output : (1; 2; 3)
> ```

## 이름 붙인 인자

- 함수 호출은 잘 되지만, 매개변수에 대한 가독성이 떨어진다.
- 그래서 kotlin에서는 아래와 같이 호출하는 매개변수의 이름을 같이 작성할 수 있다.

```kotlin
joinToString(collection, separator = " ", prefix = " ", postfix = " ")
```

- 인자의 이름을 하나라도 명시하였으면, 그 뒤 부터는 반드시 모든 인자를 명시해야 한다. (혼동을 막기 위해)

> ! JDK8미만의 버전의 Java와 같이 사용하는 경우, 해당 기능을 사용할 수 없다...

## default parameter value

- 메소드 선언 부분의 파라미터의 기본값을 지정할 수 있다.
- Java의 너무 많은 `overloading`을 피할수 있는 장점이 있다.

```kotlin
fun <T> joinToString(
    collection: Collection<T>,
    separator: String = ", ",
    prefix: String = "",
    postfix: String = "",
): String
```

- 그래서 아래와 같이 함수를 호출할 때 일부 인자를 생략하여 호출할 수 있다.

```kotlin
/* list = [1, 2, 3] */
joinToString(list)
// output : 1, 2, 3
joinToString(list, "; ")
// output : 1; 2; 3

/* 다음과 같이 인자에 이름을 붙일 수 있다. */
joinToString(list, postfix = ";", prefix = "#")
// output : # 1, 2, 3;
```

## @JvmOverloads

- Java에는 default parameter라는 개념이 없다.
- 그래서 자바에서 코틀린 함수를 호출하는 경우 해당 코틀린 함수가 default parameter를 제공하더라도 모든 인자값을 명시해야 한다.
- 하지만, 자바에서도 코틀린 함수의 default parameter를 사용하는 것 처럼 편하게 쓰기 위해 코틀린 함수 위에 `@JvmOverloads`를 명시하면 된다.
- `@JvmOverloads` : 코틀린 컴파일러가 자동으로 맨 마지막 파라미터로부터 파라미터를 하나씩 생략한 overloading한 java method를 추가해 준다.

> JoinToString에 @JvmOverloads 추가했다면
>

```java
import java.util.Collection;

/* 자바 */
String joinToString(Collection<T> collection, String separator, String prefix, String postfix);

String joinToString(Collection<T> collection, String separator, String prefix) {
    super(collection, separator, prefix, "");
}

String joinToString(Collection<T> collection, String separator) {
    super(collection, separator, "", "");
}
String joinToString(Collection<T> collection) {
    super(collection, ", ", "", "");
}
```