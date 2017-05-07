package net.lezhang.spring.beanbasic;

public class Address {
    private String street;
    private String zipcode;
    
    public Address() {
        this.street = "6101 Harrington";
        this.zipcode = "98102";
    }
    
    @Override
    public String toString() {
        return street + " " + zipcode;
    }
}
