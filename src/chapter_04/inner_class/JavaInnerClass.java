package chapter_04.inner_class;

public class JavaInnerClass {
    public static class Button implements View {
        @Override
        public State getCurrentState() {
            return new ButtonState();
        }

        @Override
        public void restoreState(State state) { /* ..... */ }

        /**
         * 내부 클래스(Button 객체를 가지고있다.)
         * 그래서 ButtonState는 State를 구현하고 있어서 Serailization이 되지만
         * Button객체는 Serialization이 되지 않기 때문에, ButtonState는 직렬화 되지 않기 때문에,
         * 직렬화 하면 에러가 발생한다.
         * >> 이를 해결하기 위해선 ButtonState가 정적 내부 클래스여야 한다.
         */
        public class ButtonState implements State {
            /* ..... */
        }
    }

}
