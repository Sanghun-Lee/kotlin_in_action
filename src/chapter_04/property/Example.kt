package chapter_04.property

interface User {
    val nickname: String
}

class PrivateUser(override val nickname: String) : User

class SubscribingUser(private val email: String) : User {
    override val nickname: String
        get() = email.substringBefore("@") // 커스텀 게터, nickname을 요청할 때 마다 계산한다.
}

class FacebookUser(accountId: String) : User {
    override val nickname: String = getFacebookName(accountId)

    private fun getFacebookName(accountId: String): String {
        // access facebook with network
        // get name
        return ""
    }
}

interface User2 {
    val email: String

    //    val nickname: String = email.substringBefore("@") // 이렇게 바로 상태를 저장할 순 없다.
    val nickname: String
        get() = email.substringBefore("@")
}

class UserImpl(override val email: String) : User2 {
    // nickname을 재정의하지 않아도 문제가 없다.
}

class UserImpl2(override val email: String) : User2 {
    override val nickname: String = email.substringBefore("@") // 또는 재정의 해도 된다.
}

fun main() {
    val subUser: User = SubscribingUser("hun@naver.com")

    val name1 = subUser.nickname
    val name2 = subUser.nickname

    println("name1 == name2 : ${name1 == name2}") // true
    println("name1 === name2 : ${name1 === name2}") // false

    val privateUser: User = PrivateUser("hun")

    val name3 = privateUser.nickname
    val name4 = privateUser.nickname

    println("name3 == name4 : ${name3 == name4}") // true
    println("name3 === name4 : ${name3 === name4}") // true

    val testUser: User2 = UserImpl("hun@naver.com")
    val testUser2: User2 = UserImpl2("hun@naver.com")

    println("testUser.nickname == testUser.nickname : ${testUser.nickname == testUser.nickname}") // true
    println("testUser.nickname === testUser.nickname : ${testUser.nickname === testUser.nickname}") // false

    println("testUser2.nickname == testUser2.nickname : ${testUser2.nickname == testUser2.nickname}") // true
    println("testUser2.nickname === testUser2.nickname : ${testUser2.nickname === testUser2.nickname}") // false
}