# 4.1 코틀린 인터페이스

## 간단한 인터페이스 및 구현

```kotlin
interface Clickable {
    fun click()
}

class Button : Clickable {
    override fun click() = println("I was clicked")
}
```

<br>

- 인터페이스 구현 시 자바에서는 `implements`키워드를 사용하지만 코틀린에서는 `:` 키워드를 사용한다.

- 함수 override시에 사용하는 키워드인 `override`도 참고

> 함수를 override 하는 경우, 반드시 override 키워드를 작성해야 한다.

<br>

### 인터페이스의 디폴트 메소드

##### 참고 파일

[KtInterfaceExample](./KtInterfaceExample.kt)<br>
[KtInterfaceJavaCompatibility](./KtInterfaceJavaCompatibility.java)

- 디폴트 메소드가 있으면 구현하는 클래스에서는 구현하지 않아도 된다.
- 자바에서 코틀린 인터페이스를 구현할 시 디폴트 메소드를 포함한 모든 메소드를 구현하여야 한다. (KtInterfaceJavaCompatibility)
    - Kotlin의 경우 Default method가 없는 Java6부터 지원하기 때문

- 코틀린에서 다이아몬드 문제가 존재할 수 있는데 (KtInterfaceExample의 Button클래스) 무조건 해당 함수를 재정의 하여야 한다.
    - 그렇지 않으면 컴파일에러가 발생한다.

---

## open, final, abstract

##### 참고 파일

[AccessModifier](AccessModifier.kt)

> 취약 기반 클래스
> [참고 사이트](https://jaehun2841.github.io/2020/07/05/object-chapter10/#상속)
>
> 부모 클래스의 변경에 의해 자식 클래스가 영향을 받는 현상을 취약한 기반 클래스 문제 라고 부른다.
>
> [KtInterfaceJavaCompatibility.java](KtInterfaceJavaCompatibility.java)의 FragileBaseCalss 참고

클래스가 취약할 수 있기 때문에, 왠만하면 상속을 금지하는것이 좋다 (Effective Java 中)

그래서 코틀린도, 기본적으로 클래스와 메소드는 final이고, 상속이나 오버라이드을 허용하려면 `open`키워드를 붙여야 한다.

- override 후 하위 클래스에서 해당 메소드를 override 금지시키고 싶을 땐 `final`을 붙인다.

`final override fun click() { }`

- 추상클래스, 추상메소드는 기본적으로 `open`상태이다.

### 가시성 변경자: 기본적으로 공개 (public, private...)

> 코틀린의 기본 visibility는 `public`이다.

- 접근 제한자 (최상위 선언 : 클래스 외부에서의 선언)

|       변경자       |             클래스 멤버       |           최상위 선언         |
| ---------------- | -------------------------- | -------------------------- |
| public(기본 가시성) | 모든 곳에서 볼 수 있다.        | 모든 곳에서 볼 수 있다.         |
| internal         | 같은 모듈에서만 볼 수 있다.     | 같은 모듈에서만 볼 수 있다.      |
| protected        | 하위 클래스 안에서만 볼 수 있다. | (최상위 선언에 적용할 수 없음)   |
| private          | 같은 클래스 안에서만 볼 수 있다. | 같은 파일 안에서만 볼 수 있다.   |

> internal의 모듈 : 한 번에 한꺼번에 컴파일되는 코틀린 파일들
>
> 모듈사이의 캡슐화를 위해서 internal이 존재한다.

- protected가 자바와 다르다는점을 기억해야한다! (자바는 같은 패키지내에서 모두 접근할 수 있다.)

- 확장함수의 경우 해당 클래스의 `private`이나, `protected`멤버는 접근할 수 없다.

