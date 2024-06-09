package chapter_05.sequence

fun main() {
    val list = listOf(1, 2, 3, 4)

    // 아래와 같이 List collection을 map, filter를 수행하게되면
    // 중간 계산 결과 리스트들이 매번 생성된다.
    list.map { it.toInt() } // toInt를 수행한 길이 4 리스트 생성
        .filter { it == 1 } // filter 결과 길이 1 리스트 생성

    // 위와같이 연산하게되면 list의 길이가 길 경우 중간중간 긴 리스트들이 만들어지기때문에 효율이 떨어진다.
    // 이를 효율적으로 수행하기 위해서는 sequence로 작업해야한다.

    list.asSequence()
        .map { it.toInt() }
        .filter { it == 1 }
        .toList()
    // 최종 결과를 만족하는 리스트가 순차적으로 생성되어 중간중간 리스트가 생성되지 않는다.

    class A {
        private var i = 0
        operator fun hasNext() = i < 10
        operator fun next() = i++
    }

    operator fun A.iterator() = this

    fun foo() {
        for(i in A()) println(i) //
    }
    foo()

}