package chapter_04.constructor

// 파라미터가 하나인 클래스의 주 생성자
class User(val nickName: String) {
    fun getName(): String = nickName // 주 생성자에 val이 붙어있으면 해당 클래스에서, 그리고 외부에서도 사용할 수 있다.
}

fun main() {
    val user = User("user")
    println("user nickname : ${user.nickName}") // nickName이 private이 아니라서, 외부에서도 접근할 수 있다.
}

// 파라미터가 하나인 주 생성자 클래스
class SecondUser(nickName: String) {
//    fun getName(): String = nickName  // 주 생성자의 파라미터인 nickName에 val이 붙어있지 않아서 함수에서 사용할 수 없다.

    // nickName은 클래스 프로퍼티를 초기화 할 때만 사용할 수 있다.
    val name = nickName
    val subName = nickName.first()

    // 또는 init블록에서만 사용할 수 있다.
    init {
        println("SecondaryUser nickName : $nickName")
    }
}