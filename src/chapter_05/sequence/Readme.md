# 5.3 지연 계산 Sequence

map, filter를 `List`에 사용할 경우 중간 계산되는 컬렉션을 즉시 생산된다.

하지만 `sequence`를 사용하면 중간 임시 컬렉션을 생산하지 않고 컬렉션 연산을 연쇄적으로 수행할 수 있다.

지연계산의 경우 `Sequence`인터페이스에서 시작된다.

해당 인터페이스는 한 번에 하나씩 열거될 수 있는 원수의 시퀀스를 표현한다.

```kotlin
public interface Sequence<out T> {
    public operator fun iterator(): Iterator<T>
}

public interface Iterator<out T> {
    public operator fun next(): T
    
    public operator fun hasNext(): Boolean
}
```

> Q. 왜 iterator, next, hasNext에 "operator"가 붙는걸까
> 
> A. https://discuss.kotlinlang.org/t/iterator-next-and-hasnext-why-are-they-operator-fun/11005/2
> next, hasNext, iterator 함수들이 명시적으로 함수 호출 없이 사용될 수 있기 때문.
> 
> 아래 kotlin 예시 참고

```kotlin
// iterator, next, hasNext의 operator
class A {
    private var i = 0
    operator fun hasNext() = i < 10
    operator fun next() = i++
}

operator fun A.iterator() = this

fun foo() {
    for(i in A()) println(i) // 1\n2\n3\n4\n...
}
```

sequence로 생성 후 리스트를 계산했다면, 마지막에는 toList로 다시 List로 만들어주는것이 좋다.

왜냐하면 sequence는 원소를 차례로 이터레이션 할 때는 좋지만, 인덱스를 이용해서 접근하는등의 다른 기능을 제공하지 않기 때문이다.

