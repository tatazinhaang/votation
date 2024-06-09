package com.schedule.votation.service;

import aj.org.objectweb.asm.ConstantDynamic;
import com.schedule.votation.entities.UserEntity;
import com.schedule.votation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id de usuário não encontrado"));
   }

   public void deleteById(Long id) {
        userRepository.deleteById(id);
   }
}
