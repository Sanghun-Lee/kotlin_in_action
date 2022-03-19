package chapter_02.while_for

import java.util.*

class MapIteration

fun mapIteration() {
    val binaryRepo = TreeMap<Char, String>()

    for (c in 'A'..'F') {
        val binary = Integer.toBinaryString(c.hashCode())
        binaryRepo[c] = binary
    }
    for ((letter, binary) in binaryRepo) {
        println("$letter : $binary")
    }
}

fun main() {
    mapIteration()

    val intRange = 1..10 step 3  // step은 infix 메서드이다.
    val downRange = 100 downTo 20 // downTo또한 infix 메서드이다.
    val isContain = 30 in 0 until 0

    println("intRagne : $intRange")
    println("downRange : $downRange")
    println("isContain : $isContain")
}