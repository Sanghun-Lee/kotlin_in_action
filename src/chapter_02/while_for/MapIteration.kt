package chapter_02.while_for

import java.util.*

class MapIteration

fun mapIteration() {
    val binaryRepo = TreeMap<Char, String>()

    for (c in 'A'..'F') {
        val binary = Integer.toBinaryString(c.toInt())
        binaryRepo[c] = binary
    }
    for ((letter, binary) in binaryRepo) {
        println("$letter : $binary")
    }
}

fun main() {
    mapIteration()
}