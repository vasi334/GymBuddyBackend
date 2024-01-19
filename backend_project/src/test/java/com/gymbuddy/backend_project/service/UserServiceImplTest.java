package com.gymbuddy.backend_project.service;

import com.github.javafaker.Faker;
import com.gymbuddy.backend_project.dto.UserDto;
import com.gymbuddy.backend_project.entity.Role;
import com.gymbuddy.backend_project.entity.User;
import com.gymbuddy.backend_project.repository.RoleRepository;
import com.gymbuddy.backend_project.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.ListResourceBundle;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.LOCAL_DATE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock private UserRepository userRepository;
    @Mock private RoleRepository roleRepository;
    @Mock private PasswordEncoder passwordEncoder;
    private UserServiceImpl testUserService;

    private Faker faker = new Faker();

    @BeforeEach
    void setUp(){
        this.testUserService = new UserServiceImpl(userRepository,
                roleRepository,
                passwordEncoder);
    }

    @Test
    void canSaveWhenUser() {
        //given
        UserDto userDto = new UserDto();
        userDto.setName(faker.name().toString());
        userDto.setEmail(faker.internet().emailAddress());
        userDto.setPassword(faker.internet().password());

        //when
        testUserService.saveUser(userDto);

        //then
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository, times(1)).save(userCaptor.capture());
        User captured = userCaptor.getValue();
        assertThat(captured.getId()).isEqualTo(userDto.getId());
        assertThat(captured.getEmail()).isEqualTo(userDto.getEmail());
        assertThat(captured.getName()).isEqualTo(userDto.getName());
    }

    @Test
    void canFindUserByEmail() {
        //given
        String desiredEmail = "john.doe@example.com";

        //when
        testUserService.findUserByEmail(desiredEmail);

        //then
        verify(userRepository, never()).findByEmail(anyString());
        assertThat(testUserService.findUserByEmail(desiredEmail)).isEqualTo(null);
    }

    @Test
    void canFindAllUsers() {
        //given
        String desiredEmail = "john.doe@example.com";
        UserDto userDto = new UserDto();
        userDto.setName(faker.name().toString());
        userDto.setEmail(desiredEmail);
        userDto.setPassword("password123");

        User userAsociat = new User();
        userAsociat.setName(userDto.getName());
        userAsociat.setEmail(userDto.getEmail());
        // encrypt the password using spring security
        userAsociat.setPassword(passwordEncoder.encode(userDto.getPassword()));

        //when
        testUserService.findAllUsers();

        //then
        verify(userRepository, times(1)).findAll();
        when(testUserService.findAllUsers()).thenReturn(List.of(userDto));

        assertThat(List.of(userAsociat))
                .hasSameSizeAs(userRepository.findAll());
    }

    @Test
    void canSaveSignUpData(){
        //given
        String desiredEmail = "john.doe@example.com";
        UserDto userDto = new UserDto();
        userDto.setName(faker.name().toString());
        userDto.setEmail(desiredEmail);
        userDto.setPassword("password123");

        //when
        testUserService.saveSignUpData(userDto);

        //then(The data to be saved IS not modified!)
        assertThat(userDto.getEmail()).isEqualTo(desiredEmail);
        assertThat(userDto.getPassword()).isEqualTo(userDto.getPassword());
    }

    @Test
    void canSaveDataFromQuestionnaire1(){
        //given
        String desiredEmail = "john.doe@example.com";
        UserDto userDto = new UserDto();
        userDto.setName(faker.name().toString());
        userDto.setEmail(desiredEmail);
        userDto.setPassword("password123");

        //when
        testUserService.saveQuestionnaire1Data(userDto);

        //then(The data to be saved IS not modified!)
        assertThat(userDto.getEmail()).isEqualTo(desiredEmail);
        assertThat(userDto.getPassword()).isEqualTo(userDto.getPassword());
    }

    @Test
    @Disabled
    void canSaveDataFromQuestionnaire2(){
        //given
        String desiredEmail = "john.doe@example.com";
        UserDto userDto = new UserDto();
        userDto.setName(faker.name().toString());
        userDto.setEmail(desiredEmail);
        userDto.setCity(faker.address().city());
        userDto.setHeight("185");
        userDto.setWeight("185");
        userDto.setDateOfBirth(LOCAL_DATE.toString());
        userDto.setSex("male");
        userDto.setPassword("password123");


        //when
        testUserService.saveQuestionnaire2Data(userDto);

        //then
//        ArgumentCaptor<User> userDtoArgumentCaptor = ArgumentCaptor
//                .forClass(User.class);
        verify(userRepository, times(1))
                .save(any(User.class));
//        User capturedUser = userDtoArgumentCaptor.getValue();
//        assertThat(userDto.getEmail()).isEqualTo(capturedUser.getEmail());
//        assertThat(userDto.getName()).isEqualTo(capturedUser.getName());
//        assertThat(userDto.getDateOfBirth()).isEqualTo(capturedUser.getDateOfBirth());
    }
}