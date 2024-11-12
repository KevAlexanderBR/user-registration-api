package com.kevin.user_registration_api.infrastructure.entity;

import com.kevin.user_registration_api.domain.model.UserDomain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    public UserDomain toDomain() {
        return new UserDomain(id, name, email, address.toDomain());
    }

    public static UserEntity fromDomain(UserDomain userDomain) {
        return new UserEntity(userDomain.getId(), userDomain.getName(), userDomain.getEmail(), AddressEntity.fromDomain(userDomain.getAddress()));
    }
}
