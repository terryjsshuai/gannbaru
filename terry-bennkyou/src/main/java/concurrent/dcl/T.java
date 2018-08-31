package concurrent.dcl;

public class T {
    private static T instance;

    public static T getInstance() {
        if (null == instance) {
            synchronized (T.class) {
                if (null == instance) {
                    instance = new T();
                }
            }
        }
        return instance;
    }
}