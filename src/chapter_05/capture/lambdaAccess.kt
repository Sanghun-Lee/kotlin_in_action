package chapter_05.capture

fun printMessageWithPrefix(messages: Collection<String>, prefix: String) {
    messages.forEach {
        println("$prefix$it") // 람다 내부에서 함수에 정의되어있는 prefix 파라미터에 접근가능하다.
    }
}

/**
 * 또한 final 변수가 아닌, 일반 변수도 접근 가능하고 수정도 가능하다
 */
fun printProblemCounts(response: Collection<String>) {
    var clientErrors = 0
    var serverErrors = 0

    response.forEach {
        if (it.startsWith("4")) {
            clientErrors++ // 람다식 외부 변수인 clientErrors 변수에 접근 가능하고 수정도 가능하다.
        } else if (it.startsWith("5")) {
            serverErrors++ // 람다식 외부 변수인 serverErrors 변수에 접근 가능하고 수정도 가능하다.
        }
    }
}