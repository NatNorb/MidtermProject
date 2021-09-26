package com.ironhack.MidtermProject.repository.users;

import com.ironhack.MidtermProject.dao.users.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface AccountHolderRepository extends JpaRepository<AccountHolder, Long> {

    Optional<AccountHolder> findByNameAndDateOfBirthAndMailingAddress (String name, LocalDate dob, String mail);

}
