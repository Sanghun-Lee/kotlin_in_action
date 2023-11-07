## 4.3 Data Class

### 모든 클래스가 재정의 할 수 있는 메서드

- toString : 자바의 toString과 동일
- equals : `==` 연산자로 객체 비교 시 호출되는 함수, 두 객체가 같은지 여부를 판별한다.
    - cf. `===` 연산자도 존재하는데, 이는 참조 비교 (Java에서 `==` 비교와 동일)이다.
- hashCode : 해당 객체로 생성할 해시코드값을 재정의한다. `equals`를 재정의했다면 `hashCode`함수도 재정의 해야한다.

#### equals를 재정의하였지만, hashCode를 재정의 하지 않은 경우

제목과 같이 equals를 재정의 하였지만, hashCode를 재정의 하지 않은 경우 아래와 같은 문제에 직면할 수 있다.

```kotlin
class Client(val name: String, val id: Int) {
    override fun equals(other: Any?): Boolean {
        return (other !is Client) and (other.name == this.name) and (other.id == this.id)
    }
}

val processed = hashSetOf(Client("ABCE", 1122))
println(processed.contains(Client("ABCE", 1122))) // false
```

위와 같은 결과가 나오는 이유는 `hashSetOf`의 특징에 있다.

hashSetOf는 객체가 존재하는지 여부를 판단할 때 **hashCode**를 먼저 확인하고, 같으면 equals로 확인한다.

하지만 hashCode를 재정의하지 않아 두 객체는 다른 객체로 판단하고 false를 리턴받게 된다.

<br>

> JVM 언어에서는 equals결과로 true가 나온다면, hashCode 결과도 true가 나와야만 한다는 제약이 있기 때문에, equals를 재정의했다면 hashCode또한 재정의하자.

---

## Data Class

특정 클래스가 데이터를 저장하는 역할만 수행한다면, toString, equals, hashCode를 모두 재정의 해 주어야 한다.

코틀린에선 `data`키워드로 클래스를 생성하면 자동으로 위 3가지 함수를 모두 자동으로 재정의한다.

다만, equals와 hashCode는 모든 프로퍼티의 값의 동등성 및 계산을 수행한다.

그래서 일부 프로퍼티만으로 비교하기 위해선 equals를 재정의 해 주어야 하긴 한다.

### copy 메서드

데이터 클래스는 모든 프로퍼티를 val로 만들어서 **불변 클래스**로 만들라고 권장한다.

왜냐하면 일부 필드를 변경할 수 있다면, 해당 데이터 클래스를 key로 사용하는 HashMap 등에서 key의 변경으로 다른 객체를 참조할 수도 있다.

또한 스레드 환경에서 여러 스레드가 필드의 값을 변경하게 되면 동기화 할 필요가 존재하는데, val로 만들게되면 이런 필요가 사라지게 된다.

그리고 코드를 추론하는것도 쉽게 할 수 있다.

이 때 객체의 값을 변경해야 할 때에는 새롭게 만드는것이 좋은데, 그 때 copy 메서드를 사용할 수 있다.