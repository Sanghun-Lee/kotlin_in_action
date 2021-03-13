# while문, for문

## while문

- while문은 java와 다르지 않다.

```kotlin
while (condition) {
    /* ... */
}

do {
    /* ... */
} while (condition)
```

## 범위와 수열

#### 범위 지정 방법 (python의 range)

- ".." 연산자로 범위를 지정할 수 있다.
- a..b : [a, b]구간의 수열을 만든다. (ex 1..5 : [1, 2, 3, 4, 5])
- 값을 1씩 증가하는 것이 아닌, N만큼 증가하고 싶다면, `step`을 사용한다.
- a..b step N (ex. 1..11 step 2 : [1, 3, 5, 7, 9, 11])

#### 역방향 설정

- `downTo`를 사용하면 된다.
- b downTo a : b부터 시작해서 a까지 값을 하나씩 줄인다.
- 5 downTo 1 : [5, 4, 3, 2, 1]

#### 값을 N씩 내리고 싶을 때

- `step`을 사용한다.
- b downTo a step N : b부터 시작해서 a까지 값을 줄이는데, 한번에 N만큼 줄인다.
- 5 downTo 1 step 2 : [5, 3, 1]

#### 반개구간 [a, b) 형식으로 만들고 싶을 때

- `until` 함수를 사용하면 된다.
- x in 0 until size -> x in 0..size-1 과 동일하다.

---

## Map Iteration

- 아래와 같은 방법으로 key, value쌍이 있는 경우, for문에서 자연스럽게 받을 수 있다.

```kotlin
val binaryRepo = TreeMap<Char, String>()

// ...
for ((letter, binary) in binaryRepo) {
    println("$letter = $binary")
}
```

### 원소 존재 여부 검사 - in

- in으로 해당 범위의 원소가 있는지 검사할 수 있다.
- **!in**으로 예상과 같이 해당 원소가 없는지를 확인할 수 있다.
- `c in 'a'..'z'`
- `c !in '0'..'9'`

> c in 'a'..'z' => 'a' <= c && c <= 'z'로 변환된다.
