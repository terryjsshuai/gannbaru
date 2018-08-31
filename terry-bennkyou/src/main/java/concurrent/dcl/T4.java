package concurrent.dcl;

public class T4 {
    public final M m = new M(); // final 可少吗？

    private static class LazyHolder {
        public static T4 instance = new T4();
    }

    public static T4 getInstance() {
        return LazyHolder.instance;
    }
}