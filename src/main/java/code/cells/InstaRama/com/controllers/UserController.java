package code.cells.InstaRama.com.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import code.cells.InstaRama.com.dtos.LoginRequest;
import code.cells.InstaRama.com.dtos.RegisterRequest;
import code.cells.InstaRama.com.dtos.UpdateUserRequest;
import code.cells.InstaRama.com.models.User;
import code.cells.InstaRama.com.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/users")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @GetMapping // http://localhost:8080/api/users
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> userList = userService.getAllUsers();
        return ResponseEntity.ok(userList);
    }

    @GetMapping(path = "{userId}") // http://localhost:8080/api/users/id
    public ResponseEntity<User> getUserById(
        @PathVariable("userId") Long id){

        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping(path = "register") // http://localhost:8080/api/users/register
    public ResponseEntity<User> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        User user = userService.registerUser(registerRequest);
        return ResponseEntity.ok(user);
    }

    @PostMapping(path = "login") // http://localhost:8080/api/users/login
    public ResponseEntity<User> loginUser(@Valid @RequestBody LoginRequest loginRequest) {
        User user = userService.loginUser(loginRequest);
        return ResponseEntity.ok(user);
    }

    @PutMapping(path = "{userId}") // http://localhost:8080/api/users/id
    public ResponseEntity<User> updateUserById(
        @PathVariable("userId") Long id,
        @Valid @RequestBody UpdateUserRequest updateUserRequest
        ){
        User user = userService.updateUser(id, updateUserRequest);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping(path = "{userId}") // http://localhost:8080/api/users/id
    public ResponseEntity<User> deleteUser(
            @PathVariable("userId") Long id
    ){
        User user = userService.deleteUserById(id);
        return ResponseEntity.ok(user);
    }
}
