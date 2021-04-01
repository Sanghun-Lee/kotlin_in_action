# 3.3 확장 함수

## 개요

- 확장 함수는 어떤 클래스의 밖에 선언된 함수를 해당 클래스의 멤버 메소드인 것처럼 호출할 수 있다.

```kotlin
fun String.lastChar(): Char = this.get(this.length - 1)
```

위와 같이 작성하면 `String`클래스 멤버인 것 처럼 사용할 수 있다.

> 확장 함수는 Java -> Kotlin변환때, Kotlin에는 없는 Java API를 사용해야 할 경우, 확장 함수를 이용해서 해당 API를 코틀린으로 사용할 수 있다.

- 확장할 클래스의 이름을 `수신 객체 타입 (receiver type)`이라고 한다.
    + 위 예시에서는 `String`이 수신 객체 타입이 된다.


- 호출되는 대상이 되는 값을 `수신 객체 (receiver type)`이라 부른다.
    + 위 예시에는 `this`가 수신 객체가 된다.

---

### 확장 함수 사용 예시

```kotlin
println("Kotlin".lastChar())
```

- 확장 함수는 클래스 파일이 있는 한 그 클래스에 원하는대로 확장할 수 있다.

> 클래스를 삭제할 때는 해당 클래스로 만들어진 확장 함수도 있는지 확인하는 편이 좋을것 같다.

- 일반 메소드에서 사용하는 것 처럼, 확장 함수에서도 `this`를 생략할 수 있다.

```kotlin
fun String.lastChar(): Char = get(length - 1)   // get과 length는 String에 있는 메소드, 필드이다.
```

- 확장 함수는 다른 확장 함수를 호출할 수 있다.

- 즉 확장 함수도, 클래스의 메소드처럼 똑같이 취급받는다.
  
- 확장 함수는 `캡슐화`가 유지된다. 그래서 `protected`, `private`멤버를 사용할 수 없다.