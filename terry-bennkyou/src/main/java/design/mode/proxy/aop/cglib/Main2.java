package design.mode.proxy.aop.cglib;

import design.mode.proxy.aop.UserService;
import design.mode.proxy.aop.UserServiceImpl;
import net.sf.cglib.proxy.Enhancer;

public class Main2 {
    public static void main(String[] args) {
        CglibProxy cglibProxy = new CglibProxy();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserServiceImpl.class);
        enhancer.setCallback(cglibProxy);

        UserService o = (UserService) enhancer.create();
        o.getName(1);
        o.getAge(1);
    }
}
