# 3.5 문자열과 정규식

자바의 `split`함수는 매개 인자로 정규식을 받는다.

하지만 다음과 같은 경우는 예상치 못한 결과를 야기할 수 있다.

`"12.345-6.A".split(".")` -> java의 split은 빈 배열을 리턴 ("." 은 정규식에서 모든 문자를 뜻한다.)

그래서 코틀린의 split은 확장 함수로 정의되어있고,
- 문자열인 경우 `String`으로
- 정규식인 경우 `Regex`타입으로 값을 받는다.

"12.345-6.A"를 '.' 또는 '-'를 기준으로 자르고 싶은 경우

```kotlin
println("12.345-6.A".split("\\.|-".toRegex())) // 정규식으로 찾을 때
println("12.345-6.A".split(".", "-"))   // 문자열로 기준을 나눌 때
// 위 두 println은 동일한 결과를 출력한다.
```

---

## 3중 따옴표 문자열과 정규식

> 정규식 선언
> 
> 예시 파일 : [RegexExample](RegexExample.kt)

전체 디렉토리 경로 예시로는 `/Users/yole/kotlin-book/chapter.doc`과 같은 형태가 있다.

이를 정규식을 분리할 수 있지만, 나중에 파악하기도 어렵기 때문에 다음과 같이 3중 따옴표를 이용해서 구분하는 것이 좋다.

```kotlin
fun parsePath(path: String) {
    val regex = """(.+)/(.+)\.(.+)""".toRegex() // \.는 .을 표시하기위한 이스케이프 시퀀스
    val matchResult = regex.matchEntire(path)
    matchResult?.let { 
        val (directory, fileName, extension) = matchResult.destructured
        println("Dir : $directory, file name : $fileName, ext : $extension")
    }
}
```

---

## 로컬 함수와 확장

> 함수 내부에 함수 선언
> 
> 예시 파일 : [LocalFunction.kt](LocalFunction.kt) 

한 메서드 내에서 코드가 중복되는 경우, 해당 함수를 추출하는 것이 좋지만,

한 클래스 내에 함수가 너무 많이 생기면 전체적인 코드 가독성이 나빠질 수 있다.

그래서 코틀린에선 함수 내부에 함수를 정의함으로써 함수를 중첩(로컬 함수)해서 사용할 수 있다.

- 로컬 함수 예시

```kotlin
 class User(val id: Int, val name: String, val address: String)

fun saveUser(user: User) {
    if(user.name.isEmpty()) {
        throw IllegalArgumentException(/* ... */) // 내부 코드가 중복된다.
    }
    if(user.address.isEmpty()) {
        throw IllegalArgumentException(/* ... */)
    }
}
```

위 코드에서 if문 내의 코드가 중복되기 때문에, 이를 하나의 로컬 함수로 빼면 다음과 같은 형태가 된다.

```kotlin
fun saveUser(user: User) {
    fun validate(value: String, fieldName: String) {
        if(value.isEmpty()) {
            // saveUser 함수를 호출할 때 가지는 user객체를 자유롭게 사용할 수 있다.
            throw IllegalArgumentException("user id : ${user.id}, $fieldName field is not empty")
        }
    }
    
    validate(user.name, "Name")
    validate(user.address, "Address")
}
```

또한 이는 확장 함수 안에도 로컬 함수를 만들 수 있다.

하지만 이러한 로컬 함수는 적당한 깊이(1뎁스)로만 정의하는 것이 좋다.