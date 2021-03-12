# Property

## Class

#### 클래스의 선언

```kotlin
class Person(val name: String)
```

- 클래스의 기본 접근제한자는 **public**이다.

- 코드 없이 데이터만 저장하는 클래스를 **값 객체 (value object)** 라고 부른다.

## Property

- 자바는 기본적으로 데이터 캡슐화한다.
- 클래스 필드는 private타입이고, setter, getter를 통해서 필드에 접근한다.

- Kotlin에서는 java의 **필드, setter, getter**를 하나로 묶어서 **Property**라고 부른다.

```kotlin
class Person(
    val name: String,       // field, getter만 생성 (val)
    var isMarried: Boolean  // field, getter, setter생성 (var)
)
```

- property접근은 일반 필드 접근처럼 코드를 작성하면 된다.
- 동작은 java의 setter, getter로 동작한다.

```kotlin
val person = Person("bob", true)
println(person.name)        // getter처럼 동작
person.isMarried = false    // setter처럼 동작
```

## Custom Getter

- **get** 메소드를 활용해서 데이터 값을 그때그때 판단해서 가져올 수 있다.

```kotlin
// 정사각형인지 확인하는 custom getter
class Rectangle(val height: Int, val width: Int) {
    val isSquare: Boolean
        get() {
            return height == width
        }
    // get() = height == width 가능.
}
```