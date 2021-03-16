# 선택 표현과 처리 : Enum과 When

## Enum

[kotlin enum class documents](https://kotlinlang.org/docs/enum-classes.html)

- enum은 자바와 같이 값을 열거하는데도 사용할 수 있다.

- 간단한 enum클래스 정의

```kotlin
enum class Direction {
    NORTH, SOUTH, WEST, EAST
}

fun getDirection(d: Direction) =
    when (d) {
        Direction.EAST -> "EAST"
        Direction.WEST -> "WEST"
        Direction.NORTH -> "NORTH"
        Direction.SOUTH -> "SOUTH"
        // enum type은 when에서 else를 사용하지 않아도 된다.
        // 하지만 enum의 모든 타입에 대한 분기를 만들어야 한다.
    }
```

- 각각의 enum상수 (NORTH, SOUTH...)는 **object**이다.
- 그래서 아래 예시와 같이 enum class의 인스턴스(객체)를 가질 수 있다.

```kotlin
enum class Color(val r: Int, val g: Int, val b: Int) {
    RED(255, 0, 0),
    ORANGE(255, 165, 0),
    YELLOW(255, 255, 0),
    GREEN(0, 255, 0),
    BLUE(0, 0, 255),
    INDIGO(75, 0, 130),
    VIOLET(238, 130, 238);  // 메소드를 정의할 시 ';'를 붙여야 한다.

    fun rgb() = (r * 256 + g) * 256 + b
}
```

### Anonymous classes

- 다음과 같이 작성할 수 있는데, 무슨 의미인지는 좀 더 봐야겠다.

```kotlin
enum class ProtocolState {
    WAITING {
        override fun signal() = TALKING
    },

    TALKING {
        override fun signal() = WAITING
    };

    abstract fun signal(): ProtocolState
}
```

## When

- When의 경우 상수만 올 수 있는 Java의 switch문과 다르게, 모든 객체가 올 수 있다.

```kotlin
fun mix(c1: Color, c2: Color) {
    when (setOf(c1, c2)) {
        setOf(RED, YELLOW) -> ORANGE
        setOf(YELLOW, BLUE) -> GREEN
        setOf(BLUE, VIOLET) -> INDIGO
        else -> throw Excpetion("Dirty color")
    }
}

print(mix(BLUE, YELLOW))
```

### when을 인자 없이 사용

- when 아래의 각 분기에 조건이 있으면 when을 인자없이 사용할 수 있다.

```kotlin
fun mixOptimized(c1: Color, c2: Color) =
    when {
        (c1 == RED && c2 == YELLOW) || (c1 == YELLOW && c2 == RED) -> ORANGE
        // ....
        else -> throw Exception("Dirty Color")
    }
```


## 스마트 캐스팅

### is == instanceof

- java의 instanceof를 코틀린으로 하면 is이다.

- java의 경우 instanceof로 해당타입을 확인하고, 강제캐스팅을 통해 타입변환을 해야한다.

- 그런데 kotlin은 is로 타입을 한 번 확인하면 강제 캐스팅코드 없이도 해당 타입 변수로 생각해준다.

```java
// java
// Number -> Integer
Number x = new Integer(10);
if(x interface Integer) {
    Integer y = (Integer)x;
    y = // do someting
}
```

```kotlin
// kotlin
val x : Number = 10
if(x is Int) {
    x = // do something
    // x는 Int타입으로 생각
}
```