package chapter_05.api

fun main() {

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

    val list = listOf(
        listOf(1, 2, 3),
        listOf(3, 4, 5),
    )
    println(list.flatten().toSet()) // 중복제거 result : [1, 2, 3, 4, 5]
    println(list.flatten()) // result : [1, 2, 3, 3, 4, 5]
}