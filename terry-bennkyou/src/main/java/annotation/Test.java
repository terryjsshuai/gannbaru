package annotation;

public class Test {
    public static void main(String[] args) throws IllegalArgumentException {
        User user = UserFactory.create();

        System.out.println(user.getName());
        System.out.println(user.getAge());

        System.out.println("--------------------------------------------------------");

        User user1 = new User();

        user1.setName("liang");
        user1.setAge("1");
        System.out.println(UserCheck.check(user1));
    }
}
