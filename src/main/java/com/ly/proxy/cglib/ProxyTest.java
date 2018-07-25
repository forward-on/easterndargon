package com.ly.proxy.cglib;

/**
 * @Description: cglib 代理：
 *                      代理没有实现接口的类！
 *                      cglib代理，也称为子类代理，它是在内存中构建一个子类对象来实现对目标对象的功能扩展。
 *                      cglib是一个强大的高性能的 代码生成包 ，它能在运行期间扩展java类和实现java接口。Spring AOP 和 synaop都使用了，为他们提供方法的拦截
 *                      ASM 是一种 小而快 的字节码处理框架，它能转换字节码并生成新的类。 直接使用 asm 必须对 JVM 内部结构包括class文件的格式和指令集都要十分熟悉，所以不鼓励。
 *
 *                      cglib 子类代理实现方法：
 *                          1、引入 cglib 的 jar 包。spring的核心包中已经包括了cglib的功能，所以直接引入 spring-core-xxxxx.jar
 *                          2、动态构建子类
 *                          3、代理类不能为final，否则报错
 *                          4、目标对象的方法不能为 final/static， 否则不会执行目标对象的额外的业务方法
 *                          5、如果方法为 static/private 则无法进行代理
 *
 * 5.如果方法为static,private则无法进行代理。
 * @Date 2018-07-25 15:46
 * @Author ly
 */
public class ProxyTest {

    public static void main(String[] args) {

        Horse horse = new Horse();
        Horse proxy = (Horse) new CglibProxy(horse).getProxyInstance();
        proxy.buyHorse();

    }

}
