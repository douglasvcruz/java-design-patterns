package one.digitalinnovation.patterns.model;

import javax.persistence.*;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    private int age;
    @ManyToOne
    private Address address;


    public int getAge() {
        return age;
    }
    public void setAge(int idade) {
        this.age = idade;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}