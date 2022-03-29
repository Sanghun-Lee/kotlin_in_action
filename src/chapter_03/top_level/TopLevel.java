package chapter_03.top_level;

import java.util.ArrayList;
import java.util.List;

public class TopLevel {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(JoinKt.CHAPTER); // const로 정의하면 자바에선 public static final 로 인식되어 바로 변수처럼 쓸 수 있다.

        /* 프로퍼티의 경우, getXXX와 setXXX로 값을 가져오고 설정한다. */
        JoinKt.getVariable();
        JoinKt.setVariable("set variable");

        String output = JoinKt.joinToString(list, ", ", "(", ")");
        System.out.println(output);

        list.add(JoinKt.CHAPTER);
    }
}
