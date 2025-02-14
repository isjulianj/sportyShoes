package com.web.sportyShoes.service;

import com.web.sportyShoes.dto.UserEditDto;
import com.web.sportyShoes.dto.UserRegistrationDto;
import com.web.sportyShoes.model.Password;
import com.web.sportyShoes.model.User;
import com.web.sportyShoes.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,  PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getALl() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }


    public User registerUser(UserRegistrationDto registrationDto) {

        if (userRepository.existsByEmail(registrationDto.getEmail().toLowerCase())) {
            throw new IllegalArgumentException("A user with this email already exists.");
        }

        User user = new User();
        user.setFirst_name(registrationDto.getFirstName());
        user.setLast_name(registrationDto.getLastName());
        user.setEmail(registrationDto.getEmail().toLowerCase());

        Date now = new Date();
        user.setCreatedAt(now);
        user.setUpdatedAt(now);


        user.setSlug(generateSlug(user));

        // Create the Password entity.
        Password passwordEntity = new Password();
        String encodedPassword = passwordEncoder.encode(registrationDto.getPassword());
        passwordEntity.setHash(encodedPassword);

        user.setPassword(passwordEntity);

        passwordEntity.setUser(user);


        return userRepository.save(user);
    }

    public User updateUser(UserEditDto userEditDto) {
        User user = userRepository.findById(userEditDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        user.setFirst_name(userEditDto.getFirstName());
        user.setLast_name(userEditDto.getLastName());
        user.setEmail(userEditDto.getEmail().toLowerCase());
        user.setSlug(userEditDto.getSlug());
        user.setUpdatedAt(new Date());

        if (userEditDto.getPassword() != null && !userEditDto.getPassword().isEmpty()) {
            Password passwordEntity = user.getPassword();
            String encodedPassword = passwordEncoder.encode(userEditDto.getPassword());
            passwordEntity.setHash(encodedPassword);
        }

        return userRepository.save(user);
    }

    public User authenticate(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword().getHash())) {
            return user;
        }
        return null;
    }


    private String generateSlug(User user) {
        String firstName = user.getFirst_name() != null ? user.getFirst_name().trim().toLowerCase() : "";
        String lastName = user.getLast_name() != null ? user.getLast_name().trim().toLowerCase() : "";
        return firstName + "-" + lastName;
    }

}
