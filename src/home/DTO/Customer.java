package home.DTO;

public class Customer extends Person {
    private Integer numVisited;

    public Customer(String name, String id, String phone, Integer age, Integer numVisited) {
        super(name, id, phone, age);
        this.numVisited = numVisited;
    }
    public Integer getNumVisited() {return numVisited;}
}
