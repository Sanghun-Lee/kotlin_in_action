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
- `enum class ClassName`으로 클래스를 정의하고, `{}`내에서 바로 객체를 생성한다고 생각하면 될 것 같다.

```kotlin
enum class Color(val r: Int, val g: Int, val b: Int) {
    RED(255, 0, 0),
    ORANGE(255, 165, 0),
    YELLOW(255, 255, 0),
    GREEN(0, 255, 0),
    BLUE(0, 0, 255),
    INDIGO(75, 0, 130),
    VIOLET(238, 130, 238);  // 메소드를 정의할 시 ';'를 붙여야 한다.

    fun rgb(): Int = (r * 256 + g) * 256 + b
}
```

`enum class Color(변수 3개) <- Color enum 클래스 선언`

`RED, ORANGE, YELLOW....`와 같은 Color 객체 생성

Color enum class와 rgb() 메서드 사용

```kotlin
fun getRgb(color: Color): Int = color.rgb()

// 외부에서 특정 필드 가져오기 (private이 아니어야 한다.)
val indigoBlue = Color.INDIGO.b
```

### Anonymous classes

- 아래와 같이 enum class는 `추상메소드`를 가질 수 있는데, enum 객체를 생성할 때 바로 추상메소드를 재정의 할 수 있다?

위에서 enum의 각각 아이템들은 object라고 하였다. 각 아이템들은 상위 enum class(Color, ProtocolState)를 상속받았다고 생각하면 되겠다.

그런상황에서 상위 enum class(ProtocolState)가 추상메서드를 가지게 되면, 하위 enum class들(WAITING, TALKING...)은 해당 추상메서드를 재정의해야만 한다. 

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
class Casting {
    public static void main(String[] args) {
        Number x = new Integer(10);
        if (x instanceof Integer) {
            Integer y = (Integer) x;
            y = 23; // do someting
        }
    }
}
```

```kotlin
// kotlin
val x : Number = 10
if(x is Int) {
    x = 2// do something
    // x는 Int타입으로 생각
}
```