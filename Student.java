import java.util.Objects;

public class Student {
    private final String name;
    private final int uid;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Student student)) return false;
        return uid == student.uid;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(uid);
    }

    Student(String name, int uid) {
        this.name = name;
        this.uid = uid;
    }

    public int uid() {
        return uid;
    }

    public String name() {
        return name;
    }
}
