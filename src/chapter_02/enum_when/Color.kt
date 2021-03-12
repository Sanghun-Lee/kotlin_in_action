package chapter_02.enum_when

enum class Color(private val r: Int, private val g: Int, private val b: Int) {
    RED(255, 0, 0),
    ORANGE(255, 165, 0),
    YELLOW(255, 255, 0),
    GREEN(0, 255, 0),
    BLUE(0, 0, 255),
    INDIGO(75, 0, 130),
    VIOLET(238, 130, 238);  // 메소드를 정의할 시 ';'를 붙여야 한다.

    fun rgb() = (r * 256 + g) * 256 + b
}

fun main(args: Array<String>) {
    println(getRgb(Color.RED))
}

fun getRgb(color: Color) =
    when (color) {
        Color.RED -> Color.RED.rgb()
        Color.ORANGE -> Color.ORANGE.rgb()
        Color.YELLOW -> Color.YELLOW.rgb()
        Color.GREEN -> Color.GREEN.rgb()
        Color.BLUE -> Color.BLUE.rgb()
        Color.INDIGO -> Color.INDIGO.rgb()
        Color.VIOLET -> Color.VIOLET.rgb()
    }