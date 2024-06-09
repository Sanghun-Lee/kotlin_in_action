## 5.3.1 시퀀스 연산 실행

시퀀스의 연산은 중간 연산과 최종 연산으로 나뉜다.

중간 연산은 항상 지연 계산되며, 최종 연산이 있을 때 계산이 수행되기 때문에

중간 연산만 있는 sequence는 계산되지 않는다.

[Operation.kt](Operation.kt) 해당 예시에 있는 코드를 보면

```kotlin
list.asSeqeunce.map.filter
list.map.filter
list.asSequence.map.filter.toList
```

3가지 케이스가 있는데 

첫 번째로는 아무런 출력이 이루어지지 않고

두 번째로는 map연산이 모두 이루어진 후 filter 연산이 이루어진다.

세 번째로는 map연산과 filter연산, map 연산과 filter 연산이 하나씩 하나씩 차례대로 이루어지고있는것을 확인할 수 있다.

---

sequence를 사용하게 되면 모든 연산은 각 원소에 대해 순차적으로 처리된다는 것을 확인할 수 있다.

그래서 차례대로 계산 후 결과가 얻어지만 그 이후에 대해서는 계산이 이루어지지 않는다.

[Operation.kt](Operation.kt) 해당 예시의 4, 5번째 `find`연산 참고

### Java Stream vs Kotlin Sqeuence

자바 8에 추가된 Stream의 경우에도 Sequence와 동일하게 지연 계산된다.

하지만 둘의 차이점이 크게 3가지 존재한다. (Effective kotlin item 49)

1. kotlin의 sequence가 더 많은 처리함수를 가지고있고, 더 단순하다.

최종연산

stream : collect(Collectors.toList()) / sequence : toList()

2. Java Stream의 경우 병렬 모드로(`parallelStream`) 실행될 수 있다.

이는 멀티코어에서 중요한 성능을 가질 수 있지만, 사용에 주의해야 한다.

3. Stream의 경우 JVM 위에서만 돌지만, kotlin의 sequence는 Kotlin/JVM, Kotlin/JS, Kotlin/Native 위에서 동작합니다.

책에서 결론은 병렬모드로 완벽한 이점을 가질 수 있는 부분에서는 Stream을 사용하고, 그 외 일반적으론 Sequence가 더 좋다고 생각하고있습니다.

> 2. 사용에 주의해야 한다.
> 
> 어떤 주의를 해야할지 찾아보았는데, 아래 글 참고
> 
> https://dzone.com/articles/be-aware-of-forkjoinpoolcommonpool
> 
> 요약 : parallelStream은 ForkJoinPool위에서 돌고, 이는 commonPool 에서 동작합니다.
> 
> commonPool의 개수는 Cpu core 수 - 1개로 정해져 있습니다.
> 
> 그래서 1초 block되는 100개의 작업을 commonPool에서 동작하면, 4코어 cpu의 경우 34초 걸립니다. (100초 / 3)
> 
> 하지만, commonPool의 개수가 1개인 경우, 작업이 submit되면 매번 새로운 thread를 생성해서, 동일한 작업을 수행할 시 1초 소요됩니다.
> 
> 그래서 cpu core 수에 따라 다르게 동작할 수 있기 때문에 주의해서 사용해야 합니다.