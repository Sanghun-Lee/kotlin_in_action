# 3.4 컬렉션 처리

### 1. 가변 길이 인자.
### 2. 중위 함수 호출
### 3. 라이브러리 지원

- `vararg` 키워드를 사용하면 호출 시 인자 개수가 달라질 수 있는 함수를 만들 수 있다.
- `infix` 함수 호출 구문을 사용하면 인자가 하나뿐인 메서드를 간단하게 호출할 수 있다.
- 구조 분해 선언(val (a, b) = "1" to "one")으로 여러 변수에 담을 수 있다.

## 가변 인자 함수

> 함수 매개 변수의 개수가 달라질 수 있다.
> 
> 예제파일 : [Vararg.kt](Vararg.kt)

- 자바의 `varargs`와 동일하다.
- 코들린에선 `vararg`로 사용한다.

```kotlin
val list = listOf(2, 3, 4, 5, 6)

fun listOf<T>(vararg values: T) : List<T> { /* ... */ }
```

#### 배열에 있는 값을 vararg타입의 파라미터로 넘길 때

- kotlin은 *(스프레드 연산자)를 이용해서 해당 배열을 모두 풀고 값을 넘겨주어야 한다.

```kotlin
fun main(args: Array<String>) {
    val list = lsitOf("args : ", *args)
    println(list)
}
```

## 값의 쌍 다루기 (pair)

`to` 단어는 키워드가 아니라, `중위 호출(infix call)`이라는 방법으로 메서드를 호출하는 것이다.

```kotlin
1.to("one") // to 메서드로 호출
1 to "one"  // infix 방식으로 호출
```

infix 메서드(or 확장 함수)를 만들기 위해선 인자가 하나만 있어야 한다.

```kotlin
/**
 * infix 키워드를 사용하면 해당 메서드는 중위 호출(infix call)로 호출할 수 있다.
 * 아래 예시는 to를 infix 메서드로 정의한 확장 함수이다.
 */
infix fun Any.to(other: Any) = Pair(this, other) // Pair 객체 반환
```

`Pair`는 다음과 같은 방법으로 변수에 값을 할당할 수 있다.

`val (number, name) = 1 to "one"`

위와 같은 기능이 `구조 분해 선언`이라고 한다.

앞에서 작성했던 withIndex 메서드도 이러한 구조 분해 선언을 통해서 변수에 값이 할당된다.

```kotlin
for((index, element) in collection.withIndex()) {
    /* ... */
}
```