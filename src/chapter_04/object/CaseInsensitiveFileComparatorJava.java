package chapter_04.object;

import java.util.Comparator;

/**
 * 자바로 구현해본 singletone class
 * 여기서 정말 하나의 인스턴스만 가지게 하기위해 locking 규칙을 두게되면 더 복잡해진다.
 */
public final class CaseInsensitiveFileComparatorJava implements Comparator<File> {

    private static final CaseInsensitiveFileComparatorJava instance = new CaseInsensitiveFileComparatorJava();

    private CaseInsensitiveFileComparatorJava() {}

    @Override
    public int compare(File file1, File file2) {
        // Java에서 object class를 접근할 때에는 INSTANCE 이름으로 접근해야 한다.
        return CaseInsensitiveFileComparator.INSTANCE.compare(file1, file2);
    }

    public static CaseInsensitiveFileComparatorJava getInstance() {
        return instance;
    }
}
