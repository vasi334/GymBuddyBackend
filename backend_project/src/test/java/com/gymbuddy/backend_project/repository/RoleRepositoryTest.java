package com.gymbuddy.backend_project.repository;

import com.gymbuddy.backend_project.entity.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleTestRepository;

    @AfterEach
    void clear(){
        roleTestRepository.deleteAll();
    }

    @Test
    void checkExistRoleByName() {
        //given
        String desiredName = "ROLE_USER";
        Role role = new Role();
        role.setName(desiredName);
        roleTestRepository.save(role);

        //then
        Role searchedRole = roleTestRepository.findByName(desiredName);

        //expected
        assertThat(searchedRole).isNotNull();
    }

    @Test
    void checkDoesNotExistRoleByName(){
        //given
        String desiredName = "ROLE_USER";

        //then
        Role searchedRole = roleTestRepository.findByName(desiredName);

        //expected
        assertThat(searchedRole).isNull();
    }
}