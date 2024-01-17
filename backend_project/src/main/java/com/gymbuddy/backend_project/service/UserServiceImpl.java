package com.gymbuddy.backend_project.service;

import com.gymbuddy.backend_project.dto.UserDto;
import com.gymbuddy.backend_project.entity.Role;
import com.gymbuddy.backend_project.entity.User;
import com.gymbuddy.backend_project.repository.RoleRepository;
import com.gymbuddy.backend_project.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void saveUser(UserDto userDto) {
        try{
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setGoal(userDto.getGoal());
        user.setHeight(userDto.getHeight());
        user.setWeight(userDto.getWeight());
        user.setCity(userDto.getCity());
        user.setDateOfBirth(userDto.getDateOfBirth());

        userRepository.save(user);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Transactional
//    public void saveUser(UserDto userDto) {
//        User user = new User();
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
//        user.setGoal(userDto.getGoal());
//        user.setHeight(userDto.getHeight());
//        user.setWeight(userDto.getWeight());
//        user.setCity(userDto.getCity());
//        user.setDateOfBirth(userDto.getDateOfBirth());
//
//        entityManager.persist(user);
//    }

    @Override
    public User findUserByEmail(String email) {
        return null;
    }

    private Role checkRoleExist() {
        // Implementează logica pentru a verifica și returna sau crea un nou obiect Role
        // Dacă nu există deja un "ROLE_USER" în baza de date
        return null;
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        // Transformă lista de User într-o listă de UserDto, dacă este necesar
        // Poți folosi un model de mapare sau manual să construiești lista
        // Exemplu simplu:
        return users.stream()
                .map(user -> new UserDto(user.getName(), user.getEmail(), user.getGoal(), user.getHeight(), user.getWeight(), user.getCity(), user.getSex(), user.getDateOfBirth()))
                .collect(Collectors.toList());
    }

    private UserDto signUpData;
    private UserDto questionnaire1Data;

    public void saveSignUpData(UserDto userDto) {
        signUpData = userDto;
    }

    @Override
    public void saveQuestionnaire1Data(UserDto userDto) {
        questionnaire1Data = userDto;
    }

    @Override
    public void saveQuestionnaire2Data(UserDto userDto) {
        // Combinează datele din toate etapele și salvează în baza de date
        UserDto user = new UserDto();
        user.setName(signUpData.getName());
        user.setEmail(signUpData.getEmail());
        user.setPassword(signUpData.getPassword());
        user.setGoal(questionnaire1Data.getGoal());
        user.setCity(userDto.getCity());
        user.setHeight(userDto.getHeight());
        user.setSex(userDto.getSex());
        user.setWeight(userDto.getWeight());
        user.setDateOfBirth(userDto.getDateOfBirth());

        // Salvează în baza de date
        //userRepository.save(user);
        saveUser(user);
    }

}
