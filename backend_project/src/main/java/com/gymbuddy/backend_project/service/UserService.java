package com.gymbuddy.backend_project.service;

import com.gymbuddy.backend_project.dto.UserDto;
import com.gymbuddy.backend_project.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();

    void saveSignUpData(UserDto userDto);
    void saveQuestionnaire1Data(UserDto userDto);
    void saveQuestionnaire2Data(UserDto userDto);
}