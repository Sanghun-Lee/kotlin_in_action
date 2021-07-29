## 4.1.5 봉인된 클래스 (sealed class)

### 클래스 계층 정의 시 계층 확장 제한

- 일반적으로 when 식을 쓸 때는 무조건 else문이 있어야 한다.

```kotlin
fun evel(e: Expr): Int =
    when (e) {
        is Num -> e.value
        is Sum -> eval(e.right) + eval(e.left)
        else -> throw IllegalArgumentException("unknown expression")
    }
```

- 항상 디폴트 메소드가 있는 경우, model type(Num과 같은 클래스)가 늘어날 때 when이 모든 경우를 처리하는지 제대로 검사할 수 없다.

- 즉, model type이 늘어나면 해당 코드는 else문을 타게되서 예상치 못한 상황이 생길 수 있다.

그래서 `sealed`변경자를 붙이면 상위 클래스를 상속한 하위 클래스 정의를 제한할 수 있다.

when 식을 사용할 때 else문을 사용하지 않아도 된다.

### Sealed class

- Sealed class는 open클래스이고, 생성자가 private인 클래스이다.

- 그래서 자식 클래스를 정의하기 위해선 내부 클래스로만 정의할 수 있다.

sealed class에 속한 값에 대해서 when 식을 사용하면, 나중에 sealed class의 상속 계층에 새로운 하위 클래스를 추가하면 when식이 컴파일 되지 않는다.

> sealed interface는 정의할 수 없다 (**kotlin 1.5버전에서는 정의가능**)
>
> sealed class를 자바에서 사용할 때에는 생성자가 private으로 접근을 제한 할 수 있지만,
>
> sealed interface는 자바에서 막을 수 있는 방법이 없기 때문에 사용할 수 없다 (kotlin 1.5에서는 가능)