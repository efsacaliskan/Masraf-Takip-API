package com.innova.masraf_takip_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.innova.masraf_takip_api.model.User;
import com.innova.masraf_takip_api.repository.TransactionRepository;
import com.innova.masraf_takip_api.repository.UserRepository;
import com.innova.masraf_takip_api.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    
    @Transactional
    public User createUser(User user) {
        return userRepository.save(user);
    }
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
    }
    @Transactional
    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        user.setName(userDetails.getName());
        user.setUsername(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());
        user.setCreatedAt(userDetails.getCreatedAt());
        user.setRole(userDetails.getRole());
        return userRepository.save(user);
    }
    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        // Kullanıcıya ait tüm işlemleri sil
        transactionRepository.deleteById(id);
     
        userRepository.delete(user);
    }
}
