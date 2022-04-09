# 4.2.1 클래스 초기화

> 코틀린에서는 `주 생성자`와 `부 생성자`가 구분되고,
> 
> `초기화 블록`을 통해 초기화 로직을 추가할 수 있다.

## 주 생성자와 초기화 블록

- 간단한 클래스의 선언

```kotlin
class User(val nickname: String)
```

위 코드와 같이 클래스 이름 뒤에 `()`괄호로 둘러쌓인 코드가 `주 생성자`이다.

- 주 생성자는 **생성자 파라미터를 지정**하고, 그 생성자 파라미터에 의해 **초기화되는 프로퍼티를 정의**하는 목적이 있다.

주 생성자는 아래와 같은 코드 동작을 한다.

```kotlin
// class User(val nickname: String) 과 같은 동작의 코드

class User constructor(_nickname: String) {
    val nickname: String

    init {
        nickname = _nickname
        this.nickname = _nickname
    }
}
```

- `constructor` : 주 생성자나 부 생성자 정의를 시작할 때 사용

- `init`(초기화 블록) : 클래스 객체가 만들어질 때 실행될 초기화 코드가 들어간다. 여러개의 초기화 블록이 올 수 있다.

초기화 블록 없이 다음과 같이 작성할 수도 있다.

```kotlin
class User(_nickname: String) { // 어노테이션이나, 가시성 변경자가 없다면 constructor 생략가능
    val nickname = _nickname // 클래스 프로퍼티를 주 생성자의 파라미터로 초기화 한다.
}
```

그리고 해당 코드는 처음에 작성했던 것 처럼 아래와 동일하다.

```kotlin
class User(val nickname: String)
```

> 생성자에서 `val`을 작성하지 않으면, 해당 프로퍼티(_nickname)는 클래스 프로퍼티를 초기화하는 식이나, init 블록에서만 사용할 수 있다.

---

### 생성자 파라미터 디폴트 값

생성자 파라미터에도 디폴드 값을 작성할 수 있다.

```kotlin
class User(val nickname: String, val isSubscribed: Boolean = true)  // 기본값 true
```

아래와 같이 User클래스를 생성할 수 있다.

```kotlin
val Lee = User("Kim")
val gye = User("Kim", false)
val han = User("Kim", isSubscribed = false)
```

- 모든 파라미터에 디폴트 값을 지정하면, 컴파일러가 자동으로 파라미터 없는 생성자를 만들어준다. 이는 DI에서 유용하게 쓰인다.
---

### 자식 클래스에서 부모 클래스 호출

```kotlin
open class User(val nickname: String)

class TwitterUser(nickname: String) : User(nickname) {}
```

부모클래스 User의 nickname 생성자 호출

- 부모 클래스에서 별도로 생성자를 호출하지 않았을 경우, 자동으로 기본 생성자를 만들어준다.

`class RadioButton: Button() // Button의 기본 생성자 호출`

자식 클래스가 부모 클래스를 상속받을 때는, 부모 클래스의 주 생성자를 호출해야해서 부모 클래스 이름 뒤에 괄호가 들어가지만, 인터페이스는 생성자가 없기 때문에, 뒤에 괄호가 들어가지 않는다.

### private 생성자

어떤 클래스가 외부에서 객체 생성을 하지 못하도록 하고싶다면 생성자를 private으로 만들어 주면 된다.

```kotlin
class Secretive private constructor() {}
// 해당 클래스는 주 생성자 밖에 없고, 주 생성자로 private이기 때문에, 외부에서는 해당 클래스 객체를 생성할 수 없다.
```

> 비공개 생성자의 대안으로는 최상위 함수(3.2.3절), 싱글턴(4.4.1절)에 있다.


