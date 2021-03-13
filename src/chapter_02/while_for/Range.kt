package chapter_02.while_for

class Range

// i % 3 -> fizz, i % 5 -> buzz, i % 15 -> fizzbuzz
fun fizzBuzz(i: Int) = when {
    i % 15 == 0 -> "FizzBuzz "
    i % 5 == 0 -> "Buzz "
    i % 3 == 0 -> "Fizz "
    else -> "$i "
}

fun main() {
    // for loop with range
    for (i in 1..100) {
        print(fizzBuzz(i))
    }
    println()

    // 값이 감소하도록 range설정
    for (i in 100 downTo 1 step 2) {
        print(fizzBuzz(i))
    }

    println()
    for (i in 1..11 step 2) {
        print(i)
    }
}