package net.lezhang.spring.data;

public class Offer {
    private int id;
    private String name;
    private String email;
    private String offer;
    
    public Offer() { }
    
    public Offer(String name, String email, String offer) {
        this.name = name;
        this.email = email;
        this.offer = offer;
    }
    
    public Offer(int id, String name, String email, String offer) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.offer = offer;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getOffer() {
        return offer;
    }
    public void setOffer(String offer) {
        this.offer = offer;
    }
    @Override
    public String toString() {
        return "Offer [id=" + id + ", name=" + name + ", email=" + email + ", offer=" + offer + "]";
    }

}
