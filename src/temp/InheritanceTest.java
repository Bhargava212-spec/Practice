package temp;

public class InheritanceTest {
    public static void main(String[] args) {
        superParent c = new Child(10);
        c.doSomething();
    }
}

class superParent {
    private int num;

    public superParent() {
        System.out.println("super");
    }

    public superParent(int num) {
        this.num = num;
        System.out.println(STR."num in super parent \{num}");
    }

    public void doSomething() {
        System.err.println("super method");
    }
}

class Parent extends superParent {
    public Parent() {
        System.out.println("Parent constructor");
    }

    public Parent(int num) {
        super(num);
    }

    public void doSomething() {
        System.err.println("Parent method");
    }
}

class Child extends Parent {
    public Child(int num) {
        super(num);
        System.err.println("Child constructor");
    }

    public void doSomething() {
        System.err.println("Child method");
    }
}