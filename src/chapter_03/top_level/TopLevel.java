package chapter_03.top_level;

import java.util.ArrayList;
import java.util.List;

public class TopLevel {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        String output = JoinKt.joinToString(list, ", ", "(", ")");
        System.out.println(output);

        list.add(JoinKt.CHAPTER);
    }
}
