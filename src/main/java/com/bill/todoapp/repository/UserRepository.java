package com.bill.todoapp.repository;

import com.bill.todoapp.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
}
