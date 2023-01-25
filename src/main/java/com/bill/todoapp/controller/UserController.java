package com.bill.todoapp.controller;

import com.bill.todoapp.entity.Todo;
import com.bill.todoapp.entity.Users;
import com.bill.todoapp.repository.TodoRepository;
import com.bill.todoapp.repository.UserRepository;
import com.bill.todoapp.request.AddTodoRequest;
import com.bill.todoapp.request.AddUserRequest;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserRepository userRepository;
    private TodoRepository todoRepository;

    public UserController(UserRepository userRepository, TodoRepository todoRepository) {
        this.userRepository = userRepository;
        this.todoRepository = todoRepository;
    }

    @GetMapping("/{userId}")
    public Users getUserById(@PathVariable Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
    }

    @PostMapping
    public Users addUser(@RequestBody AddUserRequest userRequest  ) {
        Users users = new Users();
        users.setUsername((userRequest.getUsername()));
        users.setPassword(userRequest.getPassword());
        return userRepository.save(users);
    }

    @PostMapping("/{userId}/todos")
    public void addTodo(@PathVariable Long userId, @RequestBody AddTodoRequest todoRequest){
        Users users = userRepository.findById(userId).orElseThrow(()-> new NoSuchElementException());
        Todo todo = new Todo();
        todo.setContent(todoRequest.getContent());
        users.getTodoList().add(todo);
        todoRepository.save(todo);
        userRepository.save(users);
    }

    @PostMapping("/todos/{todoId}")
    public void toggleTodoCompleted(@PathVariable Long todoId){
        Todo todo = todoRepository.findById(todoId).orElseThrow(()-> new NoSuchElementException());
        todo.setCompleted(!todo.getCompleted());
        todoRepository.save(todo);
    }

    @DeleteMapping("{userId}/todos/{todoId}")
    public void deleteTodo(@PathVariable Long todoId, @PathVariable Long userId) {
        Users users = userRepository.findById(userId).orElseThrow(()-> new NoSuchElementException());
        Todo todo = todoRepository.findById(todoId).orElseThrow(()-> new NoSuchElementException());
        users.getTodoList().remove(todo);
        todoRepository.delete(todo);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        Users users = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
        userRepository.delete(users);
    }
}
