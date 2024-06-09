package chapter_05.sequence.operation

fun main() {
    listOf(1, 2, 3, 4).asSequence()
        .map { print("sequence map $it \t"); it * it }
        .filter { print("sequence filter $it \t"); it % 2 == 0}
    // output nothing


    listOf(1, 2, 3, 4)
        .map { print("list map $it \t"); it * it }
        .filter { print("list filter $it \t"); it % 2 == 0}
    // output list map 1 list map 2... list filter 16
    println()

    listOf(1, 2, 3, 4).asSequence()
        .map { print("sequence map $it \t"); it * it }
        .filter { print("sequence filter $it \t"); it % 2 == 0}
        .toList() // 최종 연산
    // map 1, filter 1, map 2, filter 4... map4, filter 16
    println()

    listOf(1, 2, 3, 4)
        .map { print("list map $it \t"); it * it }
        .find { it > 3 }
    // output list map 1 list map 2 list map 3 list map 4
    println()

    listOf(1, 2, 3, 4).asSequence()
        .map { print("list map $it \t"); it * it }
        .find { it > 3 }
    // output list map 1 list map 2
    println()
}
