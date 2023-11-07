package chapter_04.`object`

/**
 * As-is
 */
//class User {
//    private val nickname: String
//
//    constructor(email: String) {
//        nickname = email.substringBefore('@')
//    }
//
//    constructor(facebookAccountId: Int) {
//        nickname = getFacebookName(facebookAccountId)
//    }
//
//    private fun getFacebookName(facebookAccountId: Int): String = ""
//}

/**
 * 위와 같이 부 생성자를 두 개 가지고있는 클래스를 간단하게 하는데는 클래스의 인스턴스를 생성하는 팩토리 메서드가 있다.
 */
class User private constructor(val nickName: String) {
    companion object {
        // 목적에 따라 팩토리 메서드의 이름을 정할 수 있다.
        // 리턴타입이 User가 아닌, SubscribeUser / FacebookUser로 변경할 수 도 있다.
        fun newSubscribingUser(email: String) = User(email.substringBefore('@'))
        fun newFacebookUser(accountId: Int) = User(getFacebookName(accountId))

        private fun getFacebookName(facebookAccountId: Int): String = ""
    }
}