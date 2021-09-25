package com.ironhack.MidtermProject.repository.additional;

import com.ironhack.MidtermProject.dao.additional.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Optional<Address> findByCityAndStreetAndNumberAndPostCode(String city, String street, String number, String postCode);

}
