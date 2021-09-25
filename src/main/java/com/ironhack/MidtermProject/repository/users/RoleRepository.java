package com.ironhack.MidtermProject.repository.users;

import com.ironhack.MidtermProject.dao.users.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
