package home.DTO;

public class Employee extends Person {

    private int exp;
    private int salary;
    private int times;

    public Employee(String name, String id, String phone, Integer age, int exp) {
        super(name, id, phone, age);
        this.exp = exp;
    }

    public void setTimes (int t) {
        this.times = t;
    }
    public int getTimes () {return times;}
    public void setSalary(int salary) {this.salary = salary;}
    public float getSalary() {return salary;}

    public float getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return this.getName();
    }

}
