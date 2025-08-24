## 5.4 자바 함수형 인터페이스 활용

> 코틀린 람다를 자바 API에 사용해도 아무런 문제가 없는데, 어떻게 그럴 수 있는지 확인해보자.

자바의 인터페이스 중 선언된 함수가 하나만 있는 인터페이스를 **함수형 인터페이스**, 또는 **SAM(Single Abstract Method) 인터페이스** 라고 한다.

대표적인 예로 `Runnable`, `Callable`과 같은 함수들이 있다.

코틀린에서는 이런 함수형 인터페이스를 인자로 전달할 때, 무명 클래스 인스턴스 대신, `람다`를 넘길 수 있다.

```kotlin
// button.setOnClickListener(View.OnClickListener listener)
button.setOnClickListener { view: View -> /* do something */ }
```

