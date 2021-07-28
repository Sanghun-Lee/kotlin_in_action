## 내부 클래스 (Inner class)

### 자바의 내부 클래스와 다른 점

- 코틀린에서 내부 클래스는 기본적으로 **정적 내부 클래스**이다. (public static class)
- 일반 내부 클래스로 선언하려면 `inner`키워드를 앞에 붙여야 한다.
- inner class의 경우, 외부 클래스 객체를 참조하기 위해선 `this@외부클래스`를 이용해서 참조할 수 있다.

```kotlin
class OutClass {
    class StaticInnerClass {
        fun thisExample() {
            val staticInnerClassObject = this
            /* val outClassObject = this@OutClass // 불가능 */
        }
    }
    inner class InnerClass {
        fun thisExample() {
            val innerClassObject = this
            val outClassObject = this@OutClass
        }
    }
}
```

| 클래스 B 안에 정의된 클래스 A | 자바에서는 | 코틀린에서는 |
| --- | --- | --- |
| 중첩 클래스 (바깥쪽 클래스에 대한 참조를 저장하지 않음, 정적 내부 클래스) | static class A | class A |
| 내부 클래스 (바깥쪽 클래스에 대한 참조를 저장함) | class A | inner class A |

> 기본적으로 정적 내부 클래스라서 얻는 이점
>
> `JavaInnerClass.java`
>
> Java에서 내부 클래스의 경우 외부 클래스가 생성되어야 사용가능해서 묵시적으로 외부 클래스를 참조한다. (this@OUtClass)
>
> 그래서 내부 클래스를 직렬화 할 때 내부 클래스가 외부 클래스도 참조하기 때문에, 외부 클래스가 직렬화 되지 못하고
>
> 그래서 NotSerializableException이 발생한다.