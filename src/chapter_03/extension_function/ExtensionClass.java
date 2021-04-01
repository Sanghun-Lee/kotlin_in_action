package chapter_03.extension_function;

public class ExtensionClass {
    private int value;

    private String encapsulate = "capsuled";

    public ExtensionClass(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static void main(String[] args) {
        // java에서 확장함수 사용
        int val = ExtensionFunctionKt.multiplyValue(new ExtensionClass(30));

        // 클래스의 정적메소드를 사용하는 것 처럼 사용한다. 매개변수로는 수신 객체가 들어간다.
        char lastChar = ExtensionFunctionKt.lastChar("Kotlin To Java");
    }
}
