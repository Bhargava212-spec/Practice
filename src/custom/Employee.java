package custom;

import java.util.Objects;

public class Employee {

    private int empId;
    private String name;
    private int age;
    private long salary;
    private String dept;

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.age, this.name, this.empId, this.salary);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (Objects.isNull(obj) || obj.getClass() != getClass()) {
            return false;
        }
        var emp = (Employee) obj;
        return emp.getAge() == this.age &&
                emp.getEmpId() == this.getEmpId() &&
                emp.getSalary() == this.salary &&
                Objects.equals(this.name, emp.getName());
    }

//    @Override
//    public Employee clone() {
//        try {
//            Employee clone = (Employee) super.clone();
//            return clone;
//        } catch (CloneNotSupportedException _) {
//            throw new AssertionError();
//        }
//    }


}
