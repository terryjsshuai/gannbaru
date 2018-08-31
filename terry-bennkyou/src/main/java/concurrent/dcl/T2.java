package concurrent.dcl;

public class T2 {

    private static volatile T2 instance;
    public M m; // 这里没有 final 修饰

    public static T2 getInstance() {
        if (null == instance) {
            synchronized (T2.class) {
                if (null == instance) {
                    T2 temp = new T2();
                    temp.m = new M();
                    instance = temp;
                }
            }
        }
        return instance;
    }
}
