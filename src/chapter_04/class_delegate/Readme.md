# 클래스 위임, by 키워드

일반적으로 대규모 객체지향 시스템에서는 구현, 상속에 의해 시스템이 취약해지기 쉽다. 대표적인 예시가 취약 기반 클래스이다.

하지만 종종 상속이 되지 않지만, 해당 클래서에 새로운 동작을 추가할 때가 있는데, 이 때 **데코레이터 패턴**을 이용한다.

### 데코레이터 패턴

해당 패턴은 기존 클래스 대신 사용할 수 있는 클래스를 만들되 기존 클래스와 같은 인터페이스를 데코레이터가 제공하게 만들고, 기존 클래스를 데코레이터의 내부 필드로 유지하는 것이다.

예시
```kotlin
// ArrayList를 재정의해서 데이터 존재여부를 확인할 때 마다 특정 동작을 수행하고 싶다.
// ArrayList를 상속할 순 없기 때문에, 상위 Interface인 Collection을 구현한다.
class DelegatingCollection<T>: Collection<T> {
    // ArrayList의 기존 클래스 - innerList
    private val innerList = arrayListOf<T>() // 그 외 로직은 해당 메서드가 수행한다.
    
    override fun containsAll(elements: Collection<T>): Boolean {
        /* do something */
        return innerList.containsAll(elements)
    }
    
    override val size: Int = innerList.size
    override fun isEmpty(): Boolean = innerList.isEmpty()
    override fun contains(element: T): Boolean = innerList.contains(element)
    override fun iterator(): Iterator<T> = innerList.iterator()
}
```

위 코드와 같이 ArrayList의 특정 동작을 재정의 하고 싶을 때 Collection을 구현해서 데코레이터 패턴을 이용하면 된다.

하지만 위 코드에서 보이는 것 처럼 불필요한 함수들을 재정의 해야하고, 그대로 innerList의 함수를 호출하는 경우가 생긴다.

그래서 코틀린은 위임 키워드인 `by`로 아래 보일러 플레이트 코드를 제거할 수 있다.

```kotlin
class DelegatingCollection<T>(
    private val innerList: Collection<T> = ArrayList<T>()
) : Collection<T> by innerList {
    override fun containsAll(elements: Collection<T>): Boolean {
        /* do something */
        return innerList.containsAll(elements)
    }
}
```

책의 예시대로 HashSet으로 작성하면 다음과 같다.

```kotlin
class CountingSet<T>(
    val innerSet: MutableCollection<T> = HashSet<T>()
) : MutableCollection<T> by innerSet {
    
    var objectsAdded: Int = 0
    
    override fun add(element: T): Boolean {
        objectsAdded++
        return innerSet.add(element)
    } 
    
    override fun addAll(elements: Collection<T>): Boolean {
        objectsAdded += elements.size
        return innerSet.addAll(elements)
    }
}
```

다음과 같이 코드를 작성하게 되면, addAll 내부적으로 add를 여러번 호출하거나, 그렇지 않더라도 호출되는 add는 CountingSet의 add가 아닌, 기존 HashSet Api의 add이기 때문에, 간섭의 문제도 발생하지 않는다.