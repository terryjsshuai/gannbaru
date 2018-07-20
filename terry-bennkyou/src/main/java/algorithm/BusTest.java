package algorithm;

/**
 * 公交车8站第一站上去n个人，第二站下去一半人，上来6人，以后每站下去一半，上来人数是上一站减1，第八站还剩10人，求n
 */
public class BusTest {

    public static int test(int up, int remain) {
        //第一站和第八站不需要计算，当中六站需要计算，所以一共计算6次
        if (up > 6) return remain;
        int remain1 = (remain - up) * 2;
        return test(++up, remain1);
    }

    public static void main(String[] args) {
        System.out.println(test(1, 10));
    }
}
