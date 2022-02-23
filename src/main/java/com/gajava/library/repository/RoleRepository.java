package com.gajava.library.repository;

import com.gajava.library.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * repository working with the users role
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    /**
     * find role by name
     *
     * @param name name role
     * @return role entity
     */
    Role findRoleByName(String name);
}
