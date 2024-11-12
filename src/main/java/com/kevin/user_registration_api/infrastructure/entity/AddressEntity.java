package com.kevin.user_registration_api.infrastructure.entity;

import com.kevin.user_registration_api.domain.model.AddressDomain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "address")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String postalCode;
    private String street;
    private String additionalInfo;
    private String neighborhood;
    private String number;
    private String state;
    private String stateName;

    public AddressDomain toDomain() {
        return new AddressDomain(id, postalCode, street, additionalInfo, neighborhood, number, state, stateName);
    }

    public static AddressEntity fromDomain(AddressDomain addressDomain) {
        return new AddressEntity(addressDomain.getId(), addressDomain.getPostalCode(), addressDomain.getStreet(), addressDomain.getAdditionalInfo(),
                addressDomain.getNeighborhood(), addressDomain.getNumber(), addressDomain.getState(), addressDomain.getStateName());
    }
}
