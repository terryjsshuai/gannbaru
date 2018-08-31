package concurrent.dcl;

public class T3 {
    private static final T3 instance = new T3(); // final 可少吗？
    public final M m = new M(); // final 可少吗？

    public static T3 getInstance() {
        return instance;
    }
}
