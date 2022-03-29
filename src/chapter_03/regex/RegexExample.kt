package chapter_03.regex

fun parsePath(path: String) {
    val regex = """(.+)/(.+)\.(.+)""".toRegex() // \.는 .을 표시하기위한 이스케이프 시퀀스
    val matchResult = regex.matchEntire(path)
    matchResult?.let {
        val (directory, fileName, extension) = matchResult.destructured
        println("Dir : $directory, file name : $fileName, ext : $extension")
    }
}

fun main() {
    val fileDirExample = "User/kotlin_in_action/chapter_03/Readme.md"
    parsePath(fileDirExample)
}