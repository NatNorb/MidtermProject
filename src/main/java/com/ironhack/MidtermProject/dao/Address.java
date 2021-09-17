package com.ironhack.MidtermProject.dao;

import com.ironhack.MidtermProject.dao.users.AccountHolder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;
    private String city;
    private String street;
    private String number;
    private String postCode;

    //@OneToOne (mappedBy = "primaryAddress")
    //private AccountHolder accountHolder;

    public Address(String city, String street, String number, String postCode) {
        this.city = city;
        this.street = street;
        this.number = number;
        this.postCode = postCode;
    }
}
