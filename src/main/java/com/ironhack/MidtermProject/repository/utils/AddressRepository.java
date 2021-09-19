package com.ironhack.MidtermProject.repository.utils;

import com.ironhack.MidtermProject.dao.utils.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
