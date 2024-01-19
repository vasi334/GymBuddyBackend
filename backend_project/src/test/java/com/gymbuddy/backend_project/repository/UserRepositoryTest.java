package com.gymbuddy.backend_project.repository;

import com.gymbuddy.backend_project.entity.Role;
import com.gymbuddy.backend_project.entity.User;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userTestRepository;

    @AfterEach
    void clear(){
        userTestRepository.deleteAll();
    }

    @Test
    void checkExistUserWithDesiredEmail() {
        //given
        String desireEmail = "rauldolha@gmail.com";
        String randomText = "RandomText";
        Role rol = new Role();
        rol.setName("ROLE_USEr");
        User user = new User();
        user.setName(randomText);
        user.setEmail(desireEmail);
        user.setPassword(randomText);
        user.setGoal(randomText);
        user.setHeight(randomText);
        user.setWeight(randomText);
        user.setCity(randomText);
        user.setDateOfBirth(LocalDate.now().toString());
        user.setRoles(List.of(rol));

        userTestRepository.save(user);

        //then
        User searchedUser = userTestRepository.findByEmail(desireEmail);

        //expected
        assertThat(searchedUser).isNotNull();
    }

    @Test
    void checkDoesNotExistUserWithDesiredEmail() {
        //given
        String desireEmail = "rauldolha@gmail.com";

        //then
        User searchedUser = userTestRepository.findByEmail(desireEmail);

        //expected
        assertThat(searchedUser).isNull();
    }
}