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

    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }

    public UserEntity save(UserEntity user) {
        return userRepository.save(user);
    }

   public ResponseEntity<UserEntity> update(Long id, UserEntity userDetails) {
        return findById(id).map(user -> {
            user.setName(userDetails.getName());
            userRepository.save(user);
            return ResponseEntity.ok(user);
        })
                .orElse(ResponseEntity.notFound().build());
   }

   public void deleteById(Long id) {
        userRepository.deleteById(id);
   }
}
