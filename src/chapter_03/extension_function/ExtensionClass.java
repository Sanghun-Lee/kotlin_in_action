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
}
