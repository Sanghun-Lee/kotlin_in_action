# 2.5 예외 처리

> [Kotlin Exception Docs](https://kotlinlang.org/docs/exceptions.html)

예외처리로 runCatching, 자원관리로 use등이 있는데 이는 나중에 다룬다.

## 예외 발생 - throw

- 예외를 발생시키기 위해선 자바와 동일하게 `throw`를 사용하면 된다.

```kotlin
val percentage =
        if (number in 1..100)
            number
        else
            throw IllegalArgumentException("A percentage value must be .....")
```

- `throw`또한 식이기 때문에, 다른 식에 포함될 수 있다.

## try, catch, finally

- kotlin에서 기본적인 try - catch문은 다음과 같다.

```kotlin
fun readNumber(reader: BufferedReader): Int? {  // 해당 메소드가 예외를 발생시킨다는 throws문이 없다
    return try {
        val line = reader.readLine()
        Integer.parseInt(line)
    } catch (e: NumberFormatException) {
        null
    } finally {
        reader.close()
    }
}
```


> 자바에선 해당 메서드가 예외를 처리하지 않고, 예외를 외부로 던질 경우, 메서드에 `throws`로 던질 예외를 명시해야 한다.
>
> 하지만, 코틀린은 예외를 던지지 않아도 내부에서 예외를 발생시키면 (throw IOException) 메서드명에 throws를 적지 않아도, 예외가 밖으로 전달된다.


- BufferedReader의 close()는 IOException을 발생시킬 수 있는데, Java에서는 해당 예외를 무조건 처리해야만 한다. (Checked Exception이라서)
  하지만 stream을 닫다가 실패해도, 클라이언트 프로그램에서 할 수 있는 일은 없다. 그래서 코틀린은 Checked Exception이 없다.
- 위 코드를 자바로 작성하면 아래와 같다.

```java
public class ExceptionHandle {
    public int readNumber(BufferedReader reader) throws NumberFormatException {
        try {
            String line = reader.readLine();
            return Integer.parseInt(line);
        } catch (NumberFormatException e) {
            return 0;
        } finally {
            reader.close();
        }
    }
}
```

### 체크 예외 (checked exception), 언체크 예외 (unchecked exception)

- [예제 코드 - ExceptionExample.java](ExceptionExample.java)
- [참고 블로그 - 자바 예외 구분](https://cheese10yun.github.io/checked-exception/)

<a href="https://madplay.github.io/post/java-checked-unchecked-exceptions"><img src="https://madplay.github.io/img/post/2019-03-02-java-checked-unchecked-exceptions-1.png" alt="Exception Graph Image" width="700" /></a>

#### Checked Exception

- 체크(try-catch)해야만 하는 예외들
- Runtime Exception을 상속받지 **않는다**. (바로 Exception을 상속받는다.)
- 대표적인 Exception : IOException, ClassNotFoundException

#### UnChecked Exception

- [Oracle RuntimeException Docs](https://docs.oracle.com/javase/7/docs/api/java/lang/RuntimeException.html)
- 체크(예외 처리)하지 않아도 되는 예외들
- **Runtime Exception**을 상속받는다.
- 대표적인 Exception : NullPointerException, ArrayIndexOutOfBoundsException, NumberFormatException

---

### try - catch - resource

- kotlin에선 제공하지 않는다.
- 라이브러리 함수로 같은 기능을 할 순 있다. (8.2.5절)

## try를 식으로 사용

- try문도 if와 동일하게 식으로 사용될 수 있다.
- try가 식으로 사용될 경우 마지막 줄이 리턴값이 된다.

```kotlin
fun readNumber(reader: BufferedReader) {
    val number = try {
        Integer.parseInt(reader.readLine())
    } catch (e: NumberFormatException) {
        null
    }
    println(number)
}
```

- 입력

> val reader = BufferedReader(StringReader("not a number")) <br>
> readNumber(reader)

- 출력

> null