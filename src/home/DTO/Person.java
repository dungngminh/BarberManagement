package home.DTO;

public abstract class Person {
    protected String name;
    protected String id;
    protected String phone;
    protected int age;

    public Person(String name, String id, String phone, Integer age){
        this.age = age;
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public void setName(String s) {
        this.name = s;
    }

    public String getName () {
        return this.name;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() { return age; }

    public void setAge(int age) {
        this.age = age;
    }

}
