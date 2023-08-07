package chapter_03.regex

/**
 * 로컬 함수
 * 함수 내부에 함수를 작성할 수 있다.
 * DRY(Don't Repeat Yourself)원칙을 피하기 위해서 중복되는 코드는 함수로 빼는것이 좋지만,
 * 클래스 내부에 함수가 많아지면 코드 이해하기가 힘들기 때문에, 로컬 함수로 함수 호출영역을 제한하면서 1depth만 사용하면 유용하다.
 */
class User(val id: Int, val name: String, val address: String)

// if문 내부에 호출되는 코드가 중복된다.
// 지금은 if문이 두 개지만, 여러개 비교하게되면, 동일한 코드가 더 많아질 수 있다.
fun saveUser(user: User) {
    if(user.name.isEmpty()) {
        throw IllegalArgumentException("can't name is empty - user id : ${user.id}")
    }
    if(user.address.isEmpty()) {
        throw IllegalArgumentException("can't address is empty - user id : ${user.id}")
    }
}

// 로컬 함수를 이용해서 if문 내부의 중복된 코드를 피한다.
fun saveUser2(user: User) {
    // user 객체를 받지 않아도 된다. -> saveUser2에서 받은 user를 사용할 수 있다.
    fun validate(value: String, fieldName: String) {
        if(value.isEmpty()) {
            throw IllegalArgumentException("can't $fieldName is empty - user id : ${user.id}")
        }
    }

    validate(user.name, "name")
    validate(user.address, "address")
}

// 위 코드는 확장 함수를 이용해서도 사용할 수 있다.
fun User.saveUser3() {
    fun validate(value: String, fieldName: String) {
        if(value.isEmpty()) {
            throw IllegalArgumentException("can't $fieldName is empty - user id : $id")
        }
    }

    validate(name, "Name")
    validate(address, "address")
}