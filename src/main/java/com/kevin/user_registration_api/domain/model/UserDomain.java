package com.kevin.user_registration_api.domain.model;

public class UserDomain {

    private Long id;
    private String name;
    private String email;
    private AddressDomain address;

    public UserDomain() {
    }

    public UserDomain(Long id, String name, String email, AddressDomain address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AddressDomain getAddress() {
        return address;
    }

    public void setAddress(AddressDomain address) {
        this.address = address;
    }

}
