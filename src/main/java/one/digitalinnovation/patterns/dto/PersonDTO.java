package one.digitalinnovation.patterns.dto;

import one.digitalinnovation.patterns.model.Address;

public class PersonDTO {
    public Long id;
    public String name;

    public int age;
    public AddressDTO address;


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

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }
}
