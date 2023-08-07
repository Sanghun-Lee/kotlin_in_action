package medium.delegate

import kotlin.reflect.KProperty

/**
 * 위임패턴은 프로퍼티로도 사용될 수 있다.
 * 프로퍼티가 위임받는다는것은, getter와 setter수행의 책임을 위임받는 객체에게 넘기는 것을 뜻한다.
 */
class PropertyDelegate {
    var name: String by UppercaseDelegate()
    val name2: String by lazy { "hello world" }

    override fun toString(): String {
        return "Person(name='$name')"
    }
}

// 현재 예시로는 Uppercase만 수행하도록 하였지만, caching이라던지, EventObserver등으로 활용될 수 있다.
class UppercaseDelegate() {
    private var storedValue: String = "Default"

    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return storedValue
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        storedValue = value.uppercase() // 다음 코드와 같이 추가적인 연산을 수행할 수 있다.
    }
}