package chapter_04.kt_interface;

// 코틀린의 디폴트 메소드를 포함한 인터페이스에대한 Java와의 호환성 체크
// 코틀린은 Java6와의 호환성을 지원하기 때문에, 디폴트 메소드가 없는 Java에서 사용했을 때 체크해보자.
public class KtInterfaceJavaCompatibility {
    class JavaClass implements KtInterfaceExample.Focusable {

        // default method 여부와는 관계없이, 모든 메소드를 재정의 하여야 한다.
        @Override
        public void setFocus(boolean b) {

        }

        @Override
        public void showOff() {

        }
    }


    abstract class Test {

    }

    class TestClass extends Test {

    }
}

/**
 * 취약 기반 클래스
 * 정의 : 하위 클래스(자식 클래스)가 기반 클래스(부모 클래스)에 대해 가졌던 가정이 기반 클래스를 변경함으로써 깨져버린 경우 기반 클래스를 취약 기반 클래스라고 한다.
 * 상황
 * 0. ParentClass가 changeFragileValue메소드를 재정의 하기 전
 * 1. ChildClass는 changeFragileValue를 호출하여 ParentParentClass의 fragileValue가 100으로 바뀌었을 것이라 기대
 * 2. ParentClass가 changeFragileValue메소드를 재정의해서 fragileValue값이 50으로 변경
 * 3. ChildClass는 changeFraileValue를 호출해도 fragileValue값이 50으로밖에 되지 않아서 문제가 발생
 */
class FragileBaseClass {
    class ParentParentClass {
        public int fragileValue = 30;

        public void changeFragileValue() {
            fragileValue = 100;
            System.out.println("ParentParentClass");
        }
    }

    class ParentClass extends ParentParentClass {
        // Do nothing
        // 2. ParentClass에서 changeFragileValue를 재정의
        @Override
        public void changeFragileValue() {
            fragileValue = 50;
            System.out.println("ParentClass");
        }
    }

    class ChildClass extends ParentClass {
        public void doSomething() {
            System.out.println("fragileValue : " + fragileValue);
            // 1. ParentClass에서 메소드 재정의 하기 전, fragileValue의 값이 100으로 바뀌었을것이라 기대
            changeFragileValue();
            System.out.println("fragileValue : " + fragileValue);
            // 3. fragileValue의 값이 100이 되어야 하는데, ParentClass에서 50으로 값을 바꿔서 문제가 발생
        }
    }
}