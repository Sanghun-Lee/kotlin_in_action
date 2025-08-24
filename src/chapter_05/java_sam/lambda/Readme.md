## 5.4.1 자바 메소드에 람다를 인자로 전달.

코틀린에서는 함수 인자로 함수형 인터페이스가 있다면, 람다를 이용해서 바로 전달할 수 있다.

```kotlin
// void postponeComputation(int delay, Runnable computation);

postponeComputation(1000) { println(42) } // Runnable 객체

// 또한, 무명 구현 객체를 만들어서 넘길 수 있다.
postponeComputation(1000, object : Runnable {
    override fun run() {
        println(42)
    }
})
```

람다로 넘기지 않고, 객체를 명시적으로 선언한 경우, 메소드를 호출할 때 마다 새로운 객체가 생성된다.

하지만, 람다의 경우, **외부 함수의 변수에 접근하지 않는다면(외부참조가 없다면)** 메소드를 호출할 때 마다 반복해서 사용된다.

[Lambda.kt](Lambda.kt) 파일 참고

```kotlin
fun main() {
    repeat(100) {
        postponeComputation(1000L) { // 동일한 hashCode 출력
            println(42)
        }
    }
    println()
    repeat(100) {
        postponeComputation(2000L, object : OnClickListener { // 매번 다른 hashCode 출력
            override fun onClick() {
                println(42)
            }
        })
    }
}

fun postponeComputation(delay: Long, clickListener: OnClickListener) {
    println("delay : $delay, clickListener hashCode : ${clickListener.hashCode()}")
}
```

하지만 아래와 같이 외부 변수를 참조하게 된다면 객체를 매번 새롭게 만들어진다.

```kotlin
val value = "String value"
repeat(20) {
    postponeComputation(3000L) { println(value) }
}
```

---

### 궁금해서 실험해본 테스트

- 외부참조가 매번 새로운 객체를 생성한다고 하는데, 그럼 인터페이스 내에 정의된 값이라면 어떻게 될까?


```java
// 예시 자바 인터페이스
public interface Runnable {
    public void run(int delay);
}
```


```kotlin
repeat(20) {
    postponeComputation2(4000L) { println(it) }
}

/**
 * output
 * delay : 4000, runnable hashCode : 1128032093
 * delay : 4000, runnable hashCode : 1128032093
 * delay : 4000, runnable hashCode : 1128032093
 * delay : 4000, runnable hashCode : 1128032093
 * ....
 */
```

위 케이스의 경우 기존 객체가 유지된다. (매번 새롭게 객체를 만들지 않는다.)


---

> 람다의 자세한 구현
> 람다가 외부의 값을 참조하는경우, 컴파일 후에는 아래와 같은 바이트코드가 만들어진다. 
> ```kotlin
> // as-is
> fun handleComputation(id: String) { postponeComputation(1000L) { println(id) } }
> 
> // to-be
> class HandleComputation(val id: String) : Runnable {
>     override fun run() {
>         println(id)
>     }
> }
> fun handleComputation(id: String) { postponeComputation(1000L, HandleComputation(id))}
> ```

기존 람다를 넘기면 무명 객체가 만들어지는데,

코틀린 `inline`으로 표시된 코틀린 함수에게 람다를 넘긴다면, 아무런 무명 클래스가 만들어지지 않는다. (inline은 8.2장에)