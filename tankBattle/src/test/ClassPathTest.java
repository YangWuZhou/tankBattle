package test;

import java.net.URL;

public class ClassPathTest {
    public static void main(String[] args) {
        URL url = ClassPathTest.class.getClassLoader().getResource("img/background/bg2.gif");
        System.out.println(url);
    }
}
