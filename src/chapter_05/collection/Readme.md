## 5.1 람다 소개 및 컬렉션

`람다 (lambda)` 및 `람다 식`은 기본적으로 다른 함수에 넘길 수 있는 작은 코드 조각을 뜻한다.

람다가 없었을 때는 아래 예시와 같이 무명 내부 클래스로 클랙스 객체를 넘겼어야 했다

```java
button.setOnClickListener(new OnClickListener() {
    @Override
    public void onClick(View view) {
        /* do something */
    }
})
```

하지만 Java8 버전 이후에는 람다가 추가되면서 아래와 같이 한 줄로 클래스 객체를 넘길 수 있다.

```java
button.setOnClickListener { /* do something */ }
```

람다를 많이 사용해온 컬렉션에 대해 살펴보자.

---

### 5.1.2 람다와 컬렉션

> [MaxByLambda 참고](./MaxByLambda.kt)

컬렉션(리스트)에 사용할 수 있는 람다 함수들이 있다.

대표적으로는 예시에 있는 것 처럼 `maxBy`함수가 있고, 그 외에도 `forEach`, `sum`, `groupBy`, `map`, `filter`등등이 존재한다.

---

### 5.1.3 람다 식의 문법 (표현)

람다식은 아래와 같이 항상 중괄호에 위치하고, 화살표 (->)를 기준으로 왼쪽은 파라미터, 오른쪽은 본문이 된다.

`{ x: Int, y : Int -> x + y }`

그래서 람다 식을 변수에 넣을수도 있다.

```kotlin
val sum = { x: Int, y: Int -> x + y }
println(sum(1, 2))
```

하지만 굳이 위와 같이 바로 사용하면 되는데, 변수에 저장하는 경우는 없기 때문에, 바로 사용하는 경우가 많다.

이전 maxBy 예제에서 줄여서 작성하지 않고, 풀어서 작성하게되면 아래와 같은 형태가 된다.

`peoples.maxBy({ p: Person -> p.age })`

하지만, 위 코드를 아래와 같은 규칙으로 줄일 수 있다.

1. 코틀린에서 마지막 인자가 람다 식이라면 밖으로 뺄 수 있다.
> `peoples.maxBy() { p: Person -> p.age}`

2. 인자가 람다 식 하나밖에 없고, 괄호 밖에 썼다면, 괄호를 없앨 수 있다.
> `peoples.maxBy { p: Person -> p.age }`

3. p가 Person 타입인것은 컴파일러가 알기 때문에 제거할 수 있다.
> `peoples.maxBy { p -> p.age }`

4. 인자가 하나라면, `it`이란 이름으로 자동생성되기 때문에, p 를 없앨 수 있다.
> `peoples.maxBy { it.age }`

또한, 람다 식이 여러줄이라면, 마지막 줄 값이 리턴값이 된다.