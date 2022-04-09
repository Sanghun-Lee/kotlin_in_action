package chapter_04.constructor.secondary

class Example {
}

class Context
class AttributeSet

// 클래스 뒤에 괄호가 없기 때문에, 주 생성자는 없다.
open class View {
    // 부 생성자1
    constructor(context: Context) {
        // something code...
    }

    // 부 생성자2
    constructor(context: Context, attributeSet: AttributeSet) {
        // something code...
    }
}

/**
 * View 클래스를 상속받으면서 동일하게 부 생성자를 정의할 수 있다.
 * 1. View 클래스는 주 생성자가 없기 때문에, 상속 받을 때, 괄호 없이 사용한다.
 * 2. MyView의 부 생성자를 호출하면서 : super(부모클래스 부 생성자)로 부모 클래스 객체를 생성한다.
 * 3. 즉 자식객체를 생성할 때 부모 생성자를 호출해야 한다.
 */
class MyView : View {
    constructor(context: Context) : super(context) {
        // something code...
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        // something code...
    }

    // this로 MyView의 다른 생성자를 호출할 수 있다.
    constructor(context: Context, flag: Int) : this(context) {
        // something code...
    }

    // MyView에는 주 생성자가 없기 때문에, 부 생성자에서 부모 객체를 호출해야만 한다.
    // 그래서 부 생성자에서 부모 클래스 객체를 호출하는 부분이 없다면 에러가 발생한다.
//    constructor(flag: Int) { // super를 사용하거나, 다른 MyView 생성자를 호출해서 View 객체를 만들어야만 한다.
//        // something code...
//    }
}

class SecondMyView(context: Context): View(context) {

}

/**
 * 자식 클래스에 주 생성자가 있다면, 상속받을 때 -> 주 생성자로 호출할 때, 부모 클래스의 주 생성자 또는 부 생성자를 호출해야만 한다.
 * 아래 코드는 에러가 발생한다.
 */
//class MyView3() : View {
//    constructor(context: Context) : super(context) {
//        // something...
//    }
//}