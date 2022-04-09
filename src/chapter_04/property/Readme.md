## 인터페이스에 선언된 프로퍼티 구현

인터페이스에 추상 프로퍼티를 선언할 수 있다.

인터페이스를 구현한 하위 클래스들은 인터페이스에서 정의한 추상 프로퍼티를 구현해야한다.

```kotlin
interface User {
    val nickname: String
}

// 구현하는 여러 클래스
class PrivateUser(override val nickname: String) : User

class SubscribingUser(private val email: String) : User {
    override val nickname: String
        get() = email.substringBefore("@") // 커스텀 게터, nickname을 요청할 때 마다 계산한다.
}

class FacebookUser(accountId: String) : User {
    override val nickname: String = getFacebookName(accountId)

    private fun getFacebookName(accountId: String): String {
        // access facebook with network
        // get name
        return ""
    }
}
```

위 여러 구현 클래스처럼 User 인터페이스를 구현하는 여러 클래스들은

인터페이스에 정의한 `추상 프로퍼티인 nickname`을 제공하는 방법을 마련해야 한다.

- PrivateUser 클래스는 주 생성자에서 nickname을 받도록 하였다.
- SubscribingUser는 커스텀 게터로 nickname을 요청할 때 마다 nickname을 계산하도록 하였다.
- FacebookUser는 객체 생성시에 초기화 식으로 nickname 프로퍼티를 설정한다.

---

### 인터페이스 프로퍼티의 게터 세터

인터페이스도 getter와 setter를 정의할 수 있다.

하지만 프로퍼티에 값을 바로 넣을순 없다. (인터페이스는 상태를 저장할 수 없어서)

커스텀 getter또는 setter를 넣어서 결과를 매번 계산해서 돌려주어야 한다.

```kotlin
interface User {
    val email: String

    // 커스텀 getter를 정의하면, 해당 프로퍼티는 하위 클래스에서 오버라이드 하지않아도 된다.
    val nickName: String
        get() = email.substringBefore("@")
}
```

---

## 게터와 세터에서 뒷받침하는 필드에 접근

> 뒷받침하는 필드 : 초기값, 또는 기존에 변수에 저장된 값이라고 생각하면 쉽다.
> 
> var address: String = "unspecified" // unspecified값이 뒷받침하는 필드이다.

setter를 이용해서 값이 저장될 때 마다 정의한 로직을 실행하도록 코드를 작성해 보자.

그리고 그 때 해당 프로퍼티가 이전에 가지고 있는 값에 접근할 수 있는 `field` 식별자를 알아보자.


```kotlin
class User(val name: String) {
    var address: String = "unspecified"
        // field식별자로 address를 뒷 받침하는 "unspecified"를 읽을 수 있다.
        set(value: String) {
            println("""
                Address was changed for $name :
                "$field" -> "$value".
            """.trimIndent())
            field = value // address값 변경
        }
}
```

`field`식별자로 setter에서 값이 업데이트 되기 전의 프로퍼티 값에 접근할 수 있다.

프로퍼티의 값을 변경할 때는 프로퍼티에 값을 대입하면 된다.

값을 대입하면 내부적으로 setter를 호출한다.

- getter에서 field 식별자는 기존의 값을 읽을수만 있다.
- setter에선 field의 값을 읽을 수 있고, 대입도 할 수 있다.

## 접근자(get, set)의 가시성 변경

접근자(get, set)의 가시성은 프로퍼티의 가시성과 동일하다.

하지만 원한다면 접근자의 가시성을 변경할 수 있다.

```kotlin
class LengthCounter {
    var counter: Int = 0
        private set // 클래스 내에서만 해당 프로퍼티의 값을 변경할 수 있다.
}
```