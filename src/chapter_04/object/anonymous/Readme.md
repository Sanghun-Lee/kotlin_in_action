# 4.4.4 객체 식 - 무명 내부 클래스 선언

무명 객체 (anonymous object)를 정의할 때 `object` 키워드를 사용한다.

대표적으로 아래와 같이 이벤트 리스너를 구현할 수 있다.

```kotlin
view.setOnClickListener(
    object: OnClickListener() {
        override fun onClick(view: View) {
            // do something
        }
    }
)
```

다음과 같이 선언할 때에는 인스턴스에 이름을 붙이지 않는다.

객체에 이름을 붙여야 한다면 아래와 같이 변수에 대입하면 된다

```kotlin
val listener = object: OnClickListener() {
    override fun onClick(view: View) {
        // do something
    }
}
```

코틀린은 자바와 달리, 무명 클래스는 여러 인터페이스를 구현하거나, 클래스를 확장하면서 인터페이스를 구현할 수 있다.
- [VariousAnonymousClass.kt 참고](VariousAnonymousClass.kt)

코틀린 또한, 자바와 마찬가지로, 익명 객체가 선언된 부분의 함수 필드에 접근할 수 있다.

자바는 `final`필드만 접근할 수 있었던것에 반해, 코틀린은 모든 변수에 접근할 수 있다.

```kotlin
fun countClicks() {
    var clickCount = 0
    view.setOnClickListener(object : OnClickListener {
        override fun onClick() {
            clickCount++ // 함수에 정의되어있는 로컬 변구의 값을 변경한다.
        }
    })
}
```