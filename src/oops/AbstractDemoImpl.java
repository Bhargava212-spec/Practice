package oops;

public class AbstractDemoImpl extends AbstractDemo {

    private final String city;

    public AbstractDemoImpl(int num, String name, String city) {
        super(num, name);
        this.city = city;
    }


    @Override
    Integer getNum() {
        return 0;
    }

    public static void main(String[] args)  {
        AbstractDemo abstractDemo = new AbstractDemoImpl(10, "Test", "city");
        var res = abstractDemo.demo1();
    }
}
