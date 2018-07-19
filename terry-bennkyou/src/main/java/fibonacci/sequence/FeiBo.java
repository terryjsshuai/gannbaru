package fibonacci.sequence;

public class FeiBo {
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            test(i);
        }
        System.err.println("===================================");
        for (int i = 1; i <= 10; i++) {
            System.err.println(recursion(i));
        }

        System.err.println("===================================");
        testArr(10);
    }

    /**
     * 循环
     *
     * @param n
     */
    private static void test(int n) {

        int num1 = 0;
        int num2 = 1;
        int numn = 1;
        if (n == 1) numn = 0;

        for (int i = 3; i <= n; i++) {
            numn = num1 + num2;
            num1 = num2;
            num2 = numn;
        }
        System.err.println(n + "个数的结果为：" + numn);
    }

    /**
     * 递归
     *
     * @param n
     * @return
     */
    public static int recursion(int n) {

        if (n == 1) {
            return 0;
        }

        if (n == 2) {
            return 1;
        }
        return recursion(n - 1) + recursion(n - 2);
    }

    /**
     * 数组
     *
     * @param n
     */
    public static void testArr(int n) {
        int arr[] = new int[n];
        arr[0] = 0;
        arr[1] = 1;
        for (int i = 2; i < arr.length; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        for (int j = 0; j < arr.length; j++) {
            System.err.println("数组法: " + arr[j]);
        }
    }
}
