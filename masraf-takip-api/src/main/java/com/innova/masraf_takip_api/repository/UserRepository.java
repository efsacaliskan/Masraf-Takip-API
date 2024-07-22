package com.innova.masraf_takip_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.innova.masraf_takip_api.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);
	Optional<User> findById(Long id);
}



