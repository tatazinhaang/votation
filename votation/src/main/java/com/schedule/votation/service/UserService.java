package com.schedule.votation.service;


import com.schedule.votation.entities.UserEntity;
import com.schedule.votation.repository.UserRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public UserEntity findById(Long id) {
        var user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        }
        throw new RuntimeException("Id de usuario não encontrado");
    }

    public UserEntity save(UserEntity user) {
        if (user.getName().isEmpty()) {
            throw new RuntimeException("O campo nome não pode está vazio");
        }
        return userRepository.save(user);
    }

   public UserEntity update(Long id, UserEntity newUser) {
        var userEntity = userRepository.findById(id);
        if (userEntity.isPresent()) {
            var user = userEntity.get();
            user.setName(newUser.getName());
            return userRepository.save(user);
        } else {
            throw new RuntimeException("Usuário não encontrado");
        }
   }

   public void deleteById(Long id) {
        var user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new RuntimeException("Usuário não encontrado");
        }
   }
}
