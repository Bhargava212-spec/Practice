package oops;

public class InheritanceDemo {

    public void print(){
        System.out.println("in parent");
    }
}

class Child extends InheritanceDemo{
    // cannot throw checked exception when there is no declaration in parent
    // if parent have checked exception then same should be thrown in the child
    // or child exceptions should be thrown

    @Override
    public void print() throws RuntimeException {
        super.print();
    }
}
