package com.kevin.user_registration_api.domain.model;

public class AddressDomain {

    private Long id;
    private String postalCode;
    private String street;
    private String additionalInfo;
    private String neighborhood;
    private String number;
    private String state;
    private String stateName;

    public AddressDomain() {
    }

    public AddressDomain(Long id, String postalCode, String street, String additionalInfo, String neighborhood, String number, String state, String stateName) {
        this.id = id;
        this.postalCode = postalCode;
        this.street = street;
        this.additionalInfo = additionalInfo;
        this.neighborhood = neighborhood;
        this.number = number;
        this.state = state;
        this.stateName = stateName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }
}
