package chapter_04.`object`

data class File(val path: String)

/**
 * object로 선언하면 자동으로 singleTone으로 객체를 만들어 준다.
 * object 클래스는 객체 선언문이 있는 위치에 생성자 호출없이 즉시 객체가 만들어 지기 때문에, 생성자를 선언할 수 없다.
 * object 클래스 또한 클래스나 인터페이스를 상속받을 수 있다. 다만 내부에서 상태(프로퍼티?)를 가지고 있지 않을 때 유용하다.
 *
 * 두 File 객체를 보고 대소문자 관계없이 비교하는 Compatator
 */
object CaseInsensitiveFileComparator: Comparator<File> {
    override fun compare(file1: File, file2: File): Int {
        return file1.path.compareTo(file2.path, ignoreCase = true)
    }
}

fun main() {
    val file1 = File("/user")
    val file2 = File("/User")

    val compareResult = CaseInsensitiveFileComparator.compare(file1, file2)
    println(compareResult) // 0

    // 또한 object 클래스를 일반 클래스 객체를 넘길때도 사용할 수 있다.
    // sortedWith 인자로 Comparator를 넘겨준다.
    val files = listOf(File("/Z"), File("/a"))
    val sortResult = files.sortedWith(CaseInsensitiveFileComparator)
    println(sortResult) // "/a, /Z"
}