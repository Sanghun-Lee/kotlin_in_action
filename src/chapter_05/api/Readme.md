# 컬렉션 함수 라이브러리들

람다에서 지원하는 라이브러리들을 사용해봅시다.

### filter, map

filter : 람다값이 true인 원소만 모은다.

```kotlin
val list = listOf(1, 2, 3, 4)
println(list.filter { it % 2 == 0 }) // 짝수만 남는다.
// result : [2, 4]
```

map : 원소를 변환하는 역할을 한다.

```kotlin
val list = listOf(1, 2, 3, 4)
println(list.map { it * it })
// result : [1, 4, 9, 16]
```

람다계산을 수행할 때 불필요한 연산을 수행하지 않는것이 좋다.

예시로 나이가 가장 많은 사람의 리스트를 반환하는 예시가 있다고 했을때

```kotlin
people.filter { it.age == people.maxBy(Person::age).age }
```

위와같이 코드를 작성하게되면 filter한번에 maxBy를 위해 한번 더 poeple 길이만큼 돌게된다.

그렇게되면 시간복잡도가 `O(N^2)`이 된다.

```kotlin
val maxAge = people.maxBy(Person::age).age
people.filter { it.age == maxAge }
```

위와같이 작성하게되면 `O(2 * N)` 시간으로 빠르게 계산할 수 있다.

<br>

> **map**의 경우 key와 value를 처리하는 filterKey, mapKey, filterValue, mapValue로 따로있다.

### all, any, count, find

all : 모든 원소가 해당 조건을 만족하는지

```kotlin
println(people.all { it.age <= 30 })
```

any : 조건을 하나라도 만족하는 리스트 반환

count : 조건을 만족하는 원소의 개수를 반환 (Int)

> count를 잊고 filter와 size를 사용하는경우가 있는데 filter를 사용하게되면 불필요한 리스트가 만들어지기때문에
> filter + size 보다는 count를 사용하자.

find : 조건을 만족하는 원소 하나를 찾아주는 함수
- 조건을 만족하는 원소 하나를 찾아 반환 (2개 있어서 먼저 찾은 원소 반환)
- 없으면 null 반환

---

#### groupBy : 리스트에서 여러 그룹으로 이루어진 맵으로 변경

리스트의 모든 원소를 특정 조건에 따라 여러 그룹으로 나누고싶을때 사용한다.

```kotlin
println(people.groupBy { it.age }) // 나이를 기준으로 그룹핑해서 맵으로 리턴해준다.

// 첫 글자 기준으로 그룹핑
val list = listOf("a", "ab", "b")
println(list.groupBy(String::first)) // String 클래스의 first 함수를 멤버 참조로 전달
```

---

#### flatMap

> flatMap은 주어진 람다를 모든 객체에 적용하고 (map)
> 
> 결과로 나온 여러 리스트를 한 리스트에 모은다. (flatten)

Flatten 예시

```kotlin
/**
 * 아래 flatMap은 다음 두 단계를 거친다
 * it.toList -> "abc" => ["a", "b", "c"]
 * 그래서 strings의 모든리스트가 toList하게되면 listOf(listOf("a", "b", "c"), listOf("a", "b", "c")) 가 만들어지고
 * 이를 flatten 하게되면 list내의 두 리스트가 합쳐지면서
 * listOf("a", "b", "c", "d", "e", "f")로 결과가 나오게 된다.
 */
val strings = listOf("abc", "def")
println(strings.flatMap { it.toList() })
// result : [a, b, c, d, e, f]
```

flatMap의 결과로 중복을 없애기위해, 결과값에 `toSet()`으로 중복제거하는경우도 있다.

또한 map이 굳이 필요없다면 `flatten()` 함수를 사용할수도 있다.

```kotlin
 val list = listOf(
    listOf(1, 2, 3),
    listOf(3, 4, 5),
)

println(list.flatten().toSet()) // 중복제거 result : [1, 2, 3, 4, 5]
println(list.flatten()) // result : [1, 2, 3, 3, 4, 5]
```