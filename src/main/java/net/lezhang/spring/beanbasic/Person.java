package net.lezhang.spring.beanbasic;

public class Person {
    private int id;
    private String name;
    private int taxIdThisNameDoesntMatter;
    private Address address;
    private String password;
    private String testField;
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setTaxId(int taxId) {
        this.taxIdThisNameDoesntMatter = taxId;
    }
    
    public void setAddress(Address address) {
        this.address = address;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setTestField(String test) {
        this.testField = test;
    }
    
    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public Person() {}
    
    // init method
    public void onCreate() {
        System.out.println("Person created: " + name);
    }
    
    // destroy method
    public void onDestroy() {
        System.out.println("Person destroyed: " + name);
    }
    
    
    public void speak() {
        System.out.println("Person " + id + " speaking: " + name);
        System.out.println("\ttaxId " + taxIdThisNameDoesntMatter);
        System.out.println("\taddress " + address);
        System.out.println("\tpassword " + password);
        System.out.println("\ttestField " + testField);
    }

}
