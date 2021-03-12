fun main() {
    val judge = if(100 / 10 == 0) { 1 } else { 0 }
    print(judge)
}

enum class Color(val r : Int, val g : Int, val b : Int) {
    RED(255, 0, 0), ORANGE(255, 165, 0),
    YELLOW(255, 255, 0), GREEN(0, 255, 0), BLUE(0, 0, 255),
    INDIGO(75, 0, 130), VIOLET(238, 130, 238);  // 마지막엔 무조건 세미콜론

    fun rgb() = (r * 256 + g) * 256 + b
    // 위 함수는 아래와 같다.
//    fun rgb() : Int {
//        return (r * 256 + g) * 256 + b
//    }
}