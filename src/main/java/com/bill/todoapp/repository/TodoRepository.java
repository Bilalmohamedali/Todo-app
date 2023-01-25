package com.bill.todoapp.repository;

import com.bill.todoapp.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long>{
}
