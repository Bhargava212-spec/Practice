package oops;

import java.util.HashMap;

public abstract class AbstractDemo {

    private final int num;
    private final String name;


    public AbstractDemo(int num, String name) {
        this.num = num;
        this.name = name;
    }

//    private abstract void demo(); abstract methods should not be private
//    final abstract void demo(); we should not use final for abstract methods
//    static abstract void demo(); we should not use static on abstract methods
    // abstract class can extend another abstract class which can override both abstract and concrete methods
    // abstract class can implement interfaces as well

    abstract Integer getNum();

    private void getNum1() {

    }

    static int demo() {
        return 100;
    }

    final int demo1() {
        return 100;
    }

}
