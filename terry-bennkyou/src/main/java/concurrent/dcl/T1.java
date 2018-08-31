package concurrent.dcl;

public class T1 {
    private static volatile T1 instance;
    public M m; // 这里没有 final 修饰

    public static T1 getInstance() {
        if (null == instance) {
            synchronized (T1.class) {
                if (null == instance) {
                    instance = new T1();
                    instance.m = new M();
                }
            }
        }
        return instance;
    }
}
