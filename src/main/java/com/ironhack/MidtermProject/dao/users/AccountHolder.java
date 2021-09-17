package com.ironhack.MidtermProject.dao.users;

import com.ironhack.MidtermProject.dao.Address;
import com.ironhack.MidtermProject.dao.accounts.Checking;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.el.stream.Optional;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountHolder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accHolderId;
    private String name;
    private LocalDate dateOfBirth;
    private String mailingAddress;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address primaryAddress;


    public AccountHolder(String name, LocalDate dateOfBirth, String mailingAddress, Address primaryAddress) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.mailingAddress = mailingAddress;
        this.primaryAddress = primaryAddress;
    }

    public int howOld() {
        return Period.between(this.dateOfBirth,LocalDate.now()).getYears();
    }


    //    @OneToMany(mappedBy = "accountHolder")
//    private Set<Checking> checking;

}
