package com.gymbuddy.backend_project.service;

import com.gymbuddy.backend_project.dto.UserDto;
import com.gymbuddy.backend_project.entity.Role;
import com.gymbuddy.backend_project.entity.User;
import com.gymbuddy.backend_project.repository.RoleRepository;
import com.gymbuddy.backend_project.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.ListResourceBundle;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock private UserRepository userRepository;
    @Mock private RoleRepository roleRepository;
    @Mock private PasswordEncoder passwordEncoder;
    private UserService testUserService;

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
        userDto.setFirstName("John");
        userDto.setLastName("Doe");
        userDto.setEmail("john.doe@example.com");
        userDto.setPassword("password123");

        Role mockRole = new Role();
        mockRole.setName("ROLE_ADMIN");

        when(roleRepository.findByName("ROLE_ADMIN")).thenReturn(mockRole);
        when(passwordEncoder.encode("password123")).thenReturn("encodedPassword");

        // Call the method to test
        testUserService.saveUser(userDto);

        // Verify interactions
        verify(roleRepository, times(1)).findByName("ROLE_ADMIN");
        verify(userRepository, times(1)).save(any(User.class)); // You might need to adjust this depending on your save method
        verify(passwordEncoder, times(1)).encode("password123");
    }

    @Test
    void canFindUserByEmail() {
        //given
        String desiredEmail = "john.doe@example.com";
        UserDto userDto = new UserDto();
        userDto.setFirstName("John");
        userDto.setLastName("Doe");
        userDto.setEmail(desiredEmail);
        userDto.setPassword("password123");

        //when
        testUserService.findUserByEmail(desiredEmail);

        //then
        ArgumentCaptor<String> emailCaptured = ArgumentCaptor.forClass(String.class);
        verify(userRepository, times(1)).findByEmail(emailCaptured.capture());
        String capturedEmail = emailCaptured.getValue();
        assertEquals(desiredEmail, capturedEmail);
//        when(testUserService.findAllUsers()).thenReturn(List.of(userDto));
//        assertThat(testUserService.findAllUsers())
//                .hasSameSizeAs(userRepository.findByEmail(desiredEmail));
    }

    @Test
    void canFindAllUsers() {
        //given
        String desiredEmail = "john.doe@example.com";
        UserDto userDto = new UserDto();
        userDto.setFirstName("John");
        userDto.setLastName("Doe");
        userDto.setEmail(desiredEmail);
        userDto.setPassword("password123");

        User userAsociat = new User();
        userAsociat.setName(userDto.getFirstName() + " " + userDto.getLastName());
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
}