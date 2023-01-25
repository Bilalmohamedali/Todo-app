package com.bill.todoapp;

import com.bill.todoapp.entity.Todo;
import com.bill.todoapp.entity.Users;
import com.bill.todoapp.repository.TodoRepository;
import com.bill.todoapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoAppApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TodoRepository todoRepository;

	public static void main(String[] args) {
		SpringApplication.run(TodoAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Users users = new Users();
		users.setPassword("should be hashed");
		users.setUsername("Bill");

		Todo todo  = new Todo();
		todo.setContent("make a new project");

		users.getTodoList().add(todo);

		todoRepository.save(todo);
		userRepository.save(users);


	}
}
