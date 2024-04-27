package com.pagination.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pagination.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
