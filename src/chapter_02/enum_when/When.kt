package chapter_02.enum_when

import Color

class When {
    companion object {
        fun mix(c1: Color, c2: Color) {
            when (setOf<Color>(c1, c2)) {
                setOf(Color.RED, Color.YELLOW) -> Color.ORANGE
                setOf(Color.YELLOW, Color.BLUE) -> Color.GREEN
                setOf(Color.BLUE, Color.VIOLET) -> Color.INDIGO
                else -> throw Exception("Dirty color")
            }
        }
    }
}

class Person(private val name: String, private val age: Int) {
    override fun equals(other: Any?): Boolean {
        val another = other as Person
        return (this.name == another.name)
    }

    // when에서 비교할 때 equals메소드로 비교한다.
    companion object {
        fun compare(p1: Person) =
            when (p1) {
                Person("hun", 22) -> 22
                Person("hun", 30) -> 30
                Person("zzz", 25) -> 25
                Person("hhh", 25) -> 255
                else -> 0
            }
    }
}

fun main() {
    println(Person.compare(Person("hun", 100)))
    println(Person.compare(Person("zzz", 30)))
}